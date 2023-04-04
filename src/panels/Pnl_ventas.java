package panels;

import VentanasEmegentes.Busqueda_Comprobante;
import VentanasEmegentes.Busqueda_ProductosBasic;
import VentanasEmegentes.Process_store;
import VentanasEmegentes.RegistroClientes;
import VentanasEmegentes.Venta_Rapida;
import controlador.Cola_Ventas;
import controlador.ListaCircular_Clientes;
import controlador.ListaCircular_Inventario;
import controlador.Process_Clientes;
import controlador.Process_Inventario;
import controlador.Process_Ventas;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import message.Message_confirmacion;
import modelo.Clientes;
import modelo.Inventario;
import modelo.Venta;

public class Pnl_ventas extends javax.swing.JPanel {

    // Inventario
    Inventario inventario;
    ListaCircular_Inventario listCircInv = new ListaCircular_Inventario();
    Process_Inventario processInve = new Process_Inventario();

    // Ventas
    Process_Ventas PV = new Process_Ventas();
    Cola_Ventas CV = new Cola_Ventas();

    // Cliente
    Clientes cl;
    ListaCircular_Clientes listCircClient = new ListaCircular_Clientes();
    Process_Clientes processClient = new Process_Clientes();

    // Fecha y Hora
    LocalDate FechaActual = LocalDate.now();
    LocalTime horaActual = LocalTime.now();

    String[] cab = {"Codigo", "Descripcion", "Cantidad", "Precio U.", "Precio T.", " "};
    public DefaultTableModel modelov = new DefaultTableModel(null, cab);

    public Pnl_ventas() {
        initComponents();
        Panel_Pagos.setVisible(false);
        RealizarCompra(true);
        Tabla_Ventas.setModel(modelov);
        CargarInventario();
        CargarClientes();
        Columnas();
    }

    //Limpieza de Campos
    public void LimpiezaCampos() {
        modelov = (DefaultTableModel) panels.Pnl_ventas.Tabla_Ventas.getModel();
        tipo_recibo.setSelectedIndex(0);
        txt_NroRecibo.setText(" --- ");
        jtxtDNI.setText(null);
        txt_datosC.setText(null);
        jtxtSubTotal.setText("0.00");
        jtxtIGV.setText("0.00");
        jtxtPrecioTotal.setText("0.00");
        RealizarCompra(true);
        btn_GenerarCompra.setVisible(true);
        Panel_Pagos.setVisible(false);
        modelov.removeRow(0);
    }

    // Ocultar y mostrar detalles
    private void RealizarCompra(boolean s) {
        Cod_Producto.setVisible(s);
        btn_searchProducto.setVisible(s);
        Descripcion_Producto.setVisible(s);
        cantidad_producto.setVisible(s);
        precio_producto.setVisible(s);
        btn_add.setVisible(s);
    }

    //
    private void Columnas() {
        Tabla_Ventas.getColumnModel().getColumn(0).setPreferredWidth(100);
        Tabla_Ventas.getColumnModel().getColumn(0).setResizable(false);
        Tabla_Ventas.getColumnModel().getColumn(1).setPreferredWidth(350);
        Tabla_Ventas.getColumnModel().getColumn(1).setResizable(false);
        Tabla_Ventas.getColumnModel().getColumn(2).setPreferredWidth(50);
        Tabla_Ventas.getColumnModel().getColumn(2).setResizable(false);
        Tabla_Ventas.getColumnModel().getColumn(5).setPreferredWidth(10);
        Tabla_Ventas.getColumnModel().getColumn(5).setResizable(false);
    }

