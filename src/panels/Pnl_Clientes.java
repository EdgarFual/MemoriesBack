package panels;

import VentanasEmegentes.Busqueda_avanzadaCliente;
import controlador.ListaCircular_Clientes;
import controlador.Process_Clientes;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.table.DefaultTableModel;
import message.Message_confirmacion;
import modelo.Clientes;
import views.Ventana_Administrador;

public class Pnl_Clientes extends javax.swing.JPanel {

    public static int se = 0;

    int mostrar = 1;
    int por1, por2, por3, por4, por5, por6, por7, por8;

    boolean a = true;

    String Tipo_Documento;
    LocalDate FechaActual = LocalDate.now();
    LocalTime horaActual = LocalTime.now();

    Clientes client;
    Process_Clientes processClient = new Process_Clientes();
    ListaCircular_Clientes listCircClient = new ListaCircular_Clientes();

    String[] cab = {"Documento", "Nro de Documento", "Nombres", "Apellidos", "Sexo", "Celular", "E-mail", "Registrado el", "Registrado por"};
    public DefaultTableModel modeloClientes = new DefaultTableModel(null, cab);

    public Pnl_Clientes() {
        initComponents();
        jTabla_Clientes.setModel(modeloClientes);
        registrado.setVisible(false);
        btn_RegisterClient.setEnabled(false);
        Columnas();
        cargar();
        TD();
        info();
        Progreso();
    }

    // Información
    private void info() {
        Pnl_home.nro_clientes.setText(String.valueOf(listCircClient.Longitud()));
        Pnl_Clientes.jLabel4.setText("Total : " + listCircClient.Longitud() + " Registrados");
        Ventana_Administrador.pocentajeCliente = ((int) ((listCircClient.Longitud() * 100) / 50));
    }

    private void Progreso() {
        jProgressBar1.setValue(por1 + por2 + por3 + por4 + por5 + por6 + por7 + por8);
        if (jProgressBar1.getValue() == 100) {
            btn_RegisterClient.setEnabled(true);
        } else {
            btn_RegisterClient.setEnabled(false);
        }
    }

    // Seleccionando tipo de Documento
    private void TD() {
        if (RB_DNI.isSelected()) {
            Tipo_Documento = "D.N.I";
            por1 = 13;
        }
        if (RB_ce.isSelected()) {
            Tipo_Documento = "C.E";
            por1 = 13;
        }

        if (RB_Ruc.isSelected()) {
            Tipo_Documento = "RUC";
            por1 = 13;
        }
        Progreso();
    }

    // Tamaño de Columnas
    private void Columnas() {
        jTabla_Clientes.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTabla_Clientes.getColumnModel().getColumn(0).setResizable(false);
        jTabla_Clientes.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTabla_Clientes.getColumnModel().getColumn(1).setResizable(false);
        jTabla_Clientes.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTabla_Clientes.getColumnModel().getColumn(2).setResizable(false);
        jTabla_Clientes.getColumnModel().getColumn(3).setPreferredWidth(150);
        jTabla_Clientes.getColumnModel().getColumn(3).setResizable(false);
        jTabla_Clientes.getColumnModel().getColumn(4).setPreferredWidth(40);
        jTabla_Clientes.getColumnModel().getColumn(4).setResizable(false);
        jTabla_Clientes.getColumnModel().getColumn(5).setPreferredWidth(45);
        jTabla_Clientes.getColumnModel().getColumn(5).setResizable(false);
        jTabla_Clientes.getColumnModel().getColumn(6).setPreferredWidth(190);
        jTabla_Clientes.getColumnModel().getColumn(6).setResizable(false);
    }

    // Cargar Lista
    private void cargar() {
        modeloClientes.setRowCount(0);
        try {
            processClient.leer();
            for (Clientes clien : processClient.lista) {
                modeloClientes.addRow(clien.getInformacionClientes());
                listCircClient.insertar(clien);
            }
        } catch (Exception e) {
        }
    }
    // Limpieza de campos

    public void Limpieza() {
        txt_dniCliente.setText(null);
        txt_nombreCliente.setText(null);
        txt_ApellidoCliente.setText(null);
        txt_CelularCliente.setText(null);
        txt_emailCliente.setText(null);
        txt_DireccionCliente.setText(null);
        jcomboSexo.setSelectedIndex(0);
    }

