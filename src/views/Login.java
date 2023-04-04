package views;

import controlador.ListaDoble_Configuracion;
import controlador.Process_factorySettings;
import java.awt.Color;
import message.Exit;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.UIManager;
import modelo.factory_settings;

public class Login extends javax.swing.JFrame {

    Process_factorySettings fs = new Process_factorySettings();
    ListaDoble_Configuracion LD_fs = new ListaDoble_Configuracion();
    factory_settings cfs;

    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Cargar_Login();
        txt_user.requestFocus();
        _Version_.setText("Version 2.1.0");
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/ceo_1.png"));
        return retValue;
    }

    // Cargar 
    private void Cargar_Login() {
        try {
            fs.leer();
            fs.lista.forEach(f -> {
                Object[] fila = {f.getCodigoEmpleado(), f.getName_user(), f.getPassword_user(),
                    f.getType_of_user() + f.getCompany_name(),
                    f.getRegistered_by(), f.getRegistration_date(), f.getRegistration_time()};
                LD_fs.insertar(f);
            });
        } catch (IOException e) {
            System.out.println(":: Error al cargar los datos del Login | " + e);
        }
    }

    // Limpieza de campos
    public void LimpiarCampos() {
        txt_user.setText("");
        txt_password.setText("");
        txt_user.requestFocus();
    }

    // Inicio de Frame Principal
    private void Start() {

        if (txt_user.getText().isEmpty() || txt_password.getText().isEmpty()) {
            print_Message.setText("Debe completar todos los campos");
            print_Message.setForeground(Color.red);
            LimpiarCampos();

        } else {

            try {

                cfs = LD_fs.buscar(txt_user.getText().trim());
                if (cfs != null) {

                    // Validando Datos del Login
                    if (txt_user.getText().equals(cfs.getName_user()) && txt_password.getText().equals(cfs.getPassword_user())) {

                        if (cfs.getType_of_user().equals("Administrador")) {
                            new Ventana_Administrador().setVisible(true);
                            Ventana_Administrador.print_nameUser.setText(cfs.getType_of_user());
                            Ventana_Administrador.name_empresa.setText(cfs.getCompany_name());
                            Ventana_Administrador.user = cfs.getName_user();
                            Ventana_Administrador.TipoUsuario = cfs.getType_of_user();
                            LimpiarCampos();
                            this.dispose();
                        } else {

                        }

                    } else {
                        print_Message.setText("Usuario y/o Contraseña incorrecta");
                        print_Message.setForeground(Color.red);
                        LimpiarCampos();
                    }

                }

            } catch (Exception e) {
                System.out.println(":: Error en Login al mostrar frame principal | " + e);
                new FrameError().setVisible(true);
                this.dispose();
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_color = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        rSLabelImage2 = new rojerusan.RSLabelImage();
        txt_user = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Btn_exit = new javax.swing.JButton();
        Btn_Login = new javax.swing.JButton();
        _Version_ = new javax.swing.JLabel();
        print_Message = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rSLabelImage1 = new rojerusan.RSLabelImage();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        panel_color.setBackground(new java.awt.Color(0, 114, 198));

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Iniciar Sesión");

        rSLabelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Login.png"))); // NOI18N

        javax.swing.GroupLayout panel_colorLayout = new javax.swing.GroupLayout(panel_color);
        panel_color.setLayout(panel_colorLayout);
        panel_colorLayout.setHorizontalGroup(
            panel_colorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_colorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );
        panel_colorLayout.setVerticalGroup(
            panel_colorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_colorLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(panel_colorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(rSLabelImage2, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addContainerGap())
        );

        txt_user.setBackground(new java.awt.Color(255, 255, 255));
        txt_user.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        txt_user.setForeground(new java.awt.Color(51, 51, 51));
        txt_user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_user.setToolTipText("Ingrese su nombre de Usuario");
        txt_user.setBorder(null);

        txt_password.setBackground(new java.awt.Color(255, 255, 255));
        txt_password.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        txt_password.setForeground(new java.awt.Color(51, 51, 51));
        txt_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_password.setToolTipText("Ingrese su contraseña");
        txt_password.setBorder(null);

        Btn_exit.setBackground(new java.awt.Color(165, 29, 34));
        Btn_exit.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Btn_exit.setForeground(new java.awt.Color(255, 255, 255));
        Btn_exit.setText("Salir");
        Btn_exit.setBorderPainted(false);
        Btn_exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_exitActionPerformed(evt);
            }
        });

        Btn_Login.setBackground(new java.awt.Color(0, 114, 198));
        Btn_Login.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        Btn_Login.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Login.setText("Acceder");
        Btn_Login.setToolTipText("Presione para acceder al sistema");
        Btn_Login.setBorderPainted(false);
        Btn_Login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Btn_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LoginActionPerformed(evt);
            }
        });

        _Version_.setBackground(new java.awt.Color(255, 255, 255));
        _Version_.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        _Version_.setForeground(new java.awt.Color(51, 51, 51));
        _Version_.setText("  Versión 2.0.1");

        print_Message.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        print_Message.setForeground(new java.awt.Color(255, 255, 255));
        print_Message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        print_Message.setText("Mensagge");

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Contraseña :");

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Usuario :");

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Store.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_color, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(Btn_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                .addGap(39, 39, 39))
                            .addComponent(print_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(_Version_, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel_color, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(txt_password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(print_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Btn_Login, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(Btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(_Version_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_exitActionPerformed
        new Exit(new javax.swing.JFrame(), true).setVisible(true);
        txt_user.requestFocus();
    }//GEN-LAST:event_Btn_exitActionPerformed

    private void Btn_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LoginActionPerformed
        Start();
    }//GEN-LAST:event_Btn_LoginActionPerformed

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
                    javax.swing.UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton Btn_Login;
    private javax.swing.JButton Btn_exit;
    private javax.swing.JLabel _Version_;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    public static javax.swing.JPanel panel_color;
    private javax.swing.JLabel print_Message;
    private rojerusan.RSLabelImage rSLabelImage1;
    private rojerusan.RSLabelImage rSLabelImage2;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_user;
    // End of variables declaration//GEN-END:variables
}