    // Asignando nro de Documento
    private void nroDocumento() {
        switch (tipo_recibo.getSelectedIndex()) {
            case 1:
                txt_NroRecibo.setText("B00" + String.valueOf(1000000 + Integer.parseInt(panels.Pnl_home._TotalBoletas_.getText())).substring(0, 1) + " - " + String.valueOf(100000 + Integer.parseInt(panels.Pnl_home._TotalBoletas_.getText())).substring(1, 6));
                break;
            case 2:
                txt_NroRecibo.setText("00" + String.valueOf(1000000 + Integer.parseInt(panels.Pnl_home._TotalFacturas_.getText())).substring(0, 1) + " - " + String.valueOf(100000 + Integer.parseInt(panels.Pnl_home._TotalFacturas_.getText())).substring(1, 6));
                break;
            default:
                txt_NroRecibo.setText("---");
                break;
        }
    }

    // Cargar Inventario
    private void CargarInventario() {
        try {
            processInve.leer();
            for (Inventario inve : processInve.lista) {
                listCircInv.insertar(inve);
            }
        } catch (Exception e) {
            System.out.println("error al Cargar el inventario : " + e);
        }
    }

    // Cargar Clientes
    private void CargarClientes() {
        try {
            processClient.leer();
            for (Clientes cls : processClient.lista) {
                listCircClient.insertar(cls);
            }
        } catch (Exception e) {
            System.out.println("error al Cargar el inventario : " + e);
        }
    }

    //Cargar Ventas
    private void cargar_ventas() {
        try {
            PV.leer();
            for (Venta v : PV.arrayv) {
                CV.encolar(v);
            }
        } catch (Exception e) {
        }
    }

