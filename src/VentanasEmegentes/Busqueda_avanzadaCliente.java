package VentanasEmegentes;

import com.sun.awt.AWTUtilities;
import controlador.ListaCircular_Clientes;
import controlador.Process_Clientes;
import javax.swing.JOptionPane;
import modelo.Clientes;

public class Busqueda_avanzadaCliente extends javax.swing.JDialog {

    Clientes client;
    Process_Clientes processClient = new Process_Clientes();
    ListaCircular_Clientes listCircClient = new ListaCircular_Clientes();

    public Busqueda_avanzadaCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        AWTUtilities.setWindowOpaque(this, false);
        pnl_detalles.setVisible(false);
        CargarClientes();
    }

    // Cargar Clientes
    private void CargarClientes() {
        try {
            processClient.leer();
            for (Clientes inve : processClient.lista) {
                listCircClient.insertar(inve);
            }
        } catch (Exception e) {
            System.out.println("error al Cargar el inventario : " + e);
        }
    }
    
    // Limpieza
    private void limpieza() {
        txt_dniCliente1.setText(null);
        txt_TdniCliente.setText(null);
        txt_nombreCliente1.setText(null);
        txt_ApellidoCliente1.setText(null);
        txt_CelularCliente1.setText(null);
        txt_DireccionCliente1.setText(null);
        txt_emailCliente1.setText(null);
        txt_SexoCliente.setText(null);
        Fecha_Modificacion.setText(null);
        Hora_Modificacion.setText(null);
        Register_por.setText(null);
    }

    // Busqueda
    private void search() {
        client = listCircClient.buscar(txt_dniCliente1.getText());
        if (client != null) {
            txt_TdniCliente.setText(client.getTip_Docu());
            txt_nombreCliente1.setText(client.getNombres());
            txt_ApellidoCliente1.setText(client.getApellidos());
            txt_CelularCliente1.setText(String.valueOf(client.getCelular()));
            txt_DireccionCliente1.setText(client.getDireccion());
            txt_emailCliente1.setText(client.getEmail());
            txt_SexoCliente.setText(client.getSexo());
            Fecha_Modificacion.setText(client.getFecha_Reg());
            Hora_Modificacion.setText(client.getHora_Reg());
            Register_por.setText(client.getUsuario());
        } else {
            JOptionPane.showMessageDialog(null, "El Nro de Documento: " + txt_dniCliente1.getText() + ". No existe");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title_cliente = new javax.swing.JLabel();
        close = new javax.swing.JButton();
        pnl_detalles = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        Fecha_Modificacion = new javax.swing.JLabel();
        Hora_Modificacion = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        Register_por = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        rSButtonIconUno1 = new RSMaterialComponent.RSButtonIconUno();
        rSButtonIconUno3 = new RSMaterialComponent.RSButtonIconUno();
        jLabel32 = new javax.swing.JLabel();
        txt_dniCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel33 = new javax.swing.JLabel();
        txt_nombreCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel34 = new javax.swing.JLabel();
        txt_SexoCliente = new RSMaterialComponent.RSTextFieldTwo();
        txt_DireccionCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel35 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txt_CelularCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        txt_ApellidoCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel38 = new javax.swing.JLabel();
        txt_emailCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        txt_TdniCliente = new RSMaterialComponent.RSTextFieldTwo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 55));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        title_cliente.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        title_cliente.setForeground(new java.awt.Color(255, 255, 255));
        title_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_cliente.setText("Consulta de Clientes");

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

        pnl_detalles.setBackground(new java.awt.Color(255, 255, 255));

        jLabel40.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Fecha de Modificación: ");

        Fecha_Modificacion.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        Fecha_Modificacion.setForeground(new java.awt.Color(51, 51, 51));
        Fecha_Modificacion.setText("00-00-0000");

        Hora_Modificacion.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        Hora_Modificacion.setForeground(new java.awt.Color(51, 51, 51));
        Hora_Modificacion.setText("00:00:00.000");

        jLabel42.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Hora de Modificación: ");

        jLabel41.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("Registrado Por : ");

        Register_por.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        Register_por.setForeground(new java.awt.Color(51, 51, 51));
        Register_por.setText("_user_");

        javax.swing.GroupLayout pnl_detallesLayout = new javax.swing.GroupLayout(pnl_detalles);
        pnl_detalles.setLayout(pnl_detallesLayout);
        pnl_detallesLayout.setHorizontalGroup(
            pnl_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_detallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Fecha_Modificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Hora_Modificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Register_por, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_detallesLayout.setVerticalGroup(
            pnl_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_detallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fecha_Modificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Hora_Modificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_detallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Register_por, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel39.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Sexo:");

        rSButtonIconUno1.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconUno1.setForegroundText(new java.awt.Color(0, 0, 0));
        rSButtonIconUno1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSButtonIconUno1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconUno1ActionPerformed(evt);
            }
        });

        rSButtonIconUno3.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconUno3.setForegroundText(new java.awt.Color(0, 0, 0));
        rSButtonIconUno3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR_ALL);
        rSButtonIconUno3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconUno3ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Nro de Documento : ");

        txt_dniCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_dniCliente1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_dniCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_dniCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_dniCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_dniCliente1.setPlaceholder("Ingrese Documento");

        jLabel33.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Nombres:  ");

        txt_nombreCliente1.setEditable(false);
        txt_nombreCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_nombreCliente1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_nombreCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_nombreCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente1.setPlaceholder(" Nombres Completos");

        jLabel34.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Domicilio :");

        txt_SexoCliente.setEditable(false);
        txt_SexoCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_SexoCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_SexoCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_SexoCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_SexoCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_SexoCliente.setPlaceholder("Masculino / Femenino");

        txt_DireccionCliente1.setEditable(false);
        txt_DireccionCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_DireccionCliente1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_DireccionCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_DireccionCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente1.setPlaceholder("Calle, Urbanizacion, AA.HH, Provincia");

        jLabel35.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Número de Celular: ");

        jLabel43.setBackground(new java.awt.Color(255, 255, 255));
        jLabel43.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(153, 153, 153));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("mostrar detalles");
        jLabel43.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel43MouseClicked(evt);
            }
        });

        txt_CelularCliente1.setEditable(false);
        txt_CelularCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_CelularCliente1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_CelularCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_CelularCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente1.setPlaceholder("987654321");

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));

        jLabel36.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Tipo de Documento: ");

        jSeparator2.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator2.setForeground(new java.awt.Color(153, 153, 153));

        jLabel37.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Apellidos:  ");

        txt_ApellidoCliente1.setEditable(false);
        txt_ApellidoCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_ApellidoCliente1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_ApellidoCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_ApellidoCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente1.setPlaceholder(" Apellidos Completos");

        jLabel38.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Correo Electronico :");

        txt_emailCliente1.setEditable(false);
        txt_emailCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_emailCliente1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_emailCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_emailCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente1.setPlaceholder("e-mail@example.com");

        txt_TdniCliente.setEditable(false);
        txt_TdniCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_TdniCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_TdniCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_TdniCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_TdniCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_TdniCliente.setPlaceholder("C.E / D.N.I");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_SexoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_dniCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSButtonIconUno1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSButtonIconUno3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_emailCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CelularCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TdniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nombreCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ApellidoCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_DireccionCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dniCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSButtonIconUno1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonIconUno3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TdniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombreCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ApellidoCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_DireccionCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_emailCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_CelularCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SexoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnl_detalles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_detalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        limpieza();
        dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void rSButtonIconUno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno1ActionPerformed
        search();
    }//GEN-LAST:event_rSButtonIconUno1ActionPerformed

    private void rSButtonIconUno3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno3ActionPerformed
        limpieza();
    }//GEN-LAST:event_rSButtonIconUno3ActionPerformed

    int ocultar = 0;
    private void jLabel43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel43MouseClicked
        if (ocultar == 0) {
            pnl_detalles.setVisible(true);
            jLabel43.setText("ocultar detalles");
            ocultar = 1;
        } else {
            pnl_detalles.setVisible(false);
            jLabel43.setText("mostrar detalles");
            ocultar = 0;
        }
    }//GEN-LAST:event_jLabel43MouseClicked

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
            java.util.logging.Logger.getLogger(Busqueda_avanzadaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Busqueda_avanzadaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Busqueda_avanzadaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Busqueda_avanzadaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Busqueda_avanzadaCliente dialog = new Busqueda_avanzadaCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Fecha_Modificacion;
    private javax.swing.JLabel Hora_Modificacion;
    private javax.swing.JLabel Register_por;
    private javax.swing.JButton close;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel pnl_detalles;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno1;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno3;
    public javax.swing.JLabel title_cliente;
    private RSMaterialComponent.RSTextFieldTwo txt_ApellidoCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_CelularCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_DireccionCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_SexoCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_TdniCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_dniCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_emailCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_nombreCliente1;
    // End of variables declaration//GEN-END:variables
}
