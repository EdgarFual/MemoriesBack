package VentanasEmegentes;

import controlador.ListaCircular_Clientes;
import controlador.Process_Clientes;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Clientes;
import panels.Pnl_Clientes;
import panels.Pnl_home;
import views.Ventana_Administrador;

public class RegistroClientes extends javax.swing.JDialog {

    String Tipo_Documento;
    LocalDate FechaActual = LocalDate.now();
    LocalTime horaActual = LocalTime.now();

    Clientes clien;
    Process_Clientes processClien = new Process_Clientes();
    ListaCircular_Clientes listCircClien = new ListaCircular_Clientes();

    String[] cab = {"Documento", "Nro de Documento", "Nombres", "Apellidos", "Sexo", "Celular", "E-mail", "Registrado el", "Registrado por"};
    public DefaultTableModel modelo = new DefaultTableModel(null, cab);

    public RegistroClientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        CargarClientes();
        modelo = (DefaultTableModel) panels.Pnl_Clientes.jTabla_Clientes.getModel();
        TD();
        info();
    }

    // Cargar Clientes
    private void CargarClientes() {
        try {
            processClien.leer();
            for (Clientes inve : processClien.lista) {
                listCircClien.insertar(inve);
                //modelo.addRow(clien.getInformacionClientes());
            }
        } catch (Exception e) {
            System.out.println("error al Cargar el inventario : " + e);
        }
    }

    // Seleccionando tipo de Documento
    private void TD() {
        if (RB_DNI.isSelected()) {
            Tipo_Documento = "D.N.I";
        }
        if (RB_ce.isSelected()) {
            Tipo_Documento = "C.E";
        }

        if (RB_Ruc.isSelected()) {
            Tipo_Documento = "RUC";

        }
    }

    // Limpieza
    private void limpieza() {
        txt_dniCliente.setText(null);
        txt_nombreCliente.setText(null);
        txt_ApellidoCliente.setText(null);
        txt_CelularCliente.setText(null);
        txt_DireccionCliente.setText(null);
        txt_emailCliente.setText(null);
        jcomboSexo.setSelectedIndex(0);
    }

    // Actualizando Informacion
    private void info() {
        Pnl_home.nro_clientes.setText(String.valueOf(listCircClien.Longitud()));
        Pnl_Clientes.jLabel4.setText("Total : " + listCircClien.Longitud() + " Registrados");
        Ventana_Administrador.pocentajeCliente = ((int) ((listCircClien.Longitud() * 100) / 50));
    }

    // Listar en Tabla
    public void listar() {
        modelo.setRowCount(0);
        for (Clientes cl : listCircClien.listado()) {
            modelo.addRow(cl.getInformacionClientes());
        }
        panels.Pnl_Clientes.jTabla_Clientes.setModel(modelo);
    }

    // REGISTRAR
    private void Registrar() {

        Object[] arreglo = {Tipo_Documento, txt_dniCliente.getText(), txt_nombreCliente.getText(), txt_ApellidoCliente.getText(),
            jcomboSexo.getSelectedItem().toString(), txt_CelularCliente.getText(), txt_emailCliente.getText(), txt_DireccionCliente.getText(),
            Ventana_Administrador.user, FechaActual, horaActual};
        clien = new Clientes(arreglo);
        listCircClien.insertar(clien);
        try {
            processClien.insertar(clien);
            // new Pnl_Clientes().listar();
            listar();
            info();
            limpieza();
            dispose();
        } catch (Exception e) {
            System.out.println("Error al registrar");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipo_documeto = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title_cliente = new javax.swing.JLabel();
        close = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        rSButtonIconUno3 = new RSMaterialComponent.RSButtonIconUno();
        jLabel16 = new javax.swing.JLabel();
        RB_DNI = new javax.swing.JRadioButton();
        RB_Ruc = new javax.swing.JRadioButton();
        RB_ce = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txt_dniCliente = new RSMaterialComponent.RSTextFieldTwo();
        jLabel12 = new javax.swing.JLabel();
        txt_nombreCliente = new RSMaterialComponent.RSTextFieldTwo();
        jLabel13 = new javax.swing.JLabel();
        txt_ApellidoCliente = new RSMaterialComponent.RSTextFieldTwo();
        jLabel42 = new javax.swing.JLabel();
        jcomboSexo = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel15 = new javax.swing.JLabel();
        txt_CelularCliente = new RSMaterialComponent.RSTextFieldTwo();
        jLabel14 = new javax.swing.JLabel();
        txt_DireccionCliente = new RSMaterialComponent.RSTextFieldTwo();
        jLabel30 = new javax.swing.JLabel();
        txt_emailCliente = new RSMaterialComponent.RSTextFieldTwo();
        btn_RegisterClient = new RSMaterialComponent.RSButtonMaterialRippleIcon();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        title_cliente.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        title_cliente.setForeground(new java.awt.Color(255, 255, 255));
        title_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_cliente.setText("Registrar Cliente");

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
                .addComponent(title_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        close.setBackground(new java.awt.Color(165, 29, 34));
        close.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        close.setForeground(new java.awt.Color(255, 255, 255));
        close.setText("cerrar");
        close.setBorderPainted(false);
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        rSButtonIconUno3.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconUno3.setForegroundText(new java.awt.Color(0, 0, 0));
        rSButtonIconUno3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR_ALL);
        rSButtonIconUno3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconUno3ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Tipo de Documento: ");

        RB_DNI.setBackground(new java.awt.Color(255, 255, 255));
        tipo_documeto.add(RB_DNI);
        RB_DNI.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        RB_DNI.setForeground(new java.awt.Color(0, 0, 0));
        RB_DNI.setSelected(true);
        RB_DNI.setText("D.N.I");
        RB_DNI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RB_DNIMouseClicked(evt);
            }
        });

        RB_Ruc.setBackground(new java.awt.Color(255, 255, 255));
        tipo_documeto.add(RB_Ruc);
        RB_Ruc.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        RB_Ruc.setForeground(new java.awt.Color(0, 0, 0));
        RB_Ruc.setText("RUC");
        RB_Ruc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RB_RucMouseClicked(evt);
            }
        });

        RB_ce.setBackground(new java.awt.Color(255, 255, 255));
        tipo_documeto.add(RB_ce);
        RB_ce.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        RB_ce.setForeground(new java.awt.Color(0, 0, 0));
        RB_ce.setText(" C.E");
        RB_ce.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RB_ceMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Nro de Documento : ");

        txt_dniCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_dniCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_dniCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_dniCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_dniCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_dniCliente.setPlaceholder("ejem: 99999999");

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Nombres:  ");

        txt_nombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_nombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombreCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_nombreCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente.setPlaceholder(" Ingrese Nombres Completos");

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Apellidos:  ");

        txt_ApellidoCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_ApellidoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ApellidoCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_ApellidoCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente.setPlaceholder(" Ingrese Apellidos Completos");

        jLabel42.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Sexo:");

        jcomboSexo.setForeground(new java.awt.Color(0, 0, 0));
        jcomboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Masculino", "Femenino" }));
        jcomboSexo.setFocusable(false);
        jcomboSexo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Nro de Celular: ");

        txt_CelularCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_CelularCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_CelularCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_CelularCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente.setPlaceholder("987654321");

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Domicilio :");

        txt_DireccionCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_DireccionCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_DireccionCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_DireccionCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente.setPlaceholder("Calle, Urbanizacion, AA.HH, Provincia");

        jLabel30.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Correo Electronico :");

        txt_emailCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_emailCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_emailCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_emailCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente.setPlaceholder("e-mail@example.com");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(RB_ce, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(RB_DNI)
                        .addGap(4, 4, 4)
                        .addComponent(RB_Ruc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSButtonIconUno3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txt_dniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txt_nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txt_ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jcomboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txt_CelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txt_DireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(txt_emailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RB_ce)
                    .addComponent(RB_DNI)
                    .addComponent(RB_Ruc)
                    .addComponent(rSButtonIconUno3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_dniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcomboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_CelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_DireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt_emailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        btn_RegisterClient.setText("Registrar Cliente");
        btn_RegisterClient.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btn_RegisterClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterClientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_RegisterClient, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_RegisterClient, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(close))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        limpieza();
        dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void rSButtonIconUno3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno3ActionPerformed
        limpieza();
    }//GEN-LAST:event_rSButtonIconUno3ActionPerformed

    private void RB_ceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RB_ceMouseClicked
        TD();
    }//GEN-LAST:event_RB_ceMouseClicked

    private void RB_DNIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RB_DNIMouseClicked
        TD();
    }//GEN-LAST:event_RB_DNIMouseClicked

    private void btn_RegisterClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterClientActionPerformed
        try {
            if (listCircClien.Longitud() == 0) {
                Registrar();
            } else {
                clien = listCircClien.buscar(txt_dniCliente.getText());
                if (clien != null) {
                    JOptionPane.showMessageDialog(null, " El D.N.I " + txt_dniCliente.getText() + " ya esta registrado");
                } else {
                    Registrar();
                }
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un error al registrar " + e);
        }
    }//GEN-LAST:event_btn_RegisterClientActionPerformed

    private void RB_RucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RB_RucMouseClicked
        TD();
    }//GEN-LAST:event_RB_RucMouseClicked

    int ocultar = 0;

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
            java.util.logging.Logger.getLogger(RegistroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegistroClientes dialog = new RegistroClientes(new javax.swing.JFrame(), true);
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
    private javax.swing.JRadioButton RB_DNI;
    private javax.swing.JRadioButton RB_Ruc;
    private javax.swing.JRadioButton RB_ce;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_RegisterClient;
    private javax.swing.JButton close;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private RSMaterialComponent.RSComboBoxMaterial jcomboSexo;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno3;
    private javax.swing.ButtonGroup tipo_documeto;
    public javax.swing.JLabel title_cliente;
    private RSMaterialComponent.RSTextFieldTwo txt_ApellidoCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_CelularCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_DireccionCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_dniCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_emailCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_nombreCliente;
    // End of variables declaration//GEN-END:variables
}