    // Busqueda Productos
    private void searchProducto() {
        try {
            inventario = listCircInv.buscar(Cod_Producto.getText());
            if (inventario != null) {
                Descripcion_Producto.setText(inventario.getDescripcion());
                cantidad_producto.setText(null);
                cantidad_producto.requestFocus();
                precio_producto.setText(String.valueOf(inventario.getPrecio()));
            } else {
                JOptionPane.showMessageDialog(null, "El codigo * " + Cod_Producto.getText() + " * no existe");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el producto : " + e);
        }
    }

    // Busqueda clientes
    private void searchClient() {
        try {
            cl = listCircClient.buscar(jtxtDNI.getText());
            if (cl != null) {
                txt_datosC.setText(cl.getNombres() + " " + cl.getApellidos());
                switch (cl.getTip_Docu()) {
                    case "D.N.I":
                        RB_DNI.setSelected(true);
                        break;
                    case "C.E":
                        RB_CE.setSelected(true);
                        break;
                    case "RUC":
                        RB_RUC.setSelected(true);
                        break;
                }
            } else {
                txt_datosC.setText(null);
                RB_DNI.setSelected(false);
                RB_CE.setSelected(false);
                RB_RUC.setSelected(false);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el producto : " + e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_TipoDocumento = new javax.swing.ButtonGroup();
        buttonGroup_MediodePago = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        _comprobante_ = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rSLabelFecha1 = new rojeru_san.rsdate.RSLabelFecha();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        btn_RegisterCliente = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        jPanel5 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tipo_recibo = new rojerusan.RSComboMetro();
        jLabel5 = new javax.swing.JLabel();
        txt_NroRecibo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jtxtDNI = new javax.swing.JTextField();
        RB_RUC = new javax.swing.JRadioButton();
        RB_CE = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        txt_datosC = new RSMaterialComponent.RSTextFieldIconDos();
        RB_DNI = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        Panel_Importes = new javax.swing.JPanel();
        jtxtPrecioTotal = new javax.swing.JLabel();
        jtxtSubTotal = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jtxtIGV = new javax.swing.JLabel();
        Moneda = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btn_GenerarCompra = new rojeru_san.complementos.RSButtonHover();
        Descripcion_Producto = new rojeru_san.rsfield.RSTextMaterial();
        precio_producto = new rojeru_san.rsfield.RSTextMaterial();
        jLabel14 = new javax.swing.JLabel();
        Cod_Producto = new rojeru_san.rsfield.RSTextMaterial();
        btn_searchProducto = new RSMaterialComponent.RSButtonIconUno();
        btn_add = new RSMaterialComponent.RSButtonIconUno();
        cantidad_producto = new rojeru_san.rsfield.RSTextMaterial();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Ventas = new rojerusan.RSTableMetro();
        Panel_Pagos = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        tc = new javax.swing.JRadioButton();
        rSLabelImage1 = new rojeru_san.rslabel.RSLabelImage();
        pe = new javax.swing.JRadioButton();
        rSLabelImage2 = new rojeru_san.rslabel.RSLabelImage();
        btn_NextPago = new rojeru_san.complementos.RSButtonHover();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        btn_SearchProducto = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_StoreFast = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        clean = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        jPanel8 = new javax.swing.JPanel();
        rSLabelImage3 = new rojeru_san.rslabel.RSLabelImage();
        btn_Order1 = new RSMaterialComponent.RSButtonMaterialRippleIcon();

        _comprobante_.setBackground(new java.awt.Color(255, 255, 255));
        _comprobante_.setColumns(20);
        _comprobante_.setRows(5);
        jScrollPane2.setViewportView(_comprobante_);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("  Punto de Venta (POS)");

        rSLabelFecha1.setForeground(new java.awt.Color(255, 255, 255));

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelHora1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelFecha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rSLabelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSLabelHora1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btn_RegisterCliente.setText(" Registrar Nuevo Cliente");
        btn_RegisterCliente.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PERSON_ADD);
        btn_RegisterCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterClienteActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator5.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DATOS DE EMISIÓN");

        jSeparator4.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText(":: Tipo de Recibo:");

        tipo_recibo.setForeground(new java.awt.Color(51, 51, 51));
        tipo_recibo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccionar --", "Boleta", "Factura" }));
        tipo_recibo.setColorArrow(new java.awt.Color(255, 255, 255));
        tipo_recibo.setColorBorde(new java.awt.Color(255, 255, 255));
        tipo_recibo.setColorFondo(new java.awt.Color(255, 255, 255));
        tipo_recibo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        tipo_recibo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipo_reciboItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText(":: Número de Recibo:");

        txt_NroRecibo.setEditable(false);
        txt_NroRecibo.setBackground(new java.awt.Color(255, 255, 255));
        txt_NroRecibo.setFont(new java.awt.Font("Yu Gothic Light", 1, 14)); // NOI18N
        txt_NroRecibo.setForeground(new java.awt.Color(0, 0, 0));
        txt_NroRecibo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_NroRecibo.setText("- - -");
        txt_NroRecibo.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_NroRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tipo_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NroRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText(":: Tipo de Documento:");

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText(":: Número de Documento:");

        jtxtDNI.setBackground(new java.awt.Color(255, 255, 255));
        jtxtDNI.setFont(new java.awt.Font("Yu Gothic Light", 1, 14)); // NOI18N
        jtxtDNI.setForeground(new java.awt.Color(0, 0, 0));
        jtxtDNI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtDNI.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtxtDNIKeyReleased(evt);
            }
        });

        RB_RUC.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup_TipoDocumento.add(RB_RUC);
        RB_RUC.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        RB_RUC.setForeground(new java.awt.Color(0, 0, 0));
        RB_RUC.setText("RUC");

        RB_CE.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup_TipoDocumento.add(RB_CE);
        RB_CE.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        RB_CE.setForeground(new java.awt.Color(0, 0, 0));
        RB_CE.setText("C.E");

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText(":: Datos del Cliente :");

        txt_datosC.setForeground(new java.awt.Color(0, 0, 0));
        txt_datosC.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_datosC.setColorIcon(new java.awt.Color(51, 51, 51));
        txt_datosC.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        txt_datosC.setPhColor(new java.awt.Color(51, 51, 51));
        txt_datosC.setPlaceholder("Nombres y Apellidos");

