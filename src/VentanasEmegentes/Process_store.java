package VentanasEmegentes;

import Funciones.Decimales_2Digitos;
import com.sun.awt.AWTUtilities;
import controlador.Cola_Ingresos;
import controlador.Cola_Ventas;
import Funciones.NumeroATexto;
import controlador.Process_Comprobante;
import controlador.Process_Ingresos;
import controlador.Process_Ventas;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.table.DefaultTableModel;
import modelo.Ingresos_Ventas;
import modelo.Venta;
import static panels.Pnl_Estadisticas.Porce_Efectivo;
import static panels.Pnl_Estadisticas.Porce_Tarjeta;
import static panels.Pnl_Estadisticas.porcen_boleta;
import panels.Pnl_ventas;
import static panels.Pnl_ventas.Tabla_Ventas;
import static panels.Pnl_ventas._comprobante_;
import static panels.Pnl_ventas.jtxtDNI;
import static panels.Pnl_ventas.jtxtIGV;
import static panels.Pnl_ventas.jtxtSubTotal;
import static panels.Pnl_ventas.txt_NroRecibo;
import static panels.Pnl_ventas.txt_datosC;
import views.Comprobante;
import views.Ventana_Administrador;
import static panels.Pnl_Estadisticas.porcen_factura;

public class Process_store extends javax.swing.JDialog {

    public static String Tipo_Pago;
    public static double Importe_Venta;

    // Variables
    char caracter_dia;

    // Decimales
    Decimales_2Digitos d2 = new Decimales_2Digitos();

    // Hora y Fecha
    LocalDate FechaActual = LocalDate.now();
    LocalTime horaActual = LocalTime.now();

    // Ingresos
    Ingresos_Ventas ingres;
    Process_Ingresos PI = new Process_Ingresos();
    Cola_Ingresos CI = new Cola_Ingresos();
    String[] cab = {"No. Operacion", "Medio de Pago", "Importe", "Cupon", "Descuento", "Total",
        "Registrado por", "Fecha de Proceso", "Hora de Proceso"};
    public DefaultTableModel modelo_Ope = new DefaultTableModel(null, cab);

    // Ventas
    Venta ven;
    Process_Ventas PV = new Process_Ventas();
    Cola_Ventas CV = new Cola_Ventas();
    String[] cabVentas = {"comprobante", "Nro comprobante", " Nro Operacion", "Cliente", "medio de pago",
        "Total", "Registrado por", "Fecha de Proceso", "Hora de Proceso"};
    public DefaultTableModel modelo_Ventas = new DefaultTableModel(null, cabVentas);

    // Numero a Texto
    NumeroATexto nt = new NumeroATexto();

    public Process_store(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        AWTUtilities.setWindowOpaque(this, false);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        proces.setVisible(false);
        panel_Notificaciones.setVisible(true);
        btn_TerminarProceso.setVisible(false);
        Descuento(true);
        modelo_Ope = (DefaultTableModel) panels.Pnl_Estadisticas.Tabla_ingresos.getModel();
        btn_ProcesarPago.setEnabled(true);
        txt_montoRecibido.requestFocus();
        IngresandoImporte();
        stadis();
        // Cargando base de datos
        cargar_ingresos();
        cargar_Ventas();
    }

    //Activando Descuento
    private void Descuento(boolean d) {
        message1.setVisible(!d);
        jLabel_descuento.setVisible(d);
        importe_descuento.setVisible(d);
    }

    // Nro Operaciones
    private String nro_operaciones() {
        if (tipe_pago.getText().equals("Tarjeta de Debito y/o Credito")) {
            return txt_Operacion.getText();
        } else {
            return String.valueOf(900000 + CI.nro_Operaciones());
        }

    }

    // Listar Ingresos
    private void listar() {
        modelo_Ope.setRowCount(0);
        for (Ingresos_Ventas i : CI.listado()) {
            modelo_Ope.addRow(i.getInformacionIngresos());
            panels.Pnl_Estadisticas.Tabla_ingresos.setModel(modelo_Ope);
        }
    }

