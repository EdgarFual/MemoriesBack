package VentanasEmegentes;

import Funciones.Decimales_2Digitos;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import panels.Pnl_ventas;

public class Venta_Rapida extends javax.swing.JDialog {

    String[] cab = {"Codigo", "Descripcion", "Cantidad", "Precio U.", "Precio T."};
    public DefaultTableModel modelo = new DefaultTableModel(null, cab);
    public static String user;
    LocalDate FechaActual = LocalDate.now();

    // Decimales
    Decimales_2Digitos d2 = new Decimales_2Digitos();

    public Venta_Rapida(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        btn_agregar.setEnabled(false);
        Columnas();
    }

    //
    private void Columnas() {
        Pnl_ventas.Tabla_Ventas.getColumnModel().getColumn(0).setPreferredWidth(100);
        Pnl_ventas.Tabla_Ventas.getColumnModel().getColumn(0).setResizable(false);
        Pnl_ventas.Tabla_Ventas.getColumnModel().getColumn(1).setPreferredWidth(350);
        Pnl_ventas.Tabla_Ventas.getColumnModel().getColumn(1).setResizable(false);
        Pnl_ventas.Tabla_Ventas.getColumnModel().getColumn(2).setPreferredWidth(50);
        Pnl_ventas.Tabla_Ventas.getColumnModel().getColumn(2).setResizable(false);
    }

    public void RealizarCompra(boolean s) {
        Pnl_ventas.Cod_Producto.setVisible(s);
        Pnl_ventas.btn_searchProducto.setVisible(s);
        Pnl_ventas.Descripcion_Producto.setVisible(s);
        Pnl_ventas.cantidad_producto.setVisible(s);
        Pnl_ventas.precio_producto.setVisible(s);
        Pnl_ventas.btn_add.setVisible(s);
    }

    // Calculando IGV y Subtotal
    public void ValoresVenta() {

        BigDecimal bd = new BigDecimal(Double.parseDouble(Importe_rapida.getText()));
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        Process_store.Importe_Venta = bd.doubleValue();
        //agregando segundo decimal en ocacion exactas en el total

        Pnl_ventas.jtxtPrecioTotal.setText(d2.Decimal_DobleS(Double.parseDouble(Importe_rapida.getText())));
        double Subtotal = (Double.parseDouble(Importe_rapida.getText()) / 1.18);
        //agregando segundo decimal en ocacion exactas en el subtotal
        Pnl_ventas.jtxtSubTotal.setText(d2.Decimal_DobleS(Subtotal));

        //agregando segundo decimal en ocacion exactas en el I.G.V
        double igv = (Double.parseDouble(Importe_rapida.getText()) - Subtotal);
        Pnl_ventas.jtxtIGV.setText(d2.Decimal_DobleS(igv));

        System.out.println("Subtotal: " + Subtotal + " | IGV :" + igv);
        // Colocando valores en tabla
        Object[] fast = {"MB9999", "Venta Rapida (" + FechaActual + ")", 1, Pnl_ventas.jtxtPrecioTotal.getText(), Pnl_ventas.jtxtPrecioTotal.getText()};
        modelo.addRow(fast);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title_cliente = new javax.swing.JLabel();
        btn_add1 = new javax.swing.JButton();
        Importe_rapida = new rojeru_san.rsfield.RSTextMaterial();
        btn_agregar = new RSMaterialComponent.RSButtonIconUno();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        title_cliente.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        title_cliente.setForeground(new java.awt.Color(255, 255, 255));
        title_cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title_cliente.setText("Venta Rapida");

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

        Importe_rapida.setBackground(new java.awt.Color(255, 255, 255));
        Importe_rapida.setForeground(new java.awt.Color(0, 0, 0));
        Importe_rapida.setColorMaterial(new java.awt.Color(51, 51, 51));
        Importe_rapida.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        Importe_rapida.setPlaceholder("Ingrese el Importe de la Compra");
        Importe_rapida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Importe_rapidaKeyReleased(evt);
            }
        });

        btn_agregar.setBackground(new java.awt.Color(255, 255, 255));
        btn_agregar.setForeground(new java.awt.Color(0, 0, 0));
        btn_agregar.setBackgroundHover(new java.awt.Color(0, 114, 198));
        btn_agregar.setForegroundText(new java.awt.Color(0, 0, 0));
        btn_agregar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("S/.");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText(" Estado de venta :");

        status.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        status.setForeground(new java.awt.Color(0, 0, 153));
        status.setText(" Ingrese el importe desde S/. 5.00 hasta S/ 150.00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_add1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Importe_rapida, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Importe_rapida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(status))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_add1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_add1ActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        ValoresVenta();
        RealizarCompra(false);
        Pnl_ventas.Tabla_Ventas.setModel(modelo);
        Pnl_ventas.Panel_Pagos.setVisible(true);
        Pnl_ventas.btn_GenerarCompra.setVisible(false);
        Columnas();
        dispose();
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void Importe_rapidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Importe_rapidaKeyReleased
        btn_agregar.setEnabled(false);
        try {
            if (Importe_rapida.getText().isEmpty() || Double.parseDouble(Importe_rapida.getText()) == 0) {

                status.setForeground(new Color(0, 0, 153));
                status.setText(" Ingrese el importe desde S/. 5.00 hasta S/ 150.00");
            } else if (Double.parseDouble(Importe_rapida.getText()) <= 150 && Double.parseDouble(Importe_rapida.getText()) >= 5) {
                btn_agregar.setEnabled(true);
                status.setForeground(new Color(0, 102, 0));
                status.setText("Monto aceptable || máximo de venta rápida S/. 150.00");
            } else if (Double.parseDouble(Importe_rapida.getText()) < 5) {
                status.setText("Monto no aceptado");
                status.setForeground(Color.red);
            } else {
                status.setText("El monto supera el límite de la venta rápida (S/. 150.00)");
                status.setForeground(Color.red);
            }
        } catch (Exception e) {
            status.setText("Monto no aceptado");
            status.setForeground(Color.red);
        }
    }//GEN-LAST:event_Importe_rapidaKeyReleased

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
            java.util.logging.Logger.getLogger(Venta_Rapida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Venta_Rapida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Venta_Rapida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Venta_Rapida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Venta_Rapida dialog = new Venta_Rapida(new javax.swing.JFrame(), true);
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
    private rojeru_san.rsfield.RSTextMaterial Importe_rapida;
    private javax.swing.JButton btn_add1;
    public static RSMaterialComponent.RSButtonIconUno btn_agregar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel status;
    public javax.swing.JLabel title_cliente;
    // End of variables declaration//GEN-END:variables
}
