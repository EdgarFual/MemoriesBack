package VentanasEmegentes;

import controlador.Cola_Categorias;
import controlador.Process_Categoria;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import static panels.Pnl_inventario.jcombo_cat;
import static panels.Pnl_inventario.jcombo_cat1;

public class Register_Categoria extends javax.swing.JDialog {

    Categoria categ;
    Process_Categoria PC = new Process_Categoria();
    Cola_Categorias CC = new Cola_Categorias();
    String[] cab = {"Nombre de Categoria"};
    DefaultTableModel modelo = new DefaultTableModel(null, cab);

    public Register_Categoria(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        Tabla_Categ.setModel(modelo);
        cargar_categoria();
    }

    private void RegisterCat() {
        Object[] registro = {txt_NameCategoria.getText()};
        categ = new Categoria(registro);
        CC.encolar(categ);
        try {
            listar();
            PC.insertarCat(categ);
        } catch (Exception e) {
        }
    }

    private void listar() {
        modelo.setRowCount(0);
        for (Categoria c : CC.listado()) {
            modelo.addRow(c.InfoCategoria());
        }
    }

    public void cargar_categoria() {
        modelo.setRowCount(0);
        try {
            PC.leerCat();
            for (Categoria a : PC.arrayCat) {
                modelo.addRow(a.InfoCategoria());
                CC.encolar(a);
                jcombo_cat.addItem(a.getDescipcion());
                jcombo_cat1.addItem(a.getDescipcion());
            }
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title_cliente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Categ = new rojerusan.RSTableMetro();
        panel_add = new javax.swing.JPanel();
        txt_NameCategoria = new rojeru_san.rsfield.RSTextMaterial();
        btn_save1 = new RSMaterialComponent.RSButtonIconUno();
        jLabel5 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        btn_add1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        title_cliente.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        title_cliente.setForeground(new java.awt.Color(255, 255, 255));
        title_cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title_cliente.setText("Formulario de Categoria");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_cliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_cliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 70));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Tabla_Categ.setBackground(new java.awt.Color(255, 255, 255));
        Tabla_Categ.setForeground(new java.awt.Color(0, 0, 0));
        Tabla_Categ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre de Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Categ.setAlignmentX(0.1F);
        Tabla_Categ.setAlignmentY(0.1F);
        Tabla_Categ.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla_Categ.setFocusTraversalPolicyProvider(true);
        Tabla_Categ.setHighHead(30);
        jScrollPane1.setViewportView(Tabla_Categ);
        if (Tabla_Categ.getColumnModel().getColumnCount() > 0) {
            Tabla_Categ.getColumnModel().getColumn(0).setResizable(false);
        }

        panel_add.setBackground(new java.awt.Color(255, 255, 255));

        txt_NameCategoria.setBackground(new java.awt.Color(255, 255, 255));
        txt_NameCategoria.setForeground(new java.awt.Color(0, 0, 0));
        txt_NameCategoria.setColorMaterial(new java.awt.Color(51, 51, 51));
        txt_NameCategoria.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        txt_NameCategoria.setPlaceholder("Ingrese el nombre de la categoria");
        txt_NameCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_NameCategoriaKeyReleased(evt);
            }
        });

        btn_save1.setBackground(new java.awt.Color(255, 255, 255));
        btn_save1.setForeground(new java.awt.Color(0, 0, 0));
        btn_save1.setBackgroundHover(new java.awt.Color(0, 114, 198));
        btn_save1.setForegroundText(new java.awt.Color(0, 0, 0));
        btn_save1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btn_save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText(" Estado de la categoria :");

        status.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        status.setForeground(new java.awt.Color(0, 0, 153));
        status.setText(" en espera...");

        javax.swing.GroupLayout panel_addLayout = new javax.swing.GroupLayout(panel_add);
        panel_add.setLayout(panel_addLayout);
        panel_addLayout.setHorizontalGroup(
            panel_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_addLayout.createSequentialGroup()
                        .addComponent(txt_NameCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_save1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_addLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_addLayout.setVerticalGroup(
            panel_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_addLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_save1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NameCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(status))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panel_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_add, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 400, 340));

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
        jPanel3.add(btn_add1, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 410, 90, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add1ActionPerformed
        jcombo_cat.removeAllItems();
        jcombo_cat.addItem("-- Seleccionar --");
        jcombo_cat1.removeAllItems();
        jcombo_cat1.addItem("-- Seleccionar --");
        cargar_categoria();
        this.dispose();
    }//GEN-LAST:event_btn_add1ActionPerformed

    private void btn_save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save1ActionPerformed
        RegisterCat();
        txt_NameCategoria.setText(null);
        status.setText("En espera...");
        status.setForeground(new Color(0, 0, 153));
    }//GEN-LAST:event_btn_save1ActionPerformed

    private void txt_NameCategoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NameCategoriaKeyReleased
        if (txt_NameCategoria.getText().isEmpty()) {
            status.setText("En espera...");
            status.setForeground(new Color(0, 0, 153));
            btn_save1.setEnabled(false);
        } else {

            categ = CC.buscar(txt_NameCategoria.getText());

            if (categ != null) {
                status.setText("Ya se encuentra registrado");
                status.setForeground(Color.red);
                btn_save1.setEnabled(false);
            } else {
                status.setText("Disponible");
                status.setForeground(Color.green);
                btn_save1.setEnabled(true);
            }
        }
    }//GEN-LAST:event_txt_NameCategoriaKeyReleased

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
            java.util.logging.Logger.getLogger(Register_Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register_Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register_Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register_Categoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Register_Categoria dialog = new Register_Categoria(new javax.swing.JFrame(), true);
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
    private rojerusan.RSTableMetro Tabla_Categ;
    private javax.swing.JButton btn_add1;
    public static RSMaterialComponent.RSButtonIconUno btn_save1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_add;
    private javax.swing.JLabel status;
    public javax.swing.JLabel title_cliente;
    private rojeru_san.rsfield.RSTextMaterial txt_NameCategoria;
    // End of variables declaration//GEN-END:variables
}