    // Listar comprobantes
    private void listar_Comprobantes() {
        modelo_Ventas.setRowCount(0);
        for (Venta i : CV.listado()) {
            modelo_Ventas.addRow(i.getInformacionVentas());
            panels.Pnl_Estadisticas.Tabla_Ventas.setModel(modelo_Ventas);
        }
    }

    //Cargar Ingresos
    private void cargar_ingresos() {
        modelo_Ope.setRowCount(0);
        try {
            PI.leer();
            for (Ingresos_Ventas i : PI.arrayCat) {
                modelo_Ope.addRow(i.getInformacionIngresos());
                CI.encolar(i);
            }
        } catch (Exception e) {
        }

    }

    //Cargar Ingresos
    private void cargar_Ventas() {
        modelo_Ventas.setRowCount(0);
        try {
            PV.leer();
            for (Venta v : PV.arrayv) {
                modelo_Ventas.addRow(v.getInformacionVentas());
                CV.encolar(v);
            }
        } catch (Exception e) {
        }

    }

    // Ingresando Importe
    private void IngresandoImporte() {

        tipe_pago.setText(Tipo_Pago);
        message.setVisible(false);

        if (tipe_pago.getText().equals("Tarjeta de Debito y/o Credito")) {
            TC.setVisible(true);
            btn_ProcesarPago1.setVisible(false);
            txt_montoRecibido.setEditable(false);

            importe.setText(d2.Decimal_DobleS(Importe_Venta));
            txt_montoRecibido.setText(d2.Decimal_DobleS(Importe_Venta));
            pago_total.setText(d2.Decimal_DobleS(Importe_Venta));

        } else {
            TC.setVisible(false);
            txt_montoRecibido.setEditable(true);
            btn_ProcesarPago.setVisible(false);
            importe.setText(d2.Decimal_DobleS(Importe_Venta));
            pago_total.setText(d2.Decimal_DobleS(Importe_Venta));

        }
    }

    private void Pago() {
        try {

            if ((Double.parseDouble(txt_montoRecibido.getText()) - (Importe_Venta - Double.parseDouble(importe_descuento.getText()))) >= 0) {

                Vuelto.setText(d2.Decimal_DobleS((Double.parseDouble(txt_montoRecibido.getText())) - (Double.parseDouble(pago_total.getText()))));
                message.setVisible(false);
                btn_ProcesarPago.setEnabled(true);
                btn_ProcesarPago1.setEnabled(true);

            } else {

                Vuelto.setText("0.00");
                message.setText("<html>El monto ingresado es inferior al total a pagar</html>");
                message.setVisible(true);
                btn_ProcesarPago.setEnabled(false);
                btn_ProcesarPago1.setEnabled(false);

            }
        } catch (Exception e) {
            message.setText("<html>El monto ingresado es invalido</html>");
        }

    }

    // Sacando nombre de cupon
    private String InscribiendoCupon() {
        if (txt_cupon.getText().isEmpty()) {
            return " --- ";
        } else {
            return txt_cupon.getText();
        }
    }

    // Sacando Cliente
    private String Cliente() {
        if (panels.Pnl_ventas.jtxtDNI.getText().isEmpty() || panels.Pnl_ventas.jtxtDNI.getText().equals("9")) {
            return "No registrado";
        } else {
            return "Registrado";
        }
    }

