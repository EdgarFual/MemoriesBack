package VentanasEmegentes;

import Funciones.Decimales_2Digitos;
import com.sun.awt.AWTUtilities;
import controlador.Cola_Ventas;
import controlador.Process_Ventas;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Message_confirmacion;
import modelo.Venta;
import views.Comprobante;

public class Busqueda_Comprobante extends javax.swing.JDialog {

    // Decimales
    Decimales_2Digitos d2 = new Decimales_2Digitos();

    // Ventas
    Venta v;
    Process_Ventas PV = new Process_Ventas();
    Cola_Ventas CV = new Cola_Ventas();
    
    String Nro_Comproban;
    
    public String barra = File.separator; //Es lo mismo que decir \\

    public Busqueda_Comprobante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        AWTUtilities.setWindowOpaque(this, false);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        panel_resultados.setVisible(false);
        message.setVisible(false);
        btn_anular.setVisible(false);
        panel_anulacionC.setVisible(false);
        cargar_ventas();
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
    
    private void lectura() {
        
        File archivo = new File("Comprobantes" + barra + Nro_Comproban + ".txt");
        
        try {
            
            BufferedReader leer = new BufferedReader(new FileReader(archivo));
            String linea = leer.readLine();
            
            while (linea != null) {
                _temporal_.append(linea + "\n");
                linea = leer.readLine();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            Message_confirmacion.Message = "<html>El documento del comprobante no se encuentra disponible y/o"
                    + "ha sido eliminado</html>";
            Message_confirmacion.tipo_message = "error";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        _temporal_ = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        title_cliente = new javax.swing.JLabel();
        btn_exit = new javax.swing.JButton();
        _numero_ = new rojeru_san.rsfield.RSTextMaterial();
        btn_agregar = new RSMaterialComponent.RSButtonIconUno();
        _serie_ = new rojeru_san.rsfield.RSTextMaterial();
        jLabel6 = new javax.swing.JLabel();
        panel_resultados = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        usuario = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fecha_emision = new javax.swing.JLabel();
        Hora_Emision = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nro_Operacion = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Medio_Pago = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Importe_pago = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        status7 = new javax.swing.JLabel();
        btn_views = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_close = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_anular = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        message = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tipo_comprobante = new rojerusan.RSComboMetro();
        panel_anulacionC = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        _numero_1 = new rojeru_san.rsfield.RSTextMaterial();
        btn_views2 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_close1 = new RSMaterialComponent.RSButtonMaterialRippleIcon();

        _temporal_.setColumns(20);
        _temporal_.setRows(5);
        jScrollPane1.setViewportView(_temporal_);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        title_cliente.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        title_cliente.setForeground(new java.awt.Color(255, 255, 255));
        title_cliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title_cliente.setText("Consulta de Comprobante");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_cliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_exit.setBackground(new java.awt.Color(255, 51, 51));
        btn_exit.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        btn_exit.setForeground(new java.awt.Color(255, 255, 255));
        btn_exit.setText("cerrar");
        btn_exit.setBorder(null);
        btn_exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        _numero_.setBackground(new java.awt.Color(255, 255, 255));
        _numero_.setForeground(new java.awt.Color(0, 0, 0));
        _numero_.setColorMaterial(new java.awt.Color(51, 51, 51));
        _numero_.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        _numero_.setPlaceholder("000000");
        _numero_.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                _numero_KeyReleased(evt);
            }
        });

        btn_agregar.setBackground(new java.awt.Color(255, 255, 255));
        btn_agregar.setForeground(new java.awt.Color(0, 0, 0));
        btn_agregar.setBackgroundHover(new java.awt.Color(0, 114, 198));
        btn_agregar.setForegroundText(new java.awt.Color(0, 0, 0));
        btn_agregar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        _serie_.setBackground(new java.awt.Color(255, 255, 255));
        _serie_.setForeground(new java.awt.Color(0, 0, 0));
        _serie_.setColorMaterial(new java.awt.Color(51, 51, 51));
        _serie_.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        _serie_.setPlaceholder("B001");
        _serie_.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                _serie_KeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText(":: Numero de Comprobante:");

        panel_resultados.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText(":: Registrado por:");