        RB_DNI.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup_TipoDocumento.add(RB_DNI);
        RB_DNI.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        RB_DNI.setForeground(new java.awt.Color(0, 0, 0));
        RB_DNI.setText(" D.N.I");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(RB_DNI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RB_RUC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RB_CE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jtxtDNI)
                        .addContainerGap())))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txt_datosC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RB_RUC)
                    .addComponent(RB_CE)
                    .addComponent(RB_DNI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_datosC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator5)
                    .addComponent(jSeparator4)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        Panel_Importes.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Importes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jtxtPrecioTotal.setFont(new java.awt.Font("Cambria", 1, 38)); // NOI18N
        jtxtPrecioTotal.setForeground(new java.awt.Color(51, 51, 51));
        jtxtPrecioTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtxtPrecioTotal.setText("0.00 ");

        jtxtSubTotal.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jtxtSubTotal.setForeground(new java.awt.Color(51, 51, 51));
        jtxtSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtxtSubTotal.setText("0.00");

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("SubTotal:          S/.");

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("I.G.V (18%):    S/.");

        jtxtIGV.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jtxtIGV.setForeground(new java.awt.Color(51, 51, 51));
        jtxtIGV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jtxtIGV.setText("0.00");

        Moneda.setFont(new java.awt.Font("Cambria", 1, 38)); // NOI18N
        Moneda.setForeground(new java.awt.Color(51, 51, 51));
        Moneda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Moneda.setText("S/.");

        javax.swing.GroupLayout Panel_ImportesLayout = new javax.swing.GroupLayout(Panel_Importes);
        Panel_Importes.setLayout(Panel_ImportesLayout);
        Panel_ImportesLayout.setHorizontalGroup(
            Panel_ImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ImportesLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(Panel_ImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(Panel_ImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtxtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(Moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jtxtPrecioTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        Panel_ImportesLayout.setVerticalGroup(
            Panel_ImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_ImportesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_ImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Moneda)
                    .addComponent(jtxtPrecioTotal)
                    .addGroup(Panel_ImportesLayout.createSequentialGroup()
                        .addGroup(Panel_ImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jtxtSubTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_ImportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jtxtIGV))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btn_GenerarCompra.setText("Generar Compra");
        btn_GenerarCompra.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btn_GenerarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GenerarCompraActionPerformed(evt);
            }
        });

        Descripcion_Producto.setEditable(false);
        Descripcion_Producto.setBackground(new java.awt.Color(255, 255, 255));
        Descripcion_Producto.setForeground(new java.awt.Color(0, 0, 0));
        Descripcion_Producto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Descripcion_Producto.setColorMaterial(new java.awt.Color(51, 51, 51));
        Descripcion_Producto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        Descripcion_Producto.setPlaceholder("Descripcion del Producto");

        precio_producto.setEditable(false);
        precio_producto.setBackground(new java.awt.Color(255, 255, 255));
        precio_producto.setForeground(new java.awt.Color(0, 0, 0));
        precio_producto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        precio_producto.setColorMaterial(new java.awt.Color(51, 51, 51));
        precio_producto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        precio_producto.setPlaceholder("Precio U.");

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Detalles de Compra");

        Cod_Producto.setBackground(new java.awt.Color(255, 255, 255));
        Cod_Producto.setForeground(new java.awt.Color(0, 0, 0));
        Cod_Producto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Cod_Producto.setColorMaterial(new java.awt.Color(51, 51, 51));
        Cod_Producto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        Cod_Producto.setPlaceholder("Ingrese Codigo");

        btn_searchProducto.setBackground(new java.awt.Color(255, 255, 255));
        btn_searchProducto.setForeground(new java.awt.Color(0, 0, 0));
        btn_searchProducto.setBackgroundHover(new java.awt.Color(0, 114, 198));
        btn_searchProducto.setForegroundText(new java.awt.Color(0, 0, 0));
        btn_searchProducto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btn_searchProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchProductoActionPerformed(evt);
            }
        });

        btn_add.setBackground(new java.awt.Color(255, 255, 255));
        btn_add.setForeground(new java.awt.Color(0, 0, 0));
        btn_add.setBackgroundHover(new java.awt.Color(0, 114, 198));
        btn_add.setForegroundText(new java.awt.Color(0, 0, 0));
        btn_add.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        cantidad_producto.setBackground(new java.awt.Color(255, 255, 255));
        cantidad_producto.setForeground(new java.awt.Color(0, 0, 0));
        cantidad_producto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cantidad_producto.setColorMaterial(new java.awt.Color(51, 51, 51));
        cantidad_producto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        cantidad_producto.setPlaceholder("Cantidad");

        Tabla_Ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Decripcion", "Cantidad", "Precio U.", "Precio T."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Ventas.setBackgoundHover(new java.awt.Color(255, 255, 255));
        Tabla_Ventas.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla_Ventas.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla_Ventas.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla_Ventas.setFontHead(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        Tabla_Ventas.setForegroundHover(new java.awt.Color(0, 0, 0));
        Tabla_Ventas.setGridColor(new java.awt.Color(255, 255, 255));
        Tabla_Ventas.setSelectionBackground(new java.awt.Color(204, 204, 204));
        Tabla_Ventas.setSelectionForeground(new java.awt.Color(0, 0, 0));
        Tabla_Ventas.setShowVerticalLines(false);
        Tabla_Ventas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tabla_Ventas);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(btn_GenerarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(113, 113, 113))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cod_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btn_searchProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Descripcion_Producto, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidad_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(precio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Descripcion_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(precio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Cod_Producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cantidad_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_searchProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_GenerarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Panel_Pagos.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Pagos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jSeparator7.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator7.setForeground(new java.awt.Color(204, 204, 204));

        tc.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup_MediodePago.add(tc);
        tc.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        tc.setForeground(new java.awt.Color(0, 0, 0));
        tc.setText(" Tarjeta de Debito y/o Credito ");

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Medios de pago.png"))); // NOI18N

        pe.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup_MediodePago.add(pe);
        pe.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        pe.setForeground(new java.awt.Color(0, 0, 0));
        pe.setSelected(true);
        pe.setText(" Pago en efectivo");

        rSLabelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dineroEfectivo.png"))); // NOI18N

        btn_NextPago.setText("Continuar");
        btn_NextPago.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        btn_NextPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextPagoActionPerformed(evt);
            }
        });

        jSeparator8.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator8.setForeground(new java.awt.Color(204, 204, 204));

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("MEDIOS DE PAGO");

        javax.swing.GroupLayout Panel_PagosLayout = new javax.swing.GroupLayout(Panel_Pagos);
        Panel_Pagos.setLayout(Panel_PagosLayout);
        Panel_PagosLayout.setHorizontalGroup(
            Panel_PagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PagosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_PagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_PagosLayout.createSequentialGroup()
                        .addGroup(Panel_PagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator7)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_PagosLayout.createSequentialGroup()
                                .addComponent(pe, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSLabelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_PagosLayout.createSequentialGroup()
                        .addComponent(tc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(Panel_PagosLayout.createSequentialGroup()
                        .addGroup(Panel_PagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_PagosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_NextPago, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        Panel_PagosLayout.setVerticalGroup(
            Panel_PagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_PagosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_PagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_PagosLayout.createSequentialGroup()
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSLabelImage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_PagosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rSLabelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pe, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_NextPago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_Importes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Panel_Pagos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(Panel_Importes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Panel_Pagos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_SearchProducto.setText(" Consultar Producto ");
        btn_SearchProducto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btn_SearchProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SearchProductoActionPerformed(evt);
            }
        });

        btn_StoreFast.setText(" Venta Rapida");
        btn_StoreFast.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.NOTE_ADD);
        btn_StoreFast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StoreFastActionPerformed(evt);
            }
        });

        clean.setText(" Nueva Venta");
        clean.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR_ALL);
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Venta.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(rSLabelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btn_Order1.setText("Comprobantes");
        btn_Order1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btn_Order1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Order1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_RegisterCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btn_Order1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_StoreFast, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clean, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_SearchProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_RegisterCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SearchProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_StoreFast, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clean, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Order1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_RegisterClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterClienteActionPerformed
        new RegistroClientes(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btn_RegisterClienteActionPerformed

    private void btn_GenerarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GenerarCompraActionPerformed
        Panel_Pagos.setVisible(true);
    }//GEN-LAST:event_btn_GenerarCompraActionPerformed

    private void btn_searchProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchProductoActionPerformed
        CargarInventario();
        searchProducto();
    }//GEN-LAST:event_btn_searchProductoActionPerformed

    private void tipo_reciboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipo_reciboItemStateChanged
        nroDocumento();
    }//GEN-LAST:event_tipo_reciboItemStateChanged

    private void btn_StoreFastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StoreFastActionPerformed
        new Venta_Rapida(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btn_StoreFastActionPerformed

    private void btn_SearchProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchProductoActionPerformed
        new Busqueda_ProductosBasic(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btn_SearchProductoActionPerformed

    private void jtxtDNIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtDNIKeyReleased
        CargarClientes();
        if (listCircClient.Longitud() != 0) {
            searchClient();
            if (jtxtDNI.getText().equals("9")) {
                txt_datosC.setText("Cliente General");
            }
        }
    }//GEN-LAST:event_jtxtDNIKeyReleased

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        Columnas();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_NextPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextPagoActionPerformed
        if (tc.isSelected()) {
            Process_store.Tipo_Pago = "Tarjeta de Debito y/o Credito";
        }
        if (pe.isSelected()) {
            Process_store.Tipo_Pago = "Pago en Efectivo";
        }

        new Process_store(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btn_NextPagoActionPerformed

    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed
        try {
            LimpiezaCampos();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cleanActionPerformed

    private void btn_Order1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Order1ActionPerformed
        cargar_ventas();
        if (CV.nro_ventas() == 0) {
            Message_confirmacion.Message = "<html>No hay comprobantes registrados en la base de datos</html>";
            Message_confirmacion.tipo_message = "advertencia";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        } else {
            new Busqueda_Comprobante(new javax.swing.JFrame(), true).setVisible(true);
        }
    }//GEN-LAST:event_btn_Order1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static rojeru_san.rsfield.RSTextMaterial Cod_Producto;
    public static rojeru_san.rsfield.RSTextMaterial Descripcion_Producto;
    public static javax.swing.JLabel Moneda;
    private javax.swing.JPanel Panel_Importes;
    public static javax.swing.JPanel Panel_Pagos;
    public static javax.swing.JRadioButton RB_CE;
    public static javax.swing.JRadioButton RB_DNI;
    public static javax.swing.JRadioButton RB_RUC;
    public static rojerusan.RSTableMetro Tabla_Ventas;
    public static javax.swing.JTextArea _comprobante_;
    public static rojeru_san.complementos.RSButtonHover btn_GenerarCompra;
    private rojeru_san.complementos.RSButtonHover btn_NextPago;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_Order1;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_RegisterCliente;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_SearchProducto;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_StoreFast;
    public static RSMaterialComponent.RSButtonIconUno btn_add;
    public static RSMaterialComponent.RSButtonIconUno btn_searchProducto;
    private javax.swing.ButtonGroup buttonGroup_MediodePago;
    private javax.swing.ButtonGroup buttonGroup_TipoDocumento;
    public static rojeru_san.rsfield.RSTextMaterial cantidad_producto;
    public static RSMaterialComponent.RSButtonMaterialRippleIcon clean;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    public static javax.swing.JTextField jtxtDNI;
    public static javax.swing.JLabel jtxtIGV;
    public static javax.swing.JLabel jtxtPrecioTotal;
    public static javax.swing.JLabel jtxtSubTotal;
    private javax.swing.JRadioButton pe;
    public static rojeru_san.rsfield.RSTextMaterial precio_producto;
    private rojeru_san.rsdate.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage1;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage2;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage3;
    private javax.swing.JRadioButton tc;
    public static rojerusan.RSComboMetro tipo_recibo;
    public static javax.swing.JTextField txt_NroRecibo;
    public static RSMaterialComponent.RSTextFieldIconDos txt_datosC;
    // End of variables declaration//GEN-END:variables
}
