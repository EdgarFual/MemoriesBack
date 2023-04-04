package VentanasEmegentes;

import controlador.ListaCircular_Inventario;
import controlador.Process_Inventario;
import javax.swing.JOptionPane;
import modelo.Inventario;

public class Busqueda_ProductosBasic extends javax.swing.JDialog {

    Inventario invent;
    Process_Inventario processInvent = new Process_Inventario();
    ListaCircular_Inventario listCircInv = new ListaCircular_Inventario();

    public Busqueda_ProductosBasic(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        CargarInventario();
    }

    // Cargar Clientes
    private void CargarInventario() {
        try {
            processInvent.leer();
            for (Inventario inve : processInvent.lista) {
                listCircInv.insertar(inve);
            }
        } catch (Exception e) {
            System.out.println("error al Cargar el inventario : " + e);
        }
    }

    // Limpieza
    private void limpieza() {
        txt_codigo.setText(null);
        txt_descripcion.setText(null);
        txt_PrecioUnitario.setText(null);
        txt_stock.setText(null);
        txt_categoria.setText(null);
    }

    // Busqueda
    private void search() {
        invent = listCircInv.buscar(txt_codigo.getText());
        if (invent != null) {
            txt_descripcion.setText(invent.getDescripcion());
            txt_PrecioUnitario.setText(String.valueOf(invent.getPrecio()));
            txt_stock.setText(String.valueOf(invent.getCantidad_Stock()));
            txt_categoria.setText(invent.getCategoria());
        } else {
            JOptionPane.showMessageDialog(null, "El Nro de Documento: " + txt_codigo.getText() + ". No existe");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title_cliente = new javax.swing.JLabel();
        close = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        rSButtonIconUno1 = new RSMaterialComponent.RSButtonIconUno();
        rSButtonIconUno3 = new RSMaterialComponent.RSButtonIconUno();
        jLabel32 = new javax.swing.JLabel();
        txt_codigo = new RSMaterialComponent.RSTextFieldTwo();
        jLabel34 = new javax.swing.JLabel();
        txt_stock = new RSMaterialComponent.RSTextFieldTwo();
        jLabel35 = new javax.swing.JLabel();
        txt_PrecioUnitario = new RSMaterialComponent.RSTextFieldTwo();
        jLabel36 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_categoria = new RSMaterialComponent.RSTextFieldTwo();
        txt_descripcion = new RSMaterialComponent.RSTextFieldTwo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 55));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        title_cliente.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        title_cliente.setForeground(new java.awt.Color(255, 255, 255));
        title_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_cliente.setText("Consulta de Producto");

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
        jLabel32.setText("Código de Producto :");

        txt_codigo.setForeground(new java.awt.Color(0, 0, 0));
        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigo.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_codigo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_codigo.setPhColor(new java.awt.Color(51, 51, 51));
        txt_codigo.setPlaceholder("Ingrese Codigo");

        jLabel34.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Stock Vigente :");

        txt_stock.setEditable(false);
        txt_stock.setForeground(new java.awt.Color(0, 0, 0));
        txt_stock.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_stock.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_stock.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_stock.setPhColor(new java.awt.Color(51, 51, 51));
        txt_stock.setPlaceholder("stock vigente en inventario");

        jLabel35.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Precio Unitario:");

        txt_PrecioUnitario.setEditable(false);
        txt_PrecioUnitario.setForeground(new java.awt.Color(0, 0, 0));
        txt_PrecioUnitario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_PrecioUnitario.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_PrecioUnitario.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_PrecioUnitario.setPhColor(new java.awt.Color(51, 51, 51));
        txt_PrecioUnitario.setPlaceholder("Precio unitario");

        jLabel36.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Descripción: ");

        jLabel38.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Categoria:");

        txt_categoria.setEditable(false);
        txt_categoria.setForeground(new java.awt.Color(0, 0, 0));
        txt_categoria.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_categoria.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_categoria.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_categoria.setPhColor(new java.awt.Color(51, 51, 51));
        txt_categoria.setPlaceholder("categoria ");

        txt_descripcion.setEditable(false);
        txt_descripcion.setForeground(new java.awt.Color(0, 0, 0));
        txt_descripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_descripcion.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_descripcion.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_descripcion.setPhColor(new java.awt.Color(51, 51, 51));
        txt_descripcion.setPlaceholder("Descripcion del producto");

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
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonIconUno1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonIconUno3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_PrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSButtonIconUno1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonIconUno3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_PrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void rSButtonIconUno1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno1ActionPerformed
        search();
    }//GEN-LAST:event_rSButtonIconUno1ActionPerformed

    private void rSButtonIconUno3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonIconUno3ActionPerformed
        limpieza();
    }//GEN-LAST:event_rSButtonIconUno3ActionPerformed

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
            java.util.logging.Logger.getLogger(Busqueda_ProductosBasic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Busqueda_ProductosBasic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Busqueda_ProductosBasic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Busqueda_ProductosBasic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Busqueda_ProductosBasic dialog = new Busqueda_ProductosBasic(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton close;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno1;
    private RSMaterialComponent.RSButtonIconUno rSButtonIconUno3;
    public javax.swing.JLabel title_cliente;
    private RSMaterialComponent.RSTextFieldTwo txt_PrecioUnitario;
    private RSMaterialComponent.RSTextFieldTwo txt_categoria;
    private RSMaterialComponent.RSTextFieldTwo txt_codigo;
    private RSMaterialComponent.RSTextFieldTwo txt_descripcion;
    private RSMaterialComponent.RSTextFieldTwo txt_stock;
    // End of variables declaration//GEN-END:variables
}