        usuario.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        usuario.setForeground(new java.awt.Color(102, 102, 102));
        usuario.setText("_user_");

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText(":: Fecha de Emision:");

        fecha_emision.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        fecha_emision.setForeground(new java.awt.Color(102, 102, 102));
        fecha_emision.setText("0000-00-00");

        Hora_Emision.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        Hora_Emision.setForeground(new java.awt.Color(102, 102, 102));
        Hora_Emision.setText("00:00:00");

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText(":: Hora de Emision:");

        nro_Operacion.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        nro_Operacion.setForeground(new java.awt.Color(102, 102, 102));
        nro_Operacion.setText("en espera...");

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText(":: Numero de Operación :");

        Medio_Pago.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        Medio_Pago.setForeground(new java.awt.Color(102, 102, 102));
        Medio_Pago.setText("en espera...");

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText(":: Medio de pago :");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText(":: Importe Registrado :");

        Importe_pago.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        Importe_pago.setForeground(new java.awt.Color(102, 102, 102));
        Importe_pago.setText("en espera...");

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText(":: Estado del comprobante :");

        status7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        status7.setForeground(new java.awt.Color(102, 102, 102));
        status7.setText("Registrado y procesado");

        btn_views.setBackground(new java.awt.Color(0, 114, 198));
        btn_views.setText("Visualizar");
        btn_views.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.VISIBILITY);
        btn_views.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewsActionPerformed(evt);
            }
        });

        btn_close.setBackground(new java.awt.Color(255, 51, 51));
        btn_close.setText(" Cerrar");
        btn_close.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        btn_anular.setBackground(new java.awt.Color(255, 255, 255));
        btn_anular.setText(" Anular comprobante");
        btn_anular.setForegroundIcon(new java.awt.Color(102, 102, 102));
        btn_anular.setForegroundText(new java.awt.Color(102, 102, 102));
        btn_anular.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        btn_anular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_resultadosLayout = new javax.swing.GroupLayout(panel_resultados);
        panel_resultados.setLayout(panel_resultadosLayout);
        panel_resultadosLayout.setHorizontalGroup(
            panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_resultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_anular, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_resultadosLayout.createSequentialGroup()
                        .addComponent(btn_views, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fecha_emision, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(usuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Hora_Emision, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nro_Operacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Medio_Pago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Importe_pago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(status7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_resultadosLayout.setVerticalGroup(
            panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_resultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(nro_Operacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(Medio_Pago, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Importe_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(status7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(fecha_emision, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Hora_Emision, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_resultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_views, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_anular, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        message.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        message.setForeground(new java.awt.Color(153, 0, 0));
        message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        message.setText("En numero de comprobante ingresado no se encuentra registrado");

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText(":: Tipo de Comprobante:");

        tipo_comprobante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Boleta", "Factura" }));

        panel_anulacionC.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText(":: Seleccione el motivo :");

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(0, 0, 0));
        jRadioButton1.setText(" Importe Incorrecto ");

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(0, 0, 0));
        jRadioButton2.setText(" Realiza devolución");

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(0, 0, 0));
        jRadioButton3.setText(" Nueva compra");

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(0, 0, 0));
        jRadioButton4.setText(" Otros motivos");

        _numero_1.setBackground(new java.awt.Color(255, 255, 255));
        _numero_1.setForeground(new java.awt.Color(0, 0, 0));
        _numero_1.setColorMaterial(new java.awt.Color(51, 51, 51));
        _numero_1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        _numero_1.setPlaceholder("ingresa el motivo");
        _numero_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                _numero_1KeyReleased(evt);
            }
        });

        btn_views2.setBackground(new java.awt.Color(0, 114, 198));
        btn_views2.setText(" Guardar");
        btn_views2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btn_views2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_views2ActionPerformed(evt);
            }
        });

        btn_close1.setBackground(new java.awt.Color(255, 51, 51));
        btn_close1.setText(" Cerrar");
        btn_close1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        btn_close1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_close1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_anulacionCLayout = new javax.swing.GroupLayout(panel_anulacionC);
        panel_anulacionC.setLayout(panel_anulacionCLayout);
        panel_anulacionCLayout.setHorizontalGroup(
            panel_anulacionCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_anulacionCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_anulacionCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_anulacionCLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panel_anulacionCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_anulacionCLayout.createSequentialGroup()
                                .addComponent(jRadioButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(_numero_1, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(panel_anulacionCLayout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton3)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_anulacionCLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_views2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_anulacionCLayout.setVerticalGroup(
            panel_anulacionCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_anulacionCLayout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_anulacionCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_anulacionCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton4)
                    .addComponent(_numero_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_anulacionCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_views2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel_resultados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(_serie_, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(_numero_, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7)
                                        .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tipo_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel_anulacionC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tipo_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(_numero_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(_serie_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_resultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_anulacionC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        
        if ("-- Seleccione --".equals(tipo_comprobante.getSelectedItem().toString()) || _serie_.getText().isEmpty() || _numero_.getText().isEmpty()) {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>La seleccion del tipo de comprobante y el número son obligatorios</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        } else {
            
            Nro_Comproban = "" + _serie_.getText() + " - " + _numero_.getText();
            v = CV.buscar(Nro_Comproban);
            
            if (v != null) {
                nro_Operacion.setText(v.getNro_Operacion());
                Medio_Pago.setText(v.getMedio_Pago());
                Importe_pago.setText("S/. " + d2.Decimal_DobleS(v.getTotal()));
                usuario.setText(v.getUsuario());
                fecha_emision.setText(v.getFecha_Reg());
                Hora_Emision.setText(v.getHora_Reg());
                panel_resultados.setVisible(true);
                btn_exit.setVisible(false);
                message.setVisible(false);
            } else {
                message.setVisible(true);
                panel_resultados.setVisible(false);
                btn_exit.setVisible(true);
            }
            
        }

    }//GEN-LAST:event_btn_agregarActionPerformed

    private void _numero_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event__numero_KeyReleased

    }//GEN-LAST:event__numero_KeyReleased

    private void _serie_KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event__serie_KeyReleased
        if ("Boleta".equals(tipo_comprobante.getSelectedItem().toString())) {
            if (_serie_.getText().length() == 4) {
                _numero_.requestFocus(true);
            }
        } else {
            if (_serie_.getText().length() == 3) {
                _numero_.requestFocus(true);
            }
        }

    }//GEN-LAST:event__serie_KeyReleased

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_viewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewsActionPerformed
        lectura();
        Comprobante.accion = "visualizar";
        new Comprobante(new javax.swing.JFrame(), true).setVisible(true);
        _temporal_.setText(null);
    }//GEN-LAST:event_btn_viewsActionPerformed

    private void btn_anularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anularActionPerformed
        btn_exit.setVisible(false);
        btn_close.setVisible(false);
        panel_anulacionC.setVisible(true);
    }//GEN-LAST:event_btn_anularActionPerformed

    private void _numero_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event__numero_1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event__numero_1KeyReleased

    private void btn_views2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_views2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_views2ActionPerformed

    private void btn_close1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_close1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_close1ActionPerformed

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
            java.util.logging.Logger.getLogger(Busqueda_Comprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Busqueda_Comprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Busqueda_Comprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Busqueda_Comprobante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Busqueda_Comprobante dialog = new Busqueda_Comprobante(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Hora_Emision;
    private javax.swing.JLabel Importe_pago;
    private javax.swing.JLabel Medio_Pago;
    private rojeru_san.rsfield.RSTextMaterial _numero_;
    private rojeru_san.rsfield.RSTextMaterial _numero_1;
    private rojeru_san.rsfield.RSTextMaterial _serie_;
    public static javax.swing.JTextArea _temporal_;
    public static RSMaterialComponent.RSButtonIconUno btn_agregar;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_anular;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_close;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_close1;
    private javax.swing.JButton btn_exit;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_views;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_views2;
    private javax.swing.JLabel fecha_emision;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel message;
    private javax.swing.JLabel nro_Operacion;
    private javax.swing.JPanel panel_anulacionC;
    private javax.swing.JPanel panel_resultados;
    private javax.swing.JLabel status7;
    private rojerusan.RSComboMetro tipo_comprobante;
    public javax.swing.JLabel title_cliente;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
}
