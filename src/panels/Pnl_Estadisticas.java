package panels;

import Funciones.Decimales_2Digitos;
import Funciones.clsExportarExcel;
import VentanasEmegentes.Busqueda_Comprobante;
import controlador.Cola_Ingresos;
import controlador.Cola_Ventas;
import controlador.ListaCircular_Clientes;
import controlador.ListaCircular_Empleados;
import controlador.Process_Clientes;
import controlador.Process_Empleados;
import controlador.Process_Ingresos;
import controlador.Process_Ventas;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import message.Message_confirmacion;
import modelo.Ingresos_Ventas;
import modelo.Venta;

public class Pnl_Estadisticas extends javax.swing.JPanel {

    // Ingresos
    Process_Ingresos PI = new Process_Ingresos();
    Cola_Ingresos CI = new Cola_Ingresos();

    // Ventas
    Process_Ventas PV = new Process_Ventas();
    Cola_Ventas CV = new Cola_Ventas();

    // Variables
    char caracter_dia;

    // Decimales
    Decimales_2Digitos d2 = new Decimales_2Digitos();

    // Exportacion a excel
    clsExportarExcel obj;

    //Clientes
    Process_Clientes processClien = new Process_Clientes();
    ListaCircular_Clientes listCircClien = new ListaCircular_Clientes();

    //Empleados
    Process_Empleados processe = new Process_Empleados();
    ListaCircular_Empleados listCirce = new ListaCircular_Empleados();

    // Tabla de Ingresos
    String[] cab = {"No. Operacion", "Medio de Pago", "Sub total", "Cupón", "Descuento", "Total",
        "Usuario", "Fecha de Proceso", "Hora de Proceso"};
    DefaultTableModel modelo_Ingresos = new DefaultTableModel(null, cab);

    // Tabla de Clientes
    String[] cabC = {"Documento", "Nro de Documento", "Nombres", "Apellidos", "Sexo", "Celular", "E-mail", "Registrado el", "Registrado por"};
    DefaultTableModel modelo_Cliente = new DefaultTableModel(null, cabC);

    // Tabla de Empleados
    String[] cabE = {"Cod. Interno", "Documento", "Nro. Documento", "Nombres", "Apellido", "Sexo", "Celular", "E-mail", "Cargo"};
    DefaultTableModel modelo_Empleados = new DefaultTableModel(null, cabE);

    // Tabla de Ventas
    String[] cabVentas = {"Comprobante", "Nro comprobante", " Nro Operacion", "Cliente", "medio de pago",
        "Total", "Registrado por", "Fecha de Proceso", "Hora de Proceso"};
    public DefaultTableModel modelo_Ventas = new DefaultTableModel(null, cabVentas);

    public Pnl_Estadisticas() {
        initComponents();

        cargar_ingresos();
        cargar_ventas();

        modelo_Cliente = (DefaultTableModel) panels.Pnl_Clientes.jTabla_Clientes.getModel();
        Tabla_ingresos.setModel(modelo_Ingresos);
        table_clientes.setModel(modelo_Cliente);
        Tabla_Ventas.setModel(modelo_Ventas);

        estadisticas();
        Columnas();
        Porcentaje_User.setValue((int) ((1 * 100) / 4));
    }