    // Estadisticas tabla 
    private void stadis() {
        panels.Pnl_home._TotalBoletas_.setText(String.valueOf((int) CV.TotalBoleta()));
        panels.Pnl_home._TotalFacturas_.setText(String.valueOf((int) CV.TotalFactura()));
        panels.Pnl_home._TotalOperaciones_.setText(String.valueOf(CI.nro_Operaciones()));
        panels.Pnl_home._IngresoActual_.setText("S/. " + d2.Decimal_DobleS(CI.SubTotalActual()));
        panels.Pnl_home._ImporteDescuento_.setText("S/. " + d2.Decimal_DobleS(CI.DescuentoActual()));
        panels.Pnl_home._TotalNeto_.setText("S/. " + d2.Decimal_DobleS(CI.ImporteActual()));

        // Porcentaje de Comprobantes
        if (CV.nro_ventas() == 0) {
            System.out.println("No hay Operaciones a mostrar");
        } else {
            int PorceVenta = ((int) ((CV.TotalBoleta() / CV.nro_ventas()) * 100) + ((int) ((CV.TotalFactura() / CV.nro_ventas()) * 100)));

            if (PorceVenta == 100) {
                porcen_factura.setValue((int) ((CV.TotalFactura() / CV.nro_ventas()) * 100));
                panels.Pnl_home._PocFacturas_.setValue((int) ((CV.TotalFactura() / CV.nro_ventas()) * 100));
            } else {
                porcen_factura.setValue(((int) ((CV.TotalFactura() / CI.nro_Operaciones()) * 100)) + (100 - PorceVenta));
                panels.Pnl_home._PocFacturas_.setValue(((int) ((CV.TotalFactura() / CV.nro_ventas()) * 100)) + (100 - PorceVenta));
            }

            porcen_boleta.setValue((int) ((CV.TotalBoleta() / CV.nro_ventas()) * 100));
            panels.Pnl_home._PocBoletas_.setValue((int) ((CV.TotalBoleta() / CV.nro_ventas()) * 100));
        }

        if (CI.nro_Operaciones() == 0) {
            System.out.println("No hay datos a mostrar");
        } else {
            // Porcentaje de Efectivo y Tarjetas       
            int PorcIng = ((int) ((CI.Cont_Tarjeta() / CI.nro_Operaciones()) * 100) + ((int) ((CI.Cont_efectivo() / CI.nro_Operaciones()) * 100)));

            if (PorcIng == 100) {
                Porce_Tarjeta.setValue((int) ((CI.Cont_Tarjeta() / CI.nro_Operaciones()) * 100));
                panels.Pnl_home._PorTarjeta_.setValue((int) ((CI.Cont_Tarjeta() / CI.nro_Operaciones()) * 100));
            } else {
                Porce_Tarjeta.setValue(((int) ((CI.Cont_Tarjeta() / CI.nro_Operaciones()) * 100)) + (100 - PorcIng));
                panels.Pnl_home._PorTarjeta_.setValue(((int) ((CI.Cont_Tarjeta() / CI.nro_Operaciones()) * 100)) + (100 - PorcIng));
            }

            Porce_Efectivo.setValue((int) ((CI.Cont_efectivo() / CI.nro_Operaciones()) * 100));
            panels.Pnl_home._PocEfectivo_.setValue((int) ((CI.Cont_efectivo() / CI.nro_Operaciones()) * 100));
        }
    }

    // REGISTRAR
    private void Registrar() {
        //No. Operacion, Medio de Pago, Importe, Cupon, Descuento, Total, Registrado por, Fecha actual, hora actual
        Object[] arreglo = {nro_operaciones(), tipe_pago.getText(), Double.parseDouble(importe.getText()),
            InscribiendoCupon(), Double.parseDouble(importe_descuento.getText()), Double.parseDouble(pago_total.getText()),
            Ventana_Administrador.user, FechaActual, String.valueOf(horaActual).substring(0, 8)};
        ingres = new Ingresos_Ventas(arreglo);
        CI.encolar(ingres);
        try {
            PI.insertar(ingres);
            listar();
            dispose();
        } catch (Exception e) {
            System.out.println("Error al registrar");
        }
    }