    // Campos Limpios Registro
    private void CamposLimpiosEdit() {
        txt_dniCliente1.setText(null);
        jC_tipo.setSelectedIndex(0);
        txt_nombreCliente1.setText(null);
        txt_ApellidoCliente1.setText(null);
        txt_CelularCliente1.setText(null);
        txt_DireccionCliente1.setText(null);
        txt_emailCliente1.setText(null);
        Fecha_Modificacion.setText("00-00-0000");
        jcbx_Sexo.setSelectedIndex(0);

    }

    // Campos Limpios Registro
    private void CamposLimpiosDelete() {
        txt_dniCliente3.setText(null);
        txt_TdniCliente4.setText(null);
        txt_nombreCliente2.setText(null);
        txt_ApellidoCliente2.setText(null);
        txt_CelularCliente2.setText(null);
        txt_DireccionCliente2.setText(null);
        txt_emailCliente2.setText(null);
        txt_SexoCliente.setText(null);
        date_modifi.setText("00-00-0000");
    }

    // Listar en Tabla
    public void listar() {
        modeloClientes.setRowCount(0);
        for (Clientes cl : listCircClient.listado()) {
            modeloClientes.addRow(cl.getInformacionClientes());
        }
    }

    // REGISTRAR
    private void Registrar() {

        Object[] arreglo = {Tipo_Documento, txt_dniCliente.getText(), txt_nombreCliente.getText(), txt_ApellidoCliente.getText(),
            jcomboSexo.getSelectedItem().toString(), txt_CelularCliente.getText(), txt_emailCliente.getText(), txt_DireccionCliente.getText(),
            Ventana_Administrador.user, FechaActual, String.valueOf(horaActual).substring(0, 8)};
        client = new Clientes(arreglo);
        listCircClient.insertar(client);
        try {
            processClient.insertar(client);
            listar();
            info();
            Limpieza();
            Message_confirmacion.Message = "<html>El cliente se ha registrado con exito</html>";
            Message_confirmacion.tipo_message = "confirmacion";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al registrar");
        }
    }

    // Editar
    private void Update() {
        Object[] arreglo = {jC_tipo.getSelectedItem().toString(), txt_dniCliente1.getText(), txt_nombreCliente1.getText(), txt_ApellidoCliente1.getText(),
            jcbx_Sexo.getSelectedItem().toString(), txt_CelularCliente1.getText(), txt_emailCliente1.getText(), txt_DireccionCliente1.getText(),
            Ventana_Administrador.user, FechaActual, String.valueOf(horaActual).substring(0, 8)};
        client = new Clientes(arreglo);
        listCircClient.actualizar(client);
        listar();
        info();
        Message_confirmacion.tipo_message = "confirmacion";
        Message_confirmacion.Message = "<html>" + "Los datos del documento " + txt_dniCliente1.getText() + " se han actualizado"
                + " con exito" + "</html>";
        new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        CamposLimpiosEdit();
        try {
            processClient.actualizar(listCircClient.listado());
        } catch (Exception e) {
        }
    }

    // Eliminar
    private void Delete() {
        if (listCircClient.eliminar(txt_dniCliente3.getText())) {
            listar();
            info();
            Message_confirmacion.tipo_message = "confirmacion";
            Message_confirmacion.Message = "<html>" + "El documento " + txt_dniCliente1.getText() + " se elimino"
                    + " con exito" + "</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
            try {
                processClient.actualizar(listCircClient.listado());
            } catch (Exception e) {
            }
        } else {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>El número del documento ingresado no se encuentra registrado</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        }
    }

    // Busqueda
    private void searchEditar() {
        client = listCircClient.buscar(txt_dniCliente1.getText());
        if (client != null) {
            switch (client.getTip_Docu()) {
                case "D.N.I":
                    jC_tipo.setSelectedIndex(1);
                    break;
                case "RUC":
                    jC_tipo.setSelectedIndex(2);
                    break;
                case "C.E":
                    jC_tipo.setSelectedIndex(3);
                    break;
            }
            txt_nombreCliente1.setText(client.getNombres());
            txt_ApellidoCliente1.setText(client.getApellidos());
            txt_CelularCliente1.setText(String.valueOf(client.getCelular()));
            txt_DireccionCliente1.setText(client.getDireccion());
            txt_emailCliente1.setText(client.getEmail());
            Fecha_Modificacion.setText(client.getFecha_Reg());
            switch (client.getSexo()) {
                case "Masculino":
                    jcbx_Sexo.setSelectedIndex(1);
                    break;
                case "Femenino":
                    jcbx_Sexo.setSelectedIndex(2);
                    break;
            }

        } else {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>El número del documento ingresado no se encuentra registrado</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        }
    }