    // Estadisticas
    private void estadisticas() {

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

        // Porcentaje de Efectivo y Tarjetas       
        if (CI.nro_Operaciones() == 0) {
            System.out.println("No hay Operaciones a mostrar");
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

    // Tamaño de Columnas
    private void Columnas() {
        Tabla_ingresos.getColumnModel().getColumn(0).setPreferredWidth(100);
        Tabla_ingresos.getColumnModel().getColumn(0).setResizable(false);
        Tabla_ingresos.getColumnModel().getColumn(1).setPreferredWidth(160);
        Tabla_ingresos.getColumnModel().getColumn(1).setResizable(false);
        Tabla_ingresos.getColumnModel().getColumn(2).setPreferredWidth(50);
        Tabla_ingresos.getColumnModel().getColumn(2).setResizable(false);
        Tabla_ingresos.getColumnModel().getColumn(3).setPreferredWidth(110);
        Tabla_ingresos.getColumnModel().getColumn(3).setResizable(false);
        Tabla_ingresos.getColumnModel().getColumn(4).setPreferredWidth(50);
        Tabla_ingresos.getColumnModel().getColumn(4).setResizable(false);
        Tabla_ingresos.getColumnModel().getColumn(5).setPreferredWidth(50);
        Tabla_ingresos.getColumnModel().getColumn(5).setResizable(false);
    }

    //Cargar Ingresos
    private void cargar_ingresos() {
        modelo_Ingresos.setRowCount(0);
        try {
            PI.leer();
            for (Ingresos_Ventas i : PI.arrayCat) {
                modelo_Ingresos.addRow(i.getInformacionIngresos());
                CI.encolar(i);
            }
        } catch (Exception e) {
        }
    }

    //Cargar Ventas
    private void cargar_ventas() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_color = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSLabelFecha1 = new rojeru_san.rsdate.RSLabelFecha();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnl_stadisticas = new javax.swing.JPanel();
        Porcentaje_Cliente = new rojeru_san.rsprogress.RSProgressCircle();
        rSLabelTextIcon1 = new RSMaterialComponent.RSLabelTextIcon();
        Porcentaje_Productos = new rojeru_san.rsprogress.RSProgressCircle();
        rSLabelTextIcon4 = new RSMaterialComponent.RSLabelTextIcon();
        Porcentaje_User = new rojeru_san.rsprogress.RSProgressCircle();
        rSLabelTextIcon3 = new RSMaterialComponent.RSLabelTextIcon();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btn_ventas = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_ingresos = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        wallpaper_Reportes = new javax.swing.JPanel();
        rSLabelImage1 = new rojeru_san.rslabel.RSLabelImage();
        Panel_Ingresos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_ingresos = new rojerusan.RSTableMetro();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Porce_Efectivo = new rojerusan.componentes.RSProgressBar();
        Porce_Tarjeta = new rojerusan.componentes.RSProgressBar();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        rSButtonMaterialRippleIcon1 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        panel_ventas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_Ventas = new rojerusan.RSTableMetro();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        porcen_boleta = new rojerusan.componentes.RSProgressBar();
        porcen_factura = new rojerusan.componentes.RSProgressBar();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btn_exporComprobantes = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_search = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        panel_Clientes = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_clientes = new rojerusan.RSTableMetro();
        rSButtonMaterialRippleIcon3 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        panel_egresos = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tabla_Emple = new rojerusan.RSTableMetro();
        rSButtonMaterialRippleIcon2 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_egresos = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_clientes = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();

        setName(""); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(740, 629));

        panel_color.setBackground(new java.awt.Color(0, 114, 198));
        panel_color.setForeground(new java.awt.Color(255, 204, 0));

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Estadisticas y Reportes");

        rSLabelFecha1.setForeground(new java.awt.Color(255, 255, 255));

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel_colorLayout = new javax.swing.GroupLayout(panel_color);
        panel_color.setLayout(panel_colorLayout);
        panel_colorLayout.setHorizontalGroup(
            panel_colorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_colorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_colorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelHora1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelFecha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        panel_colorLayout.setVerticalGroup(
            panel_colorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_colorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_colorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_colorLayout.createSequentialGroup()
                        .addComponent(rSLabelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(104, 104, 104));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Licencia Gratuita");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("   :: Estadisticas del Sistema");

        pnl_stadisticas.setBackground(new java.awt.Color(255, 255, 255));

        Porcentaje_Cliente.setValue(0);
        Porcentaje_Cliente.setColorText(new java.awt.Color(102, 102, 102));

        rSLabelTextIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSLabelTextIcon1.setText(" Clientes");

        Porcentaje_Productos.setValue(0);
        Porcentaje_Productos.setColorText(new java.awt.Color(102, 102, 102));

        rSLabelTextIcon4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSLabelTextIcon4.setText("Productos");
        rSLabelTextIcon4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.BORDER_ALL);

        Porcentaje_User.setValue(0);
        Porcentaje_User.setColorText(new java.awt.Color(102, 102, 102));

        rSLabelTextIcon3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rSLabelTextIcon3.setText("Usuarios ");
        rSLabelTextIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FAVORITE_BORDER);
        rSLabelTextIcon3.setInheritsPopupMenu(false);

        javax.swing.GroupLayout pnl_stadisticasLayout = new javax.swing.GroupLayout(pnl_stadisticas);
        pnl_stadisticas.setLayout(pnl_stadisticasLayout);
        pnl_stadisticasLayout.setHorizontalGroup(
            pnl_stadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_stadisticasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_stadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Porcentaje_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnl_stadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Porcentaje_Productos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelTextIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnl_stadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Porcentaje_User, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSLabelTextIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_stadisticasLayout.setVerticalGroup(
            pnl_stadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_stadisticasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnl_stadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_stadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_stadisticasLayout.createSequentialGroup()
                            .addComponent(Porcentaje_Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rSLabelTextIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnl_stadisticasLayout.createSequentialGroup()
                            .addComponent(Porcentaje_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(rSLabelTextIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnl_stadisticasLayout.createSequentialGroup()
                        .addComponent(Porcentaje_Productos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSLabelTextIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("   :: Reportes:");

        btn_ventas.setText(" Ventas ");
        btn_ventas.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PIE_CHART);
        btn_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ventasActionPerformed(evt);
            }
        });

        btn_ingresos.setText(" Ingresos");
        btn_ingresos.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PAYMENT);
        btn_ingresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingresosActionPerformed(evt);
            }
        });

        wallpaper_Reportes.setBackground(new java.awt.Color(255, 255, 255));

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pngtree-technology-future-office-business-man-image_8014.png"))); // NOI18N

        javax.swing.GroupLayout wallpaper_ReportesLayout = new javax.swing.GroupLayout(wallpaper_Reportes);
        wallpaper_Reportes.setLayout(wallpaper_ReportesLayout);
        wallpaper_ReportesLayout.setHorizontalGroup(
            wallpaper_ReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wallpaper_ReportesLayout.createSequentialGroup()
                .addContainerGap(568, Short.MAX_VALUE)
                .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        wallpaper_ReportesLayout.setVerticalGroup(
            wallpaper_ReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wallpaper_ReportesLayout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        rSPanelsSlider1.add(wallpaper_Reportes, "card2");

        Panel_Ingresos.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBackground(new java.awt.Color(0, 114, 198));

        Tabla_ingresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro. Operacion", "Medio de pago", "Importe", "Cupon Aplicado", "Descuento", "Total", "Registrado por", "Fecha del Proceso", "Hora del Proceso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_ingresos.setColorBorderRows(new java.awt.Color(153, 153, 153));
        Tabla_ingresos.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla_ingresos.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla_ingresos.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla_ingresos.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        Tabla_ingresos.setFontHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 11)); // NOI18N
        Tabla_ingresos.setWidthBorderHead(0);
        Tabla_ingresos.setWidthBorderRows(0);
        jScrollPane3.setViewportView(Tabla_ingresos);
        if (Tabla_ingresos.getColumnModel().getColumnCount() > 0) {
            Tabla_ingresos.getColumnModel().getColumn(0).setResizable(false);
            Tabla_ingresos.getColumnModel().getColumn(1).setResizable(false);
            Tabla_ingresos.getColumnModel().getColumn(2).setResizable(false);
            Tabla_ingresos.getColumnModel().getColumn(3).setResizable(false);
            Tabla_ingresos.getColumnModel().getColumn(4).setResizable(false);
            Tabla_ingresos.getColumnModel().getColumn(5).setResizable(false);
            Tabla_ingresos.getColumnModel().getColumn(6).setResizable(false);
            Tabla_ingresos.getColumnModel().getColumn(7).setResizable(false);
            Tabla_ingresos.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Pago en efectivo:");

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tarjeta de Credito y/o Debito:");

        Porce_Efectivo.setValue(0);
        Porce_Efectivo.setColorBorde(new java.awt.Color(102, 102, 102));
        Porce_Efectivo.setFont(new java.awt.Font("Roboto Bold", 1, 10)); // NOI18N

        Porce_Tarjeta.setValue(0);
        Porce_Tarjeta.setColorBorde(new java.awt.Color(102, 102, 102));
        Porce_Tarjeta.setFont(new java.awt.Font("Roboto Bold", 1, 10)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Información detallada");

        jLabel16.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Resumen");

        rSButtonMaterialRippleIcon1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonMaterialRippleIcon1.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMaterialRippleIcon1.setText("Exporta a excel");
        rSButtonMaterialRippleIcon1.setBackgroundHover(new java.awt.Color(51, 196, 129));
        rSButtonMaterialRippleIcon1.setForegroundIcon(new java.awt.Color(0, 0, 0));
        rSButtonMaterialRippleIcon1.setForegroundText(new java.awt.Color(0, 0, 0));
        rSButtonMaterialRippleIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_BOX);
        rSButtonMaterialRippleIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_IngresosLayout = new javax.swing.GroupLayout(Panel_Ingresos);
        Panel_Ingresos.setLayout(Panel_IngresosLayout);
        Panel_IngresosLayout.setHorizontalGroup(
            Panel_IngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_IngresosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_IngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Panel_IngresosLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                        .addGap(207, 207, 207))
                    .addComponent(jSeparator4)
                    .addGroup(Panel_IngresosLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Porce_Efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Porce_Tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSButtonMaterialRippleIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addComponent(jSeparator5))
                .addContainerGap())
        );
        Panel_IngresosLayout.setVerticalGroup(
            Panel_IngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_IngresosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_IngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_IngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(Porce_Efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_IngresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(Porce_Tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSButtonMaterialRippleIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSPanelsSlider1.add(Panel_Ingresos, "card3");

        panel_ventas.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(0, 112, 192));

        Tabla_Ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo de Venta", "Nro de Venta", "Importe", "Medio de pago", "Registrado por", "Fecha del Proceso", "Hora del Proceso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Ventas.setColorBorderRows(new java.awt.Color(153, 153, 153));
        Tabla_Ventas.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla_Ventas.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla_Ventas.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla_Ventas.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        Tabla_Ventas.setFontHead(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Tabla_Ventas.setWidthBorderHead(0);
        Tabla_Ventas.setWidthBorderRows(0);
        jScrollPane2.setViewportView(Tabla_Ventas);
        if (Tabla_Ventas.getColumnModel().getColumnCount() > 0) {
            Tabla_Ventas.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Ventas.getColumnModel().getColumn(1).setResizable(false);
            Tabla_Ventas.getColumnModel().getColumn(2).setResizable(false);
            Tabla_Ventas.getColumnModel().getColumn(3).setResizable(false);
            Tabla_Ventas.getColumnModel().getColumn(4).setResizable(false);
            Tabla_Ventas.getColumnModel().getColumn(5).setResizable(false);
            Tabla_Ventas.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Boleta  :");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Factura:");

        porcen_boleta.setValue(0);
        porcen_boleta.setColorBorde(new java.awt.Color(102, 102, 102));
        porcen_boleta.setFont(new java.awt.Font("Roboto Bold", 1, 10)); // NOI18N

        porcen_factura.setValue(0);
        porcen_factura.setColorBorde(new java.awt.Color(102, 102, 102));
        porcen_factura.setFont(new java.awt.Font("Roboto Bold", 1, 10)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Información detallada");

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Resumen");

        btn_exporComprobantes.setBackground(new java.awt.Color(255, 255, 255));
        btn_exporComprobantes.setForeground(new java.awt.Color(0, 0, 0));
        btn_exporComprobantes.setText("Exporta a excel");
        btn_exporComprobantes.setBackgroundHover(new java.awt.Color(51, 196, 129));
        btn_exporComprobantes.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btn_exporComprobantes.setForegroundText(new java.awt.Color(0, 0, 0));
        btn_exporComprobantes.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_BOX);
        btn_exporComprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exporComprobantesActionPerformed(evt);
            }
        });

        btn_search.setBackground(new java.awt.Color(255, 255, 255));
        btn_search.setForeground(new java.awt.Color(0, 0, 0));
        btn_search.setText("Consultar ");
        btn_search.setBackgroundHover(new java.awt.Color(0, 112, 192));
        btn_search.setForegroundIcon(new java.awt.Color(0, 0, 0));
        btn_search.setForegroundText(new java.awt.Color(0, 0, 0));
        btn_search.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ventasLayout = new javax.swing.GroupLayout(panel_ventas);
        panel_ventas.setLayout(panel_ventasLayout);
        panel_ventasLayout.setHorizontalGroup(
            panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ventasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addGroup(panel_ventasLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porcen_boleta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(porcen_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_exporComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel_ventasLayout.setVerticalGroup(
            panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ventasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(porcen_boleta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(porcen_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_exporComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSPanelsSlider1.add(panel_ventas, "card4");

        panel_Clientes.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Resumen");

        table_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table_clientes.setColorBorderRows(new java.awt.Color(153, 153, 153));
        table_clientes.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        table_clientes.setColorSecondary(new java.awt.Color(255, 255, 255));
        table_clientes.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        table_clientes.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        table_clientes.setFontHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 11)); // NOI18N
        table_clientes.setWidthBorderHead(0);
        table_clientes.setWidthBorderRows(0);
        jScrollPane5.setViewportView(table_clientes);

        rSButtonMaterialRippleIcon3.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonMaterialRippleIcon3.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMaterialRippleIcon3.setText("Exporta a excel");
        rSButtonMaterialRippleIcon3.setBackgroundHover(new java.awt.Color(51, 196, 129));
        rSButtonMaterialRippleIcon3.setForegroundIcon(new java.awt.Color(0, 0, 0));
        rSButtonMaterialRippleIcon3.setForegroundText(new java.awt.Color(0, 0, 0));
        rSButtonMaterialRippleIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_BOX);
        rSButtonMaterialRippleIcon3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_ClientesLayout = new javax.swing.GroupLayout(panel_Clientes);
        panel_Clientes.setLayout(panel_ClientesLayout);
        panel_ClientesLayout.setHorizontalGroup(
            panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_ClientesLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMaterialRippleIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator7)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        panel_ClientesLayout.setVerticalGroup(
            panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_ClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rSButtonMaterialRippleIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(5, 5, 5)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSPanelsSlider1.add(panel_Clientes, "card4");

        panel_egresos.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Resumen");

        jScrollPane4.setBackground(new java.awt.Color(0, 114, 198));

        Tabla_Emple.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro. Operacion", "Medio de pago", "Importe", "Cupon Aplicado", "Descuento", "Total", "Registrado por", "Fecha del Proceso", "Hora del Proceso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Emple.setColorBorderRows(new java.awt.Color(153, 153, 153));
        Tabla_Emple.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla_Emple.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla_Emple.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla_Emple.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        Tabla_Emple.setFontHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 11)); // NOI18N
        Tabla_Emple.setWidthBorderHead(0);
        Tabla_Emple.setWidthBorderRows(0);
        jScrollPane4.setViewportView(Tabla_Emple);
        if (Tabla_Emple.getColumnModel().getColumnCount() > 0) {
            Tabla_Emple.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Emple.getColumnModel().getColumn(1).setResizable(false);
            Tabla_Emple.getColumnModel().getColumn(2).setResizable(false);
            Tabla_Emple.getColumnModel().getColumn(3).setResizable(false);
            Tabla_Emple.getColumnModel().getColumn(4).setResizable(false);
            Tabla_Emple.getColumnModel().getColumn(5).setResizable(false);
            Tabla_Emple.getColumnModel().getColumn(6).setResizable(false);
            Tabla_Emple.getColumnModel().getColumn(7).setResizable(false);
            Tabla_Emple.getColumnModel().getColumn(8).setResizable(false);
        }

        rSButtonMaterialRippleIcon2.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonMaterialRippleIcon2.setForeground(new java.awt.Color(0, 0, 0));
        rSButtonMaterialRippleIcon2.setText("Exporta a excel");
        rSButtonMaterialRippleIcon2.setBackgroundHover(new java.awt.Color(51, 196, 129));
        rSButtonMaterialRippleIcon2.setForegroundIcon(new java.awt.Color(0, 0, 0));
        rSButtonMaterialRippleIcon2.setForegroundText(new java.awt.Color(0, 0, 0));
        rSButtonMaterialRippleIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD_BOX);
        rSButtonMaterialRippleIcon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_egresosLayout = new javax.swing.GroupLayout(panel_egresos);
        panel_egresos.setLayout(panel_egresosLayout);
        panel_egresosLayout.setHorizontalGroup(
            panel_egresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_egresosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_egresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator9)
                    .addGroup(panel_egresosLayout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSButtonMaterialRippleIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel_egresosLayout.setVerticalGroup(
            panel_egresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_egresosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panel_egresosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(rSButtonMaterialRippleIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSPanelsSlider1.add(panel_egresos, "card4");

        btn_egresos.setText(" Personal");
        btn_egresos.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PEOPLE);
        btn_egresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_egresosActionPerformed(evt);
            }
        });

        btn_clientes.setText(" Clientes");
        btn_clientes.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PEOPLE_OUTLINE);
        btn_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator10)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ingresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_egresos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_color, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnl_stadisticas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_stadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ingresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingresosActionPerformed
        wallpaper_Reportes.setVisible(false);
        Panel_Ingresos.setVisible(true);
        panel_ventas.setVisible(false);
        panel_Clientes.setVisible(false);
        panel_egresos.setVisible(false);
    }//GEN-LAST:event_btn_ingresosActionPerformed

    private void btn_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ventasActionPerformed
        wallpaper_Reportes.setVisible(false);
        Panel_Ingresos.setVisible(false);
        panel_ventas.setVisible(true);
        panel_Clientes.setVisible(false);
        panel_egresos.setVisible(false);
    }//GEN-LAST:event_btn_ventasActionPerformed

    private void btn_egresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_egresosActionPerformed
        wallpaper_Reportes.setVisible(false);
        Panel_Ingresos.setVisible(false);
        panel_ventas.setVisible(false);
        panel_Clientes.setVisible(false);
        panel_egresos.setVisible(true);
    }//GEN-LAST:event_btn_egresosActionPerformed

    private void btn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesActionPerformed
        wallpaper_Reportes.setVisible(false);
        Panel_Ingresos.setVisible(false);
        panel_ventas.setVisible(false);
        panel_Clientes.setVisible(true);
        panel_egresos.setVisible(false);
    }//GEN-LAST:event_btn_clientesActionPerformed

    private void rSButtonMaterialRippleIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon1ActionPerformed
        try {
            obj = new clsExportarExcel();
            obj.exportarExcel(Tabla_ingresos, "Reporte de Ingresos ");
        } catch (IOException ex) {
            System.out.println("Error al exportar los Ingresos a Excel");
        }
    }//GEN-LAST:event_rSButtonMaterialRippleIcon1ActionPerformed

    private void btn_exporComprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exporComprobantesActionPerformed
        try {
            obj = new clsExportarExcel();
            obj.exportarExcel(Tabla_Ventas, "Reporte de Comprobantes");
        } catch (IOException ex) {
            System.out.println("Error al exportar los Ingresos a Excel");
        }
    }//GEN-LAST:event_btn_exporComprobantesActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        cargar_ventas();
        if (CV.nro_ventas() == 0) {
            Message_confirmacion.Message = "<html>No hay comprobantes registrador en la base de datos</html>";
            Message_confirmacion.tipo_message = "advertencia";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        } else {
            new Busqueda_Comprobante(new javax.swing.JFrame(), true).setVisible(true);
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void rSButtonMaterialRippleIcon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon2ActionPerformed
        try {
            obj = new clsExportarExcel();
            obj.exportarExcel(Tabla_Emple, "Reporte de Empleados");
        } catch (IOException ex) {
            System.out.println("Error al exportar los Ingresos a Excel");
        }
    }//GEN-LAST:event_rSButtonMaterialRippleIcon2ActionPerformed

    private void rSButtonMaterialRippleIcon3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon3ActionPerformed
        try {
            obj = new clsExportarExcel();
            obj.exportarExcel(table_clientes, "Reporte de Clientes");
        } catch (IOException ex) {
            System.out.println("Error al exportar los Ingresos a Excel");
        }
    }//GEN-LAST:event_rSButtonMaterialRippleIcon3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Ingresos;
    public static rojerusan.componentes.RSProgressBar Porce_Efectivo;
    public static rojerusan.componentes.RSProgressBar Porce_Tarjeta;
    public static rojeru_san.rsprogress.RSProgressCircle Porcentaje_Cliente;
    public static rojeru_san.rsprogress.RSProgressCircle Porcentaje_Productos;
    private rojeru_san.rsprogress.RSProgressCircle Porcentaje_User;
    public static rojerusan.RSTableMetro Tabla_Emple;
    public static rojerusan.RSTableMetro Tabla_Ventas;
    public static rojerusan.RSTableMetro Tabla_ingresos;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_clientes;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_egresos;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_exporComprobantes;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_ingresos;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_search;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_ventas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel panel_Clientes;
    public static javax.swing.JPanel panel_color;
    private javax.swing.JPanel panel_egresos;
    private javax.swing.JPanel panel_ventas;
    private javax.swing.JPanel pnl_stadisticas;
    public static rojerusan.componentes.RSProgressBar porcen_boleta;
    public static rojerusan.componentes.RSProgressBar porcen_factura;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon1;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon2;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon3;
    private rojeru_san.rsdate.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon1;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon3;
    private RSMaterialComponent.RSLabelTextIcon rSLabelTextIcon4;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    public static rojerusan.RSTableMetro table_clientes;
    private javax.swing.JPanel wallpaper_Reportes;
    // End of variables declaration//GEN-END:variables

}
