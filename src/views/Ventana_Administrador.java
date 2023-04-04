package views;

import VentanasEmegentes.Register_Categoria;
import java.awt.Color;
import rojerusan.RSPanelsSlider;
import message.Acerca_de;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.UIManager;
import panels.Pnl_Estadisticas;

public class Ventana_Administrador extends javax.swing.JFrame {

    public static double pocentajeCliente;
    public static double pocentajeInventario;
    public static String user, TipoUsuario;
    Register_Categoria RC = new Register_Categoria(new javax.swing.JFrame(), true);

    public Ventana_Administrador() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        pnl_homeUser1.setVisible(false);
        pnl_home1.setVisible(false);
        version.setText("Version 2.1.0");
        jSeparator9.setVisible(false);
        Bttn_Conf.setVisible(false);
        Bttn_Admin.setVisible(false);
        jSeparator10.setVisible(false);
        if ("Administrador".equals(TipoUsuario)) {
            this.pnlSlider.setPanelSlider(1, pnl_home1, RSPanelsSlider.DIRECT.RIGHT);
            pnl_home1.setVisible(true);
        } else {
            this.pnlSlider.setPanelSlider(1, pnl_homeUser1, RSPanelsSlider.DIRECT.RIGHT);
            pnl_homeUser1.setVisible(true);
        }
        RC.cargar_categoria();
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/ceo_1.png"));
        return retValue;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        print_nameUser = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        version = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        txt_acercade = new javax.swing.JLabel();
        Bttn_home = new rojeru_san.rsbutton.RSButtonEffect();
        Bttn_Clientes = new rojeru_san.rsbutton.RSButtonEffect();
        Bttn_Ventas = new rojeru_san.rsbutton.RSButtonEffect();
        Bttn_Almacen = new rojeru_san.rsbutton.RSButtonEffect();
        Bttn_Estadistica = new rojeru_san.rsbutton.RSButtonEffect();
        texto_bienvenida = new javax.swing.JLabel();
        rSPanelImage1 = new rojeru_san.rspanel.RSPanelImage();
        Btn_logout = new RSMaterialComponent.RSButtonMaterialIconUno();
        name_empresa = new javax.swing.JLabel();
        Bttn_Conf = new rojeru_san.rsbutton.RSButtonEffect();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        Bttn_Admin = new rojeru_san.rsbutton.RSButtonEffect();
        Bttn_Empleados = new rojeru_san.rsbutton.RSButtonEffect();
        jSeparator11 = new javax.swing.JSeparator();
        pnlSlider = new rojerusan.RSPanelsSlider();
        pnl_home1 = new panels.Pnl_home();
        pnl_Clientes1 = new panels.Pnl_Clientes();
        pnl_Config1 = new panels.Pnl_Config();
        pnl_Estadisticas1 = new panels.Pnl_Estadisticas();
        pnl_inventario1 = new panels.Pnl_inventario();
        pnl_ventas1 = new panels.Pnl_ventas();
        pnl_Empleados1 = new panels.Pnl_Empleados();
        pnl_homeUser1 = new panels.Pnl_homeUser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 114, 198));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        print_nameUser.setFont(new java.awt.Font("Cambria", 2, 14)); // NOI18N
        print_nameUser.setForeground(new java.awt.Color(255, 255, 255));
        print_nameUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_nameUser.setText("Tipo de usuario");

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("MemoriesBack");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));

        version.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        version.setForeground(new java.awt.Color(255, 255, 255));
        version.setText("Version 2.0.1");

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));

        txt_acercade.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        txt_acercade.setForeground(new java.awt.Color(255, 255, 255));
        txt_acercade.setText("Acerca de");
        txt_acercade.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txt_acercade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_acercadeMousePressed(evt);
            }
        });

        Bttn_home.setBackground(new java.awt.Color(0, 114, 198));
        Bttn_home.setBorder(null);
        Bttn_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        Bttn_home.setText(" Inicio");
        Bttn_home.setEfecto(rojeru_san.rsbutton.RSButtonEffect.EFECTO.RIPPLE);
        Bttn_home.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Bttn_home.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Bttn_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bttn_homeActionPerformed(evt);
            }
        });

        Bttn_Clientes.setBackground(new java.awt.Color(0, 114, 198));
        Bttn_Clientes.setBorder(null);
        Bttn_Clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clientes.png"))); // NOI18N
        Bttn_Clientes.setText(" Clientes  ");
        Bttn_Clientes.setEfecto(rojeru_san.rsbutton.RSButtonEffect.EFECTO.RIPPLE);
        Bttn_Clientes.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Bttn_Clientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Bttn_Clientes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Bttn_Clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bttn_ClientesActionPerformed(evt);
            }
        });

        Bttn_Ventas.setBackground(new java.awt.Color(0, 114, 198));
        Bttn_Ventas.setBorder(null);
        Bttn_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/carrito.png"))); // NOI18N
        Bttn_Ventas.setText(" Ventas");
        Bttn_Ventas.setEfecto(rojeru_san.rsbutton.RSButtonEffect.EFECTO.RIPPLE);
        Bttn_Ventas.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Bttn_Ventas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Bttn_Ventas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Bttn_Ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bttn_VentasActionPerformed(evt);
            }
        });

        Bttn_Almacen.setBackground(new java.awt.Color(0, 114, 198));
        Bttn_Almacen.setBorder(null);
        Bttn_Almacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/almacen.png"))); // NOI18N
        Bttn_Almacen.setText(" Almacen");
        Bttn_Almacen.setEfecto(rojeru_san.rsbutton.RSButtonEffect.EFECTO.RIPPLE);
        Bttn_Almacen.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Bttn_Almacen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Bttn_Almacen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Bttn_Almacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bttn_AlmacenActionPerformed(evt);
            }
        });

        Bttn_Estadistica.setBackground(new java.awt.Color(0, 114, 198));
        Bttn_Estadistica.setBorder(null);
        Bttn_Estadistica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/102501.png"))); // NOI18N
        Bttn_Estadistica.setText(" Reportes");
        Bttn_Estadistica.setEfecto(rojeru_san.rsbutton.RSButtonEffect.EFECTO.RIPPLE);
        Bttn_Estadistica.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Bttn_Estadistica.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Bttn_Estadistica.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Bttn_Estadistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bttn_EstadisticaActionPerformed(evt);
            }
        });

        texto_bienvenida.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        texto_bienvenida.setForeground(new java.awt.Color(255, 255, 255));
        texto_bienvenida.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        texto_bienvenida.setText(":: BIENVENIDO:");

        rSPanelImage1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/images/usuario.png"))); // NOI18N

        javax.swing.GroupLayout rSPanelImage1Layout = new javax.swing.GroupLayout(rSPanelImage1);
        rSPanelImage1.setLayout(rSPanelImage1Layout);
        rSPanelImage1Layout.setHorizontalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        rSPanelImage1Layout.setVerticalGroup(
            rSPanelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Btn_logout.setText(" Cerrar Sesión");
        Btn_logout.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        Btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_logoutActionPerformed(evt);
            }
        });

        name_empresa.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        name_empresa.setForeground(new java.awt.Color(255, 255, 255));
        name_empresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name_empresa.setText("Nombre Empresa");

        Bttn_Conf.setBackground(new java.awt.Color(0, 114, 198));
        Bttn_Conf.setBorder(null);
        Bttn_Conf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/conf.png"))); // NOI18N
        Bttn_Conf.setText(" Configuración");
        Bttn_Conf.setEfecto(rojeru_san.rsbutton.RSButtonEffect.EFECTO.RIPPLE);
        Bttn_Conf.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Bttn_Conf.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Bttn_Conf.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Bttn_Conf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bttn_ConfActionPerformed(evt);
            }
        });

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));

        Bttn_Admin.setBackground(new java.awt.Color(0, 114, 198));
        Bttn_Admin.setBorder(null);
        Bttn_Admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adm.png"))); // NOI18N
        Bttn_Admin.setText(" Administración");
        Bttn_Admin.setEfecto(rojeru_san.rsbutton.RSButtonEffect.EFECTO.RIPPLE);
        Bttn_Admin.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Bttn_Admin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Bttn_Admin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Bttn_Admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bttn_AdminActionPerformed(evt);
            }
        });

        Bttn_Empleados.setBackground(new java.awt.Color(0, 114, 198));
        Bttn_Empleados.setBorder(null);
        Bttn_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clientes.png"))); // NOI18N
        Bttn_Empleados.setText(" Empleados");
        Bttn_Empleados.setEfecto(rojeru_san.rsbutton.RSButtonEffect.EFECTO.RIPPLE);
        Bttn_Empleados.setFont(new java.awt.Font("Roboto Bold", 1, 16)); // NOI18N
        Bttn_Empleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Bttn_Empleados.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Bttn_Empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bttn_EmpleadosActionPerformed(evt);
            }
        });

        jSeparator11.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_acercade, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(version, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(texto_bienvenida)
                            .addComponent(print_nameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bttn_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bttn_Clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(name_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Bttn_Conf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bttn_Ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bttn_Almacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bttn_Estadistica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bttn_Admin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bttn_Empleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(name_empresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(rSPanelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(texto_bienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(print_nameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(Bttn_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(Bttn_Clientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bttn_Empleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bttn_Ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(Bttn_Almacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(Bttn_Estadistica, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bttn_Admin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bttn_Conf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7)
                .addGap(65, 65, 65)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_acercade, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(version, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlSlider.setBackground(new java.awt.Color(255, 255, 255));

        pnl_home1.setName("pnl_home1"); // NOI18N
        pnlSlider.add(pnl_home1, "card2");

        pnl_Clientes1.setName("pnl_Clientes1"); // NOI18N
        pnlSlider.add(pnl_Clientes1, "card3");

        pnl_Config1.setName("pnl_Config1"); // NOI18N
        pnlSlider.add(pnl_Config1, "card4");

        pnl_Estadisticas1.setName("pnl_Estadisticas1"); // NOI18N
        pnlSlider.add(pnl_Estadisticas1, "card7");

        pnl_inventario1.setName("pnl_inventario1"); // NOI18N
        pnlSlider.add(pnl_inventario1, "card5");

        pnl_ventas1.setName("pnl_ventas1"); // NOI18N
        pnlSlider.add(pnl_ventas1, "card6");

        pnl_Empleados1.setName("pnl_Empleados1"); // NOI18N
        pnlSlider.add(pnl_Empleados1, "card8");

        pnl_homeUser1.setName("pnl_homeUser1"); // NOI18N
        pnlSlider.add(pnl_homeUser1, "card9");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pnlSlider, javax.swing.GroupLayout.DEFAULT_SIZE, 1187, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_acercadeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_acercadeMousePressed
        new Acerca_de(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_txt_acercadeMousePressed

    private void Bttn_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bttn_homeActionPerformed
        if ("Administrador".equals(TipoUsuario)) {
            this.pnlSlider.setPanelSlider(1, pnl_home1, RSPanelsSlider.DIRECT.RIGHT);
        } else {
            this.pnlSlider.setPanelSlider(1, pnl_homeUser1, RSPanelsSlider.DIRECT.RIGHT);
        }
        // Colores     
        Bttn_home.setBackground(new Color(51, 153, 255));
        Bttn_Clientes.setBackground(new Color(0, 114, 198));
        Bttn_Empleados.setBackground(new Color(0, 114, 198));
        Bttn_Ventas.setBackground(new Color(0, 114, 198));
        Bttn_Almacen.setBackground(new Color(0, 114, 198));
        Bttn_Estadistica.setBackground(new Color(0, 114, 198));
        Bttn_Admin.setBackground(new Color(0, 114, 198));
        Bttn_Conf.setBackground(new Color(0, 114, 198));
    }//GEN-LAST:event_Bttn_homeActionPerformed

    private void Bttn_ClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bttn_ClientesActionPerformed
        pnlSlider.setPanelSlider(1, pnl_Clientes1, RSPanelsSlider.DIRECT.RIGHT);
        // Colores   
        Bttn_home.setBackground(new Color(0, 114, 198));
        Bttn_Clientes.setBackground(new Color(51, 153, 255));
        Bttn_Empleados.setBackground(new Color(0, 114, 198));
        Bttn_Ventas.setBackground(new Color(0, 114, 198));
        Bttn_Almacen.setBackground(new Color(0, 114, 198));
        Bttn_Estadistica.setBackground(new Color(0, 114, 198));
        Bttn_Admin.setBackground(new Color(0, 114, 198));
        Bttn_Conf.setBackground(new Color(0, 114, 198));
    }//GEN-LAST:event_Bttn_ClientesActionPerformed

    private void Bttn_VentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bttn_VentasActionPerformed
        pnlSlider.setPanelSlider(1, pnl_ventas1, RSPanelsSlider.DIRECT.RIGHT);
        // Colores  
        Bttn_home.setBackground(new Color(0, 114, 198));
        Bttn_Clientes.setBackground(new Color(0, 114, 198));
        Bttn_Empleados.setBackground(new Color(0, 114, 198));
        Bttn_Ventas.setBackground(new Color(51, 153, 255));
        Bttn_Almacen.setBackground(new Color(0, 114, 198));
        Bttn_Estadistica.setBackground(new Color(0, 114, 198));
        Bttn_Admin.setBackground(new Color(0, 114, 198));
        Bttn_Conf.setBackground(new Color(0, 114, 198));
    }//GEN-LAST:event_Bttn_VentasActionPerformed

    private void Bttn_AlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bttn_AlmacenActionPerformed
        pnlSlider.setPanelSlider(1, pnl_inventario1, RSPanelsSlider.DIRECT.RIGHT);
        // Colores   
        Bttn_home.setBackground(new Color(0, 114, 198));
        Bttn_Clientes.setBackground(new Color(0, 114, 198));
        Bttn_Empleados.setBackground(new Color(0, 114, 198));
        Bttn_Ventas.setBackground(new Color(0, 114, 198));
        Bttn_Almacen.setBackground(new Color(51, 153, 255));
        Bttn_Estadistica.setBackground(new Color(0, 114, 198));
        Bttn_Admin.setBackground(new Color(0, 114, 198));
        Bttn_Conf.setBackground(new Color(0, 114, 198));
    }//GEN-LAST:event_Bttn_AlmacenActionPerformed

    private void Bttn_EstadisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bttn_EstadisticaActionPerformed
        pnlSlider.setPanelSlider(1, pnl_Estadisticas1, RSPanelsSlider.DIRECT.RIGHT);
        // Colores 
        Bttn_home.setBackground(new Color(0, 114, 198));
        Bttn_Clientes.setBackground(new Color(0, 114, 198));
        Bttn_Empleados.setBackground(new Color(0, 114, 198));
        Bttn_Ventas.setBackground(new Color(0, 114, 198));
        Bttn_Almacen.setBackground(new Color(0, 114, 198));
        Bttn_Estadistica.setBackground(new Color(51, 153, 255));
        Bttn_Admin.setBackground(new Color(0, 114, 198));
        Bttn_Conf.setBackground(new Color(0, 114, 198));
        // Estadisticas
        Pnl_Estadisticas.Porcentaje_Cliente.setValue((int) (pocentajeCliente));
        Pnl_Estadisticas.Porcentaje_Productos.setValue((int) (pocentajeInventario));
    }//GEN-LAST:event_Bttn_EstadisticaActionPerformed

    private void Btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_logoutActionPerformed

        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Btn_logoutActionPerformed

    private void Bttn_ConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bttn_ConfActionPerformed
        pnlSlider.setPanelSlider(1, pnl_Config1, RSPanelsSlider.DIRECT.RIGHT);
    }//GEN-LAST:event_Bttn_ConfActionPerformed

    private void Bttn_AdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bttn_AdminActionPerformed
        Bttn_home.setBackground(new Color(0, 114, 198));
        Bttn_Clientes.setBackground(new Color(0, 114, 198));
        Bttn_Empleados.setBackground(new Color(0, 114, 198));
        Bttn_Ventas.setBackground(new Color(0, 114, 198));
        Bttn_Almacen.setBackground(new Color(0, 114, 198));
        Bttn_Estadistica.setBackground(new Color(0, 114, 198));
        Bttn_Admin.setBackground(new Color(51, 153, 255));
        Bttn_Conf.setBackground(new Color(0, 114, 198));
    }//GEN-LAST:event_Bttn_AdminActionPerformed

    private void Bttn_EmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bttn_EmpleadosActionPerformed
        pnlSlider.setPanelSlider(1, pnl_Empleados1, RSPanelsSlider.DIRECT.RIGHT);
        Bttn_home.setBackground(new Color(0, 114, 198));
        Bttn_Clientes.setBackground(new Color(0, 114, 198));
        Bttn_Empleados.setBackground(new Color(51, 153, 255));
        Bttn_Ventas.setBackground(new Color(0, 114, 198));
        Bttn_Almacen.setBackground(new Color(0, 114, 198));
        Bttn_Estadistica.setBackground(new Color(0, 114, 198));
        Bttn_Admin.setBackground(new Color(0, 114, 198));
        Bttn_Conf.setBackground(new Color(0, 114, 198));
    }//GEN-LAST:event_Bttn_EmpleadosActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static RSMaterialComponent.RSButtonMaterialIconUno Btn_logout;
    public static rojeru_san.rsbutton.RSButtonEffect Bttn_Admin;
    public static rojeru_san.rsbutton.RSButtonEffect Bttn_Almacen;
    public static rojeru_san.rsbutton.RSButtonEffect Bttn_Clientes;
    public static rojeru_san.rsbutton.RSButtonEffect Bttn_Conf;
    public static rojeru_san.rsbutton.RSButtonEffect Bttn_Empleados;
    public static rojeru_san.rsbutton.RSButtonEffect Bttn_Estadistica;
    public static rojeru_san.rsbutton.RSButtonEffect Bttn_Ventas;
    public static rojeru_san.rsbutton.RSButtonEffect Bttn_home;
    private javax.swing.JLabel jLabel6;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    public static javax.swing.JLabel name_empresa;
    private rojerusan.RSPanelsSlider pnlSlider;
    private panels.Pnl_Clientes pnl_Clientes1;
    private panels.Pnl_Config pnl_Config1;
    private panels.Pnl_Empleados pnl_Empleados1;
    private panels.Pnl_Estadisticas pnl_Estadisticas1;
    private panels.Pnl_home pnl_home1;
    private panels.Pnl_homeUser pnl_homeUser1;
    private panels.Pnl_inventario pnl_inventario1;
    private panels.Pnl_ventas pnl_ventas1;
    public static javax.swing.JLabel print_nameUser;
    private rojeru_san.rspanel.RSPanelImage rSPanelImage1;
    private javax.swing.JLabel texto_bienvenida;
    private javax.swing.JLabel txt_acercade;
    public static javax.swing.JLabel version;
    // End of variables declaration//GEN-END:variables

}