    // Busqueda
    private void searchDelete() {
        client = listCircClient.buscar(txt_dniCliente3.getText());
        if (client != null) {
            txt_TdniCliente4.setText(client.getTip_Docu());
            txt_nombreCliente2.setText(client.getNombres());
            txt_ApellidoCliente2.setText(client.getApellidos());
            txt_CelularCliente2.setText(String.valueOf(client.getCelular()));
            txt_DireccionCliente2.setText(client.getDireccion());
            txt_emailCliente2.setText(client.getEmail());
            txt_SexoCliente.setText(client.getSexo());
            date_modifi.setText(client.getFecha_Reg());
        } else {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>El número del documento ingresado no se encuentra registrado</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupDocumento = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        rSLabelFecha1 = new rojeru_san.rsdate.RSLabelFecha();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        rSPanelsSlider2 = new rojerusan.RSPanelsSlider();
        Panel_RegisterCliente = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txt_dniCliente = new RSMaterialComponent.RSTextFieldTwo();
        jLabel12 = new javax.swing.JLabel();
        txt_nombreCliente = new RSMaterialComponent.RSTextFieldTwo();
        jLabel14 = new javax.swing.JLabel();
        txt_DireccionCliente = new RSMaterialComponent.RSTextFieldTwo();
        jLabel15 = new javax.swing.JLabel();
        txt_CelularCliente = new RSMaterialComponent.RSTextFieldTwo();
        RB_ce = new javax.swing.JRadioButton();
        RB_DNI = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        btn_RegisterClient = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        jLabel13 = new javax.swing.JLabel();
        txt_ApellidoCliente = new RSMaterialComponent.RSTextFieldTwo();
        jLabel30 = new javax.swing.JLabel();
        txt_emailCliente = new RSMaterialComponent.RSTextFieldTwo();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel31 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jcomboSexo = new RSMaterialComponent.RSComboBoxMaterial();
        RB_Ruc = new javax.swing.JRadioButton();
        registrado = new javax.swing.JLabel();
        Panel_EditCliente = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txt_dniCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel33 = new javax.swing.JLabel();
        txt_nombreCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel34 = new javax.swing.JLabel();
        txt_DireccionCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel35 = new javax.swing.JLabel();
        txt_CelularCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel36 = new javax.swing.JLabel();
        btn_RegisterClient1 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        jLabel37 = new javax.swing.JLabel();
        txt_ApellidoCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel38 = new javax.swing.JLabel();
        txt_emailCliente1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel40 = new javax.swing.JLabel();
        Fecha_Modificacion = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jcbx_Sexo = new RSMaterialComponent.RSComboBoxMaterial();
        rSButtonIconUno1 = new RSMaterialComponent.RSButtonIconUno();
        rSButtonIconUno3 = new RSMaterialComponent.RSButtonIconUno();
        jC_tipo = new rojerusan.RSComboMetro();
        Panel_DeleteCliente = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        txt_dniCliente3 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel44 = new javax.swing.JLabel();
        txt_nombreCliente2 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel45 = new javax.swing.JLabel();
        txt_DireccionCliente2 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel46 = new javax.swing.JLabel();
        txt_CelularCliente2 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel47 = new javax.swing.JLabel();
        btn_RegisterClient2 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        jLabel48 = new javax.swing.JLabel();
        txt_ApellidoCliente2 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel49 = new javax.swing.JLabel();
        txt_emailCliente2 = new RSMaterialComponent.RSTextFieldTwo();
        txt_TdniCliente4 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel50 = new javax.swing.JLabel();
        date_modifi = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        rSButtonIconUno2 = new RSMaterialComponent.RSButtonIconUno();
        txt_SexoCliente = new RSMaterialComponent.RSTextFieldTwo();
        rSButtonIconUno4 = new RSMaterialComponent.RSButtonIconUno();
        btn_register = new rojeru_san.rsbutton.RSButtonCustom();
        btn_edit = new rojeru_san.rsbutton.RSButtonCustom();
        btn_delete = new rojeru_san.rsbutton.RSButtonCustom();
        rSButtonMaterialRippleIcon2 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTabla_Clientes = new rojerusan.RSTableMetro();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText(" Clientes");

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        rSLabelFecha1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelHora1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelFecha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rSLabelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSLabelHora1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText(":: Listado de Clientes");

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Total : -- Registrados  ");

        rSPanelsSlider2.setBackground(new java.awt.Color(255, 255, 255));

        Panel_RegisterCliente.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Nro de Documento : ");

        txt_dniCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_dniCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_dniCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_dniCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_dniCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_dniCliente.setPlaceholder("ejem: 99999999");
        txt_dniCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_dniClienteKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Nombres:  ");

        txt_nombreCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_nombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombreCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_nombreCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente.setPlaceholder(" Ingrese Nombres Completos");
        txt_nombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nombreClienteKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Domicilio :");

        txt_DireccionCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_DireccionCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_DireccionCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_DireccionCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente.setPlaceholder("Calle, Urbanizacion, AA.HH, Provincia");
        txt_DireccionCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_DireccionClienteKeyReleased(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Nro de Celular: ");

        txt_CelularCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_CelularCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_CelularCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_CelularCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente.setPlaceholder("987654321");
        txt_CelularCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_CelularClienteKeyReleased(evt);
            }
        });