    // REGISTRAR
    private void RegistrarComprobante() {
        //Tipo comprobante, Nro comprobante, Nro Operacion, Cliente, medio de pago, Total, Registrado por, Fecha actual, hora actual
        Object[] arreglo = {panels.Pnl_ventas.tipo_recibo.getSelectedItem().toString(), panels.Pnl_ventas.txt_NroRecibo.getText(), nro_operaciones(),
            Cliente(), tipe_pago.getText(), Double.parseDouble(pago_total.getText()), Ventana_Administrador.user, FechaActual, String.valueOf(horaActual).substring(0, 8)};
        ven = new Venta(arreglo);
        CV.encolar(ven);
        try {
            PV.insertar(ven);
            listar_Comprobantes();
            dispose();
        } catch (Exception e) {
            System.out.println("Error al registrar");
        }
    }

    private void Comprobante() {

        _comprobante_.setText(null);
        _comprobante_.append("\n\t--------------------------------------\n"
                + "\t        Sistema de Venta MEMORIESBACK\n"
                + "\t--------------------------------------\n"
                + "\t                 " + Ventana_Administrador.name_empresa.getText() + "\n"
                + "\t          " + panels.Pnl_ventas.tipo_recibo.getSelectedItem().toString() + " de Venta electronica" + "\n"
                + "\t                       " + txt_NroRecibo.getText() + "\n"
                + "\tFecha: " + FechaActual + "              Hora: " + String.valueOf(horaActual).substring(0, 8) + "\n"
                + "\tCajero(a):\n"
                + "\t--------------------------------------\n"
                + "\t\tDatos Cliente\n"
                + "\t--------------------------------------\n"
                + "\t D.N.I: " + jtxtDNI.getText() + "\n"
                + "\t Nombre: " + txt_datosC.getText() + "\n"
                + "\t--------------------------------------\n"
                + "\t Cant.	 Descripcion\t     Precio\n"
                + "\t--------------------------------------\n");
        for (int i = 0; i < Tabla_Ventas.getRowCount(); i++) {
            _comprobante_.append("\t  " + Tabla_Ventas.getValueAt(i, 2).toString()
                    + "   " + Tabla_Ventas.getValueAt(i, 1).toString() + "      " + Tabla_Ventas.getValueAt(i, 4).toString() + "  \n");
        }
        _comprobante_.append("\t--------------------------------------\n");
        _comprobante_.append("\t Subtotal\tS/.\t" + jtxtSubTotal.getText() + "\n"
                + "\t I.G.V(18%)\tS/.\t" + jtxtIGV.getText() + "\n"
                + "\t Descuento:\tS/.\t" + Process_store.importe_descuento.getText() + "\n"
                + "\t Total\tS/.\t" + Process_store.pago_total.getText() + "\n"
                + "\t--------------------------------------\n"
                + "\t Son: " + nt.Convertir(pago_total.getText(), true) + "\n"
                + "\t--------------------------------------\n"
                + "\t              ** Descripcion de pago **\n"
                + "\t--------------------------------------\n");
        _comprobante_.append("\t Tipo de pago:\t" + Process_store.Tipo_Pago + "\n");
        if (Process_store.Tipo_Pago.equals("Pago en Efectivo")) {
            System.out.println("Venta al contado");
            _comprobante_.append("\t Nro Operación:  " + nro_operaciones() + "\n");
        } else {
            _comprobante_.append("\t No. Tarjeta:\t************" + txt_nroTarjetas.getText().substring(txt_nroTarjetas.getText().length() - 4, txt_nroTarjetas.getText().length()) + "\n");
            _comprobante_.append("\t Tipo Tarjeta:  " + tipo_recibo.getSelectedItem().toString() + "\n");
            _comprobante_.append("\t Nro Operación:  " + txt_Operacion.getText() + "\n");
        }
        _comprobante_.append("\t Moneda :\t" + "Soles\n"
                + "\t Importe:\tS/.\t" + Process_store.txt_montoRecibido.getText() + "\n"
                + "\t Vuelto:\tS/.\t" + Process_store.Vuelto.getText() + "\n"
                + "\t--------------------------------------\n"
                + "\tCambio y/o Devolución según politica\n"
                + "\tde comercio. Tiempo estimado 24horas\n"
                + "\t--------------------------------------\n"
                + "\t            Gracias por su preferencia\n");

    }