        RB_ce.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupDocumento.add(RB_ce);
        RB_ce.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        RB_ce.setForeground(new java.awt.Color(0, 0, 0));
        RB_ce.setText(" C.E");
        RB_ce.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RB_ceMouseClicked(evt);
            }
        });

        RB_DNI.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupDocumento.add(RB_DNI);
        RB_DNI.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        RB_DNI.setForeground(new java.awt.Color(0, 0, 0));
        RB_DNI.setSelected(true);
        RB_DNI.setText("D.N.I");
        RB_DNI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RB_DNIMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Tipo de Documento: ");

        btn_RegisterClient.setText("Registrar Cliente");
        btn_RegisterClient.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btn_RegisterClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterClientActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Apellidos:  ");

        txt_ApellidoCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_ApellidoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ApellidoCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_ApellidoCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente.setPlaceholder(" Ingrese Apellidos Completos");
        txt_ApellidoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_ApellidoClienteKeyReleased(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Correo Electronico :");

        txt_emailCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_emailCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_emailCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_emailCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente.setPlaceholder("e-mail@example.com");
        txt_emailCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_emailClienteKeyReleased(evt);
            }
        });

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(0, 112, 192));
        jProgressBar1.setValue(13);

        jLabel31.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Progreso de Registro");

        jLabel42.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("Sexo:");

        jcomboSexo.setForeground(new java.awt.Color(0, 0, 0));
        jcomboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Masculino", "Femenino" }));
        jcomboSexo.setFocusable(false);
        jcomboSexo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jcomboSexo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcomboSexoItemStateChanged(evt);
            }
        });

        RB_Ruc.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupDocumento.add(RB_Ruc);
        RB_Ruc.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        RB_Ruc.setForeground(new java.awt.Color(0, 0, 0));
        RB_Ruc.setText("RUC");
        RB_Ruc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RB_RucMouseClicked(evt);
            }
        });

        registrado.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 10)); // NOI18N
        registrado.setForeground(new java.awt.Color(204, 0, 0));
        registrado.setText("documento ya registrado");

        javax.swing.GroupLayout Panel_RegisterClienteLayout = new javax.swing.GroupLayout(Panel_RegisterCliente);
        Panel_RegisterCliente.setLayout(Panel_RegisterClienteLayout);
        Panel_RegisterClienteLayout.setHorizontalGroup(
            Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                        .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                                .addComponent(txt_nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcomboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_CelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                                .addComponent(txt_DireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txt_emailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_RegisterClient, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(RB_ce, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RB_DNI)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RB_Ruc)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registrado)
                            .addComponent(txt_dniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Panel_RegisterClienteLayout.setVerticalGroup(
            Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                        .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RB_ce)
                                    .addComponent(RB_DNI)
                                    .addComponent(RB_Ruc)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_dniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(registrado, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_CelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcomboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_DireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_RegisterClienteLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(Panel_RegisterClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_emailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_RegisterClient, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        rSPanelsSlider2.add(Panel_RegisterCliente, "card2");

        Panel_EditCliente.setBackground(new java.awt.Color(255, 255, 255));

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

        txt_nombreCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_nombreCliente1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombreCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_nombreCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente1.setPlaceholder(" Nombres Completos");

        jLabel34.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Domicilio :");

        txt_DireccionCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_DireccionCliente1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_DireccionCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_DireccionCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente1.setPlaceholder("Calle, Urbanizacion, AA.HH, Provincia");

        jLabel35.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Nro de Celular: ");

        txt_CelularCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_CelularCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_CelularCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente1.setPlaceholder("987654321");

        jLabel36.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Tipo de Documento: ");

        btn_RegisterClient1.setText("Guardar Cambios");
        btn_RegisterClient1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btn_RegisterClient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterClient1ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Apellidos:  ");

        txt_ApellidoCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_ApellidoCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_ApellidoCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente1.setPlaceholder(" Apellidos Completos");

        jLabel38.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Correo Electronico :");

        txt_emailCliente1.setForeground(new java.awt.Color(0, 0, 0));
        txt_emailCliente1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_emailCliente1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_emailCliente1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente1.setPlaceholder("e-mail@example.com");

        jLabel40.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Fecha de Modificación: ");

        Fecha_Modificacion.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        Fecha_Modificacion.setForeground(new java.awt.Color(51, 51, 51));
        Fecha_Modificacion.setText("00-00-0000");

        jLabel39.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setText("Sexo:");

        jcbx_Sexo.setForeground(new java.awt.Color(0, 0, 0));
        jcbx_Sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Masculino", "Femenino" }));
        jcbx_Sexo.setFocusable(false);
        jcbx_Sexo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N

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

        jC_tipo.setBackground(new java.awt.Color(255, 255, 255));
        jC_tipo.setForeground(new java.awt.Color(0, 0, 0));
        jC_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "D.N.I", "RUC", "C.E" }));
        jC_tipo.setColorFondo(new java.awt.Color(255, 255, 255));
        jC_tipo.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N

        javax.swing.GroupLayout Panel_EditClienteLayout = new javax.swing.GroupLayout(Panel_EditCliente);
        Panel_EditCliente.setLayout(Panel_EditClienteLayout);
        Panel_EditClienteLayout.setHorizontalGroup(
            Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dniCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonIconUno1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonIconUno3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Fecha_Modificacion))
                    .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                        .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                                .addComponent(txt_DireccionCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txt_emailCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_RegisterClient1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                                .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                                        .addGap(265, 265, 265)
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jC_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7))
                                    .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                                        .addComponent(txt_nombreCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_ApellidoCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbx_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_CelularCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(32, 32, 32))
        );
        Panel_EditClienteLayout.setVerticalGroup(
            Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_dniCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Fecha_Modificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jC_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSButtonIconUno1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonIconUno3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                        .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nombreCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ApellidoCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_CelularCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_DireccionCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_EditClienteLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(Panel_EditClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_emailCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_RegisterClient1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jcbx_Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rSPanelsSlider2.add(Panel_EditCliente, "card2");

        Panel_DeleteCliente.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Nro de Documento : ");

        txt_dniCliente3.setForeground(new java.awt.Color(0, 0, 0));
        txt_dniCliente3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_dniCliente3.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_dniCliente3.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_dniCliente3.setPhColor(new java.awt.Color(51, 51, 51));
        txt_dniCliente3.setPlaceholder("Ingrese Documento");

        jLabel44.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("Nombres:  ");

        txt_nombreCliente2.setEditable(false);
        txt_nombreCliente2.setForeground(new java.awt.Color(0, 0, 0));
        txt_nombreCliente2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombreCliente2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_nombreCliente2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_nombreCliente2.setPlaceholder(" Nombres Completos");

        jLabel45.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("Domicilio :");

        txt_DireccionCliente2.setEditable(false);
        txt_DireccionCliente2.setForeground(new java.awt.Color(0, 0, 0));
        txt_DireccionCliente2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_DireccionCliente2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_DireccionCliente2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_DireccionCliente2.setPlaceholder("Calle, Urbanizacion, AA.HH, Provincia");

        jLabel46.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Nro de Celular: ");

        txt_CelularCliente2.setEditable(false);
        txt_CelularCliente2.setForeground(new java.awt.Color(0, 0, 0));
        txt_CelularCliente2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_CelularCliente2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_CelularCliente2.setPlaceholder("987654321");

        jLabel47.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Tipo de Documento: ");

        btn_RegisterClient2.setText("Eliminar Cliente");
        btn_RegisterClient2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        btn_RegisterClient2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterClient2ActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("Apellidos:  ");

        txt_ApellidoCliente2.setEditable(false);
        txt_ApellidoCliente2.setForeground(new java.awt.Color(0, 0, 0));
        txt_ApellidoCliente2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_ApellidoCliente2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_ApellidoCliente2.setPlaceholder(" Apellidos Completos");

        jLabel49.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(51, 51, 51));
        jLabel49.setText("Correo Electronico :");

        txt_emailCliente2.setEditable(false);
        txt_emailCliente2.setForeground(new java.awt.Color(0, 0, 0));
        txt_emailCliente2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_emailCliente2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_emailCliente2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_emailCliente2.setPlaceholder("e-mail@example.com");

        txt_TdniCliente4.setEditable(false);
        txt_TdniCliente4.setForeground(new java.awt.Color(0, 0, 0));
        txt_TdniCliente4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_TdniCliente4.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_TdniCliente4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_TdniCliente4.setPhColor(new java.awt.Color(51, 51, 51));
        txt_TdniCliente4.setPlaceholder("C.E / D.N.I");

        jLabel50.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Fecha de Modificación: ");

        date_modifi.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        date_modifi.setForeground(new java.awt.Color(51, 51, 51));
        date_modifi.setText("00-00-0000");

        jLabel52.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Sexo:");

        rSButtonIconUno2.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconUno2.setForegroundText(new java.awt.Color(0, 0, 0));
        rSButtonIconUno2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSButtonIconUno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconUno2ActionPerformed(evt);
            }
        });

        txt_SexoCliente.setEditable(false);
        txt_SexoCliente.setForeground(new java.awt.Color(0, 0, 0));
        txt_SexoCliente.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_SexoCliente.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_SexoCliente.setPhColor(new java.awt.Color(51, 51, 51));
        txt_SexoCliente.setPlaceholder("Masc / Femen");

        rSButtonIconUno4.setBackground(new java.awt.Color(255, 255, 255));
        rSButtonIconUno4.setForegroundText(new java.awt.Color(0, 0, 0));
        rSButtonIconUno4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR_ALL);
        rSButtonIconUno4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonIconUno4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_DeleteClienteLayout = new javax.swing.GroupLayout(Panel_DeleteCliente);
        Panel_DeleteCliente.setLayout(Panel_DeleteClienteLayout);
        Panel_DeleteClienteLayout.setHorizontalGroup(
            Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DeleteClienteLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DeleteClienteLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dniCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonIconUno2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_modifi))
                    .addGroup(Panel_DeleteClienteLayout.createSequentialGroup()
                        .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Panel_DeleteClienteLayout.createSequentialGroup()
                                .addComponent(txt_DireccionCliente2, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txt_emailCliente2, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_RegisterClient2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_DeleteClienteLayout.createSequentialGroup()
                                .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Panel_DeleteClienteLayout.createSequentialGroup()
                                        .addGap(217, 217, 217)
                                        .addComponent(rSButtonIconUno4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_TdniCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Panel_DeleteClienteLayout.createSequentialGroup()
                                        .addComponent(txt_nombreCliente2, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel48)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_ApellidoCliente2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_SexoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_CelularCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(32, 32, 32))
        );
        Panel_DeleteClienteLayout.setVerticalGroup(
            Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DeleteClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_dniCliente3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(date_modifi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_TdniCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSButtonIconUno2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonIconUno4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombreCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ApellidoCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_CelularCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SexoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_DireccionCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_DeleteClienteLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(Panel_DeleteClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_emailCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_RegisterClient2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rSPanelsSlider2.add(Panel_DeleteCliente, "card2");

        btn_register.setForeground(new java.awt.Color(102, 102, 102));
        btn_register.setText("Registrar");
        btn_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registerActionPerformed(evt);
            }
        });

        btn_edit.setForeground(new java.awt.Color(102, 102, 102));
        btn_edit.setText("Modificar");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_delete.setForeground(new java.awt.Color(102, 102, 102));
        btn_delete.setText("Eliminar");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        rSButtonMaterialRippleIcon2.setText("Consulta General");
        rSButtonMaterialRippleIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        rSButtonMaterialRippleIcon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon2ActionPerformed(evt);
            }
        });

        jTabla_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTabla_Clientes.setColorBorderRows(new java.awt.Color(153, 153, 153));
        jTabla_Clientes.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        jTabla_Clientes.setColorSecondary(new java.awt.Color(255, 255, 255));
        jTabla_Clientes.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        jTabla_Clientes.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jTabla_Clientes.setFontHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 11)); // NOI18N
        jTabla_Clientes.setWidthBorderHead(0);
        jTabla_Clientes.setWidthBorderRows(0);
        jScrollPane5.setViewportView(jTabla_Clientes);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jScrollPane5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_register, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rSButtonMaterialRippleIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(rSPanelsSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_register, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMaterialRippleIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSPanelsSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
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

    private void btn_RegisterClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterClientActionPerformed
        try {
            if (listCircClient.Longitud() == 0) {
                Registrar();
            } else {
                client = listCircClient.buscar(txt_dniCliente.getText());
                if (client != null) {
                    Message_confirmacion.tipo_message = "advertencia";
                    Message_confirmacion.Message = "<html>El documento " + txt_dniCliente.getText()
                            + " ya se encuentra registrado</html>";
                    new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
                } else {
                    Registrar();
                }
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un error al registrar " + e);
        }
        por1 = 13;
        por2 = 0;
        por3 = 0;
        por4 = 0;
        por5 = 0;
        por6 = 0;
        por7 = 0;
        por8 = 0;
        Progreso();
        repaint();
    }//GEN-LAST:event_btn_RegisterClientActionPerformed

    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed
        Panel_RegisterCliente.setVisible(true);
        Panel_EditCliente.setVisible(false);
        Panel_DeleteCliente.setVisible(false);
    }//GEN-LAST:event_btn_registerActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        Panel_RegisterCliente.setVisible(false);
        Panel_EditCliente.setVisible(true);
        Panel_DeleteCliente.setVisible(false);
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        Panel_RegisterCliente.setVisible(false);
        Panel_EditCliente.setVisible(false);
        Panel_DeleteCliente.setVisible(true);
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_RegisterClient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterClient1ActionPerformed
        Update();
    }//GEN-LAST:event_btn_RegisterClient1ActionPerformed

    private void RB_ceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RB_ceMouseClicked
        TD();
    }//GEN-LAST:event_RB_ceMouseClicked

    private void RB_DNIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RB_DNIMouseClicked
        TD();
    }//GEN-LAST:event_RB_DNIMouseClicked

    private void RB_RucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RB_RucMouseClicked
        TD();
    }//GEN-LAST:event_RB_RucMouseClicked

    private void btn_RegisterClient2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterClient2ActionPerformed
        Delete();
        CamposLimpiosDelete();
    }//GEN-LAST:event_btn_RegisterClient2ActionPerformed

    private void rSButtonIconUno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno1ActionPerformed
        if (listCircClient.Longitud() == 0) {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>No hay Clientes registrados en la base de datos</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        } else {
            searchEditar();
        }

    }//GEN-LAST:event_rSButtonIconUno1ActionPerformed

    private void rSButtonIconUno2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno2ActionPerformed
        if (listCircClient.Longitud() == 0) {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>No hay Clientes registrados en la base de datos</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        } else {
            searchDelete();
        }
    }//GEN-LAST:event_rSButtonIconUno2ActionPerformed

    private void rSButtonIconUno3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno3ActionPerformed
        CamposLimpiosEdit();
    }//GEN-LAST:event_rSButtonIconUno3ActionPerformed

    private void rSButtonIconUno4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno4ActionPerformed
        CamposLimpiosDelete();
    }//GEN-LAST:event_rSButtonIconUno4ActionPerformed

    private void rSButtonMaterialRippleIcon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon2ActionPerformed
        if (listCircClient.Longitud() == 0) {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>No hay Clientes registrados en la base de datos</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        } else {
            new Busqueda_avanzadaCliente(new javax.swing.JFrame(), true).setVisible(true);
        }
    }//GEN-LAST:event_rSButtonMaterialRippleIcon2ActionPerformed

    private void txt_dniClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dniClienteKeyReleased
        if (listCircClient.Longitud() == 0) {
            if (!txt_dniCliente.getText().isEmpty()) {
                por2 = 13;

            } else {
                por2 = 0;
                registrado.setVisible(false);
            }
        } else {
            if (!txt_dniCliente.getText().isEmpty()) {
                por2 = 13;
                if (listCircClient.Longitud() > 0 || listCircClient.Longitud() == 0) {
                    client = listCircClient.buscar(txt_dniCliente.getText());
                    if (client != null) {
                        registrado.setVisible(true);
                    } else {
                        registrado.setVisible(false);
                    }
                }

            } else {
                por2 = 0;
                registrado.setVisible(false);
            }
        }

        Progreso();

        repaint();
    }//GEN-LAST:event_txt_dniClienteKeyReleased

    private void txt_nombreClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreClienteKeyReleased
        if (!txt_nombreCliente.getText().isEmpty()) {
            por3 = 13;
        } else {
            por3 = 0;
        }
        Progreso();
    }//GEN-LAST:event_txt_nombreClienteKeyReleased

    private void txt_ApellidoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ApellidoClienteKeyReleased
        if (!txt_ApellidoCliente.getText().isEmpty()) {
            por4 = 13;
        } else {
            por4 = 0;
        }
        Progreso();
    }//GEN-LAST:event_txt_ApellidoClienteKeyReleased

    private void jcomboSexoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcomboSexoItemStateChanged
        if ("Masculino".equals(jcomboSexo.getSelectedItem().toString()) || "Femenino".equals(jcomboSexo.getSelectedItem().toString())) {
            por5 = 13;
        } else {
            por5 = 0;
        }
        Progreso();
    }//GEN-LAST:event_jcomboSexoItemStateChanged

    private void txt_CelularClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CelularClienteKeyReleased
        if (!txt_CelularCliente.getText().isEmpty()) {
            por6 = 13;
        } else {
            por6 = 0;
        }
        Progreso();
    }//GEN-LAST:event_txt_CelularClienteKeyReleased

    private void txt_DireccionClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DireccionClienteKeyReleased
        if (!txt_DireccionCliente.getText().isEmpty()) {
            por7 = 9;
        } else {
            por7 = 0;
        }
        Progreso();
    }//GEN-LAST:event_txt_DireccionClienteKeyReleased

    private void txt_emailClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_emailClienteKeyReleased
        if (!txt_emailCliente.getText().isEmpty()) {
            por8 = 13;
        } else {
            por8 = 0;
        }
        Progreso();
    }//GEN-LAST:event_txt_emailClienteKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fecha_Modificacion;
    private javax.swing.JPanel Panel_DeleteCliente;
    private javax.swing.JPanel Panel_EditCliente;
    private javax.swing.JPanel Panel_RegisterCliente;
    private javax.swing.JRadioButton RB_DNI;
    private javax.swing.JRadioButton RB_Ruc;
    private javax.swing.JRadioButton RB_ce;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_RegisterClient;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_RegisterClient1;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_RegisterClient2;
    private rojeru_san.rsbutton.RSButtonCustom btn_delete;
    private rojeru_san.rsbutton.RSButtonCustom btn_edit;
    private rojeru_san.rsbutton.RSButtonCustom btn_register;
    private javax.swing.ButtonGroup buttonGroupDocumento;
    private javax.swing.JLabel date_modifi;
    private rojerusan.RSComboMetro jC_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    public static javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator3;
    public static rojerusan.RSTableMetro jTabla_Clientes;
    private RSMaterialComponent.RSComboBoxMaterial jcbx_Sexo;
    private RSMaterialComponent.RSComboBoxMaterial jcomboSexo;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno1;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno2;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno3;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno4;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon2;
    private rojeru_san.rsdate.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private rojerusan.RSPanelsSlider rSPanelsSlider2;
    private javax.swing.JLabel registrado;
    private RSMaterialComponent.RSTextFieldTwo txt_ApellidoCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_ApellidoCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_ApellidoCliente2;
    private RSMaterialComponent.RSTextFieldTwo txt_CelularCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_CelularCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_CelularCliente2;
    private RSMaterialComponent.RSTextFieldTwo txt_DireccionCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_DireccionCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_DireccionCliente2;
    private RSMaterialComponent.RSTextFieldTwo txt_SexoCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_TdniCliente4;
    private RSMaterialComponent.RSTextFieldTwo txt_dniCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_dniCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_dniCliente3;
    private RSMaterialComponent.RSTextFieldTwo txt_emailCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_emailCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_emailCliente2;
    private RSMaterialComponent.RSTextFieldTwo txt_nombreCliente;
    private RSMaterialComponent.RSTextFieldTwo txt_nombreCliente1;
    private RSMaterialComponent.RSTextFieldTwo txt_nombreCliente2;
    // End of variables declaration//GEN-END:variables
}