    private void FinalizarCompra() {
        Registrar();

        txt_montoRecibido.setText(d2.Decimal_DobleS(Double.parseDouble(txt_montoRecibido.getText())));

        Comprobante();
        Comprobante.Operacion = nro_operaciones();
        new Comprobante(new javax.swing.JFrame(), true).setVisible(true);

        try {
            new Process_Comprobante().Crear();
            RegistrarComprobante();
        } catch (Exception e) {
            System.out.println("Error al crear comprobante " + Pnl_ventas.txt_NroRecibo.getText());
        }
        stadis();

        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title_cliente = new javax.swing.JLabel();
        btn_add1 = new javax.swing.JButton();
        tipe_pago = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        importe = new javax.swing.JLabel();
        Vuelto = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_montoRecibido = new javax.swing.JTextField();
        btn_ProcesarPago = new rojeru_san.complementos.RSButtonHover();
        proces = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txt_Operacion = new javax.swing.JTextField();
        TC = new javax.swing.JPanel();
        txt_nroTarjetas = new javax.swing.JTextField();
        tipo_recibo = new rojerusan.RSComboMetro();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        btn_ProcesarPago1 = new rojeru_san.complementos.RSButtonHover();
        jLabel12 = new javax.swing.JLabel();
        txt_cupon = new javax.swing.JTextField();
        message1 = new javax.swing.JLabel();
        importe_descuento = new javax.swing.JLabel();
        jLabel_descuento = new javax.swing.JLabel();
        jLabel_descuento1 = new javax.swing.JLabel();
        pago_total = new javax.swing.JLabel();
        panel_Notificaciones = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_TerminarProceso = new rojeru_san.complementos.RSButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        title_cliente.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        title_cliente.setForeground(new java.awt.Color(255, 255, 255));
        title_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_cliente.setText("Medio de Pago");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_cliente)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btn_add1.setBackground(new java.awt.Color(255, 51, 51));
        btn_add1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btn_add1.setForeground(new java.awt.Color(255, 255, 255));
        btn_add1.setText("cerrar");
        btn_add1.setBorder(null);
        btn_add1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_add1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add1ActionPerformed(evt);
            }
        });

        tipe_pago.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        tipe_pago.setForeground(new java.awt.Color(0, 0, 0));
        tipe_pago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tipe_pago.setText("esperando...");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText(":: Vuelto:");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText(":: Importe de Venta:");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText(":: Tipo de Pago:");

        importe.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        importe.setForeground(new java.awt.Color(0, 0, 0));
        importe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        importe.setText("esperando...");

        Vuelto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        Vuelto.setForeground(new java.awt.Color(0, 0, 0));
        Vuelto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Vuelto.setText("0.00");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText(":: Monto Recibido:");

        txt_montoRecibido.setBackground(new java.awt.Color(255, 255, 255));
        txt_montoRecibido.setFont(new java.awt.Font("Yu Gothic Light", 1, 14)); // NOI18N
        txt_montoRecibido.setForeground(new java.awt.Color(0, 0, 0));
        txt_montoRecibido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_montoRecibido.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_montoRecibido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_montoRecibidoKeyReleased(evt);
            }
        });

        btn_ProcesarPago.setText("Procesar Pago");
        btn_ProcesarPago.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btn_ProcesarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProcesarPagoActionPerformed(evt);
            }
        });

        proces.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText(":: ID de la Operación:");

        txt_Operacion.setBackground(new java.awt.Color(255, 255, 255));
        txt_Operacion.setFont(new java.awt.Font("Yu Gothic Light", 1, 14)); // NOI18N
        txt_Operacion.setForeground(new java.awt.Color(0, 0, 0));
        txt_Operacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Operacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout procesLayout = new javax.swing.GroupLayout(proces);
        proces.setLayout(procesLayout);
        procesLayout.setHorizontalGroup(
            procesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(procesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_Operacion))
        );
        procesLayout.setVerticalGroup(
            procesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(procesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(procesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Operacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        TC.setBackground(new java.awt.Color(255, 255, 255));

        txt_nroTarjetas.setBackground(new java.awt.Color(255, 255, 255));
        txt_nroTarjetas.setFont(new java.awt.Font("Yu Gothic Light", 1, 14)); // NOI18N
        txt_nroTarjetas.setForeground(new java.awt.Color(0, 0, 0));
        txt_nroTarjetas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nroTarjetas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_nroTarjetas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nroTarjetasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nroTarjetasKeyTyped(evt);
            }
        });

        tipo_recibo.setForeground(new java.awt.Color(51, 51, 51));
        tipo_recibo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccionar --", "Visa", "Mastercard", "American Express", "Diners Club" }));
        tipo_recibo.setColorArrow(new java.awt.Color(255, 255, 255));
        tipo_recibo.setColorBorde(new java.awt.Color(255, 255, 255));
        tipo_recibo.setColorFondo(new java.awt.Color(255, 255, 255));
        tipo_recibo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText(":: Nro de Tarjeta :");

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText(":: Tipo de Tarjeta :");

        javax.swing.GroupLayout TCLayout = new javax.swing.GroupLayout(TC);
        TC.setLayout(TCLayout);
        TCLayout.setHorizontalGroup(
            TCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TCLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipo_recibo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(TCLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nroTarjetas))))
        );
        TCLayout.setVerticalGroup(
            TCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipo_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nroTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        message.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        message.setForeground(new java.awt.Color(204, 0, 0));
        message.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        message.setText("<html>Monto ingresado es inferior al importe y/o invalido </html>");

        btn_ProcesarPago1.setText("Finalizar Pago");
        btn_ProcesarPago1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btn_ProcesarPago1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ProcesarPago1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText(":: Cupón de Descuento:");

        txt_cupon.setBackground(new java.awt.Color(255, 255, 255));
        txt_cupon.setFont(new java.awt.Font("Yu Gothic Light", 1, 14)); // NOI18N
        txt_cupon.setForeground(new java.awt.Color(0, 0, 0));
        txt_cupon.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cupon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_cupon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cuponKeyReleased(evt);
            }
        });

        message1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        message1.setForeground(new java.awt.Color(204, 0, 0));
        message1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        message1.setText("<html>cupón de descuento Ingresado no existe y/o invalido </html>");

        importe_descuento.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        importe_descuento.setForeground(new java.awt.Color(153, 0, 0));
        importe_descuento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        importe_descuento.setText("0.00");

        jLabel_descuento.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel_descuento.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_descuento.setText(":: Descuento por Cupon:");

        jLabel_descuento1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel_descuento1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_descuento1.setText(":: Total a pagar:");

        pago_total.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        pago_total.setForeground(new java.awt.Color(0, 0, 0));
        pago_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pago_total.setText("0.00");

        panel_Notificaciones.setBackground(new java.awt.Color(255, 255, 255));
        panel_Notificaciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Anuncios del Comercio ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI", 1, 14), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 114, 198));
        jLabel1.setText("<html>Usa Nuestros cupones para mayor beneficio de Nuestros clientes. PRIMERACOMPRA10 = Descuento de S/ 10.00</html>");

        javax.swing.GroupLayout panel_NotificacionesLayout = new javax.swing.GroupLayout(panel_Notificaciones);
        panel_Notificaciones.setLayout(panel_NotificacionesLayout);
        panel_NotificacionesLayout.setHorizontalGroup(
            panel_NotificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_NotificacionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_NotificacionesLayout.setVerticalGroup(
            panel_NotificacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_NotificacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        btn_TerminarProceso.setText("Finalizar Pago");
        btn_TerminarProceso.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btn_TerminarProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TerminarProcesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 194, Short.MAX_VALUE)
                .addComponent(btn_add1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_TerminarProceso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(message1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_ProcesarPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ProcesarPago1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(proces, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(message, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Vuelto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_descuento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_descuento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(importe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(importe_descuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pago_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_montoRecibido)))
                    .addComponent(panel_Notificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipe_pago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cupon)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_Notificaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipe_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(message1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importe, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importe_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_descuento1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pago_total, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_montoRecibido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Vuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_ProcesarPago1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ProcesarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(proces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_TerminarProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(btn_add1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_add1ActionPerformed

    private void btn_ProcesarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProcesarPagoActionPerformed
        proces.setVisible(true);
        txt_nroTarjetas.setEditable(false);
        tipo_recibo.setEditable(false);
        btn_TerminarProceso.setVisible(true);
    }//GEN-LAST:event_btn_ProcesarPagoActionPerformed

    private void txt_montoRecibidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoRecibidoKeyReleased
        Pago();
    }//GEN-LAST:event_txt_montoRecibidoKeyReleased

    private void txt_nroTarjetasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nroTarjetasKeyReleased
        String Temporal;
        switch (txt_nroTarjetas.getText().length()) {
            case 4:
                Temporal = txt_nroTarjetas.getText();
                txt_nroTarjetas.setText(null);
                txt_nroTarjetas.setText(Temporal + " ");
                break;
            case 9:
                Temporal = txt_nroTarjetas.getText();
                txt_nroTarjetas.setText(null);
                txt_nroTarjetas.setText(Temporal + " ");
                break;
            case 14:
                Temporal = txt_nroTarjetas.getText();
                txt_nroTarjetas.setText(null);
                txt_nroTarjetas.setText(Temporal + " ");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_txt_nroTarjetasKeyReleased

    private void txt_nroTarjetasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nroTarjetasKeyTyped
        if (txt_nroTarjetas.getText().length() >= 19) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nroTarjetasKeyTyped

    private void btn_ProcesarPago1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ProcesarPago1ActionPerformed
        FinalizarCompra();
    }//GEN-LAST:event_btn_ProcesarPago1ActionPerformed

    private void txt_cuponKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cuponKeyReleased
        if (txt_cupon.getText().equals("PRIMERACOMPRA10")) {
            importe_descuento.setText(d2.Decimal_DobleS(10.0));

            pago_total.setText(d2.Decimal_DobleS((Importe_Venta - (Double.parseDouble(importe_descuento.getText())))));

        } else {
            importe_descuento.setText("0.00");
            pago_total.setText(importe.getText());
        }
        Pago();
    }//GEN-LAST:event_txt_cuponKeyReleased

    private void btn_TerminarProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TerminarProcesoActionPerformed
        FinalizarCompra();
    }//GEN-LAST:event_btn_TerminarProcesoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Process_store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Process_store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Process_store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Process_store.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Process_store dialog = new Process_store(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel TC;
    public static javax.swing.JLabel Vuelto;
    private rojeru_san.complementos.RSButtonHover btn_ProcesarPago;
    private rojeru_san.complementos.RSButtonHover btn_ProcesarPago1;
    private rojeru_san.complementos.RSButtonHover btn_TerminarProceso;
    private javax.swing.JButton btn_add1;
    public static javax.swing.JLabel importe;
    public static javax.swing.JLabel importe_descuento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_descuento;
    private javax.swing.JLabel jLabel_descuento1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel message;
    private javax.swing.JLabel message1;
    public static javax.swing.JLabel pago_total;
    private javax.swing.JPanel panel_Notificaciones;
    private javax.swing.JPanel proces;
    private javax.swing.JLabel tipe_pago;
    private rojerusan.RSComboMetro tipo_recibo;
    public javax.swing.JLabel title_cliente;
    private javax.swing.JTextField txt_Operacion;
    private javax.swing.JTextField txt_cupon;
    public static javax.swing.JTextField txt_montoRecibido;
    private javax.swing.JTextField txt_nroTarjetas;
    // End of variables declaration//GEN-END:variables
}
