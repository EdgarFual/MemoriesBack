package panels;

import controlador.ListaCircular_Empleados;
import controlador.Process_Empleados;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import message.Message_confirmacion;
import modelo.Empleados;
import views.Ventana_Administrador;

public class Pnl_Empleados extends javax.swing.JPanel {

    LocalDate FechaActual = LocalDate.now();
    LocalTime horaActual = LocalTime.now();

    Empleados Empleado;
    Process_Empleados pro = new Process_Empleados();
    ListaCircular_Empleados LCE = new ListaCircular_Empleados();

    String[] cab = {"Cod. Interno", "Documento", "Nro. Documento", "Nombres", "Apellido", "Sexo", "Celular", "E-mail", "Cargo"};
    public DefaultTableModel modeloEmpleados = new DefaultTableModel(null, cab);

    double ConteoMasculino = 0, ConteoFemenino = 0;
    String Cod_Interno;

    public Pnl_Empleados() {
        initComponents();
        Tabla_Empleados.setModel(modeloEmpleados);
        panels.Pnl_Estadisticas.Tabla_Emple.setModel(modeloEmpleados);
        Panel_resumen.setVisible(false);
        Panel_resumen1.setVisible(false);
        btn_Status.setVisible(false);
        jButton1.setVisible(false);
        cargarArchivo();
        Reporte();
    }

    // Cargar Lista
    private void cargarArchivo() {
        modeloEmpleados.setRowCount(0);
        try {
            pro.leer();
            for (Empleados e : pro.listado) {
                modeloEmpleados.addRow(e.InformacionEmpleados());
                LCE.insertar(e);
            }
        } catch (Exception e) {
        }
    }

    // Generador Codigo Interno
    private String Cod_Empleado() {
        if (Tabla_Empleados.getRowCount() == 0) {
            return String.valueOf(FechaActual).substring(0, 4) + 10000;
        } else {
            return String.valueOf(FechaActual).substring(0, 4) + (10000 + Tabla_Empleados.getRowCount());
        }
    }

    // Limpieza de Search Editar
    private void DatosEditar() {
        Panel_resumen.setVisible(false);
        dato_Busqueda.setText(null);
        Panel_resumen1.setVisible(false);
    }

    // Listar en Tabla
    public void listar() {
        modeloEmpleados.setRowCount(0);
        for (Empleados e : LCE.listado()) {
            modeloEmpleados.addRow(e.InformacionEmpleados());
        }
    }

    // Limpieza de campos
    private void LimpiezaCampos() {
        tipo_documento.setSelectedIndex(0);
        txt_DNI.setText(null);
        jDateChooser1.setDate(null);
        name.setText(null);
        lastname.setText(null);
        sexo.setSelectedIndex(0);
        EstadoCivil.setSelectedIndex(0);
        domicilio.setText(null);
        phone.setText(null);
        Operador_Movil.setSelectedIndex(0);
        correo.setText(null);
        ocupacion.setSelectedIndex(0);
        cargo.setSelectedIndex(0);
    }

    // reporte de Empleados
    private void Reporte() {
        System.out.println("Cod. generado automatico para empleado " + Cod_Empleado());
        Total_Empleados.setText(String.valueOf(Tabla_Empleados.getRowCount()));
        panels.Pnl_home.nro_Empleados.setText(String.valueOf(Tabla_Empleados.getRowCount()));

        for (int i = 0; i < Tabla_Empleados.getRowCount(); i++) {
            if ("Masculino".equals(Tabla_Empleados.getValueAt(i, 5).toString())) {
                ConteoMasculino++;
            } else {
                ConteoFemenino++;
            }
        }

        Mas.setText(String.valueOf((int) ConteoMasculino));
        Fem.setText(String.valueOf((int) ConteoFemenino));

        if (Integer.parseInt(Total_Empleados.getText()) == 0) {
            _Masculino_.setValue(0);
            _Femenino_.setValue(0);
        } else {
            int PorceSexo = ((int) ((ConteoMasculino / Integer.parseInt(Total_Empleados.getText())) * 100) + ((int) ((ConteoFemenino / Integer.parseInt(Total_Empleados.getText())) * 100)));
            if (PorceSexo == 100) {
                _Masculino_.setValue((int) ((ConteoMasculino / Integer.parseInt(Total_Empleados.getText())) * 100));
            } else {
                _Masculino_.setValue(((int) ((ConteoMasculino / Integer.parseInt(Total_Empleados.getText())) * 100)) + (100 - PorceSexo));
            }
            _Femenino_.setValue((int) ((ConteoFemenino / Integer.parseInt(Total_Empleados.getText())) * 100));
        }

    }

    // Editar
    private void Update() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String FechaNacimiento = sdf.format(jDateChooser2.getDate());

        Object[] Registro = {Cod_Interno, tipo_documento1.getSelectedItem().toString(), txt_DNI1.getText(), FechaNacimiento,
            name1.getText().toUpperCase(), lastname1.getText().toUpperCase(), sexo1.getSelectedItem().toString(),
            EstadoCivil1.getSelectedItem().toString(), domicilio1.getText().toUpperCase(), Integer.parseInt(phone1.getText()),
            Operador_Movil1.getSelectedItem().toString(), correo1.getText().toLowerCase(),
            ocupacion1.getSelectedItem().toString(), cargo1.getSelectedItem().toString(), "Activo",
            Ventana_Administrador.user, FechaActual, String.valueOf(horaActual).substring(0, 8)};
        Empleado = new Empleados(Registro);
        LCE.actualizar(Empleado);
        listar();
        Message_confirmacion.tipo_message = "confirmacion";
        Message_confirmacion.Message = "<html>Los datos del empleado se han actualizado con exito</html>";
        new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        try {
            pro.actualizar(LCE.listado());
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Tipo_Busqueda = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        rSLabelFecha1 = new rojeru_san.rsdate.RSLabelFecha();
        jPanel14 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        Total_Empleados = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        Mas = new javax.swing.JLabel();
        rSLabelImage1 = new rojeru_san.rslabel.RSLabelImage();
        _Masculino_ = new rojerusan.componentes.RSProgressCircle();
        jPanel17 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        Fem = new javax.swing.JLabel();
        rSLabelImage3 = new rojeru_san.rslabel.RSLabelImage();
        _Femenino_ = new rojerusan.componentes.RSProgressCircle();
        jSeparator13 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        btn_Register = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_Status = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_update = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_consultar = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_Listado = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        panel_home = new javax.swing.JPanel();
        rSLabelImage2 = new rojeru_san.rslabel.RSLabelImage();
        panel_registroEmpleado = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        tipo_documento = new rojerusan.RSComboMetro();
        jLabel3 = new javax.swing.JLabel();
        txt_DNI = new RSMaterialComponent.RSTextFieldTwo();
        jLabel4 = new javax.swing.JLabel();
        name = new RSMaterialComponent.RSTextFieldTwo();
        jLabel6 = new javax.swing.JLabel();
        lastname = new RSMaterialComponent.RSTextFieldTwo();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        sexo = new rojerusan.RSComboMetro();
        jLabel10 = new javax.swing.JLabel();
        EstadoCivil = new rojerusan.RSComboMetro();
        jLabel11 = new javax.swing.JLabel();
        domicilio = new RSMaterialComponent.RSTextFieldTwo();
        jLabel12 = new javax.swing.JLabel();
        phone = new RSMaterialComponent.RSTextFieldTwo();
        jLabel13 = new javax.swing.JLabel();
        Operador_Movil = new rojerusan.RSComboMetro();
        jLabel14 = new javax.swing.JLabel();
        correo = new RSMaterialComponent.RSTextFieldTwo();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        ocupacion = new rojerusan.RSComboMetro();
        jLabel19 = new javax.swing.JLabel();
        cargo = new rojerusan.RSComboMetro();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        rSButtonMaterialRippleIcon1 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        rSButtonMaterialRippleIcon6 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        panel_ListadoRegistros = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla_Empleados = new rojerusan.RSTableMetro();
        panel_EditarCliente = new javax.swing.JPanel();
        Panel_resumen = new javax.swing.JPanel();
        correo1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel35 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        phone1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel33 = new javax.swing.JLabel();
        domicilio1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel32 = new javax.swing.JLabel();
        EstadoCivil1 = new rojerusan.RSComboMetro();
        jLabel31 = new javax.swing.JLabel();
        sexo1 = new rojerusan.RSComboMetro();
        tipo_documento1 = new rojerusan.RSComboMetro();
        jLabel24 = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        Operador_Movil1 = new rojerusan.RSComboMetro();
        jLabel30 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel39 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        cargo1 = new rojerusan.RSComboMetro();
        lastname1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel38 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        ocupacion1 = new rojerusan.RSComboMetro();
        name1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel37 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        txt_DNI1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel36 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        rSButtonMaterialRippleIcon2 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        rSButtonMaterialRippleIcon4 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        jPanel5 = new javax.swing.JPanel();
        jSeparator25 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        jSeparator26 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        Codigo_Interno = new javax.swing.JRadioButton();
        Nro_Documento = new javax.swing.JRadioButton();
        dato_Busqueda = new RSMaterialComponent.RSTextFieldTwo();
        _searchEmpleado_ = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        panel_Consulta = new javax.swing.JPanel();
        Panel_resumen1 = new javax.swing.JPanel();
        rSButtonMaterialRippleIcon3 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        jPanel3 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        rSLabelImage4 = new rojeru_san.rslabel.RSLabelImage();
        jLabel48 = new javax.swing.JLabel();
        _CodEmpleasod_ = new javax.swing.JLabel();
        _NroDocumento_ = new javax.swing.JLabel();
        _Apellidos_ = new javax.swing.JLabel();
        _Nombres_ = new javax.swing.JLabel();
        _email_ = new javax.swing.JLabel();
        _Domicilio_ = new javax.swing.JLabel();
        _TipoDocu_ = new javax.swing.JLabel();
        _FechaNacimientto_ = new javax.swing.JLabel();
        _Celular_ = new javax.swing.JLabel();
        _Sexo_ = new javax.swing.JLabel();
        _EstadoCivil_ = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        _Ocupacion_ = new javax.swing.JLabel();
        _Cargo_ = new javax.swing.JLabel();
        _Status_ = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txt_DNI2 = new RSMaterialComponent.RSTextFieldTwo();
        txt_DNI3 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel68 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jSeparator33 = new javax.swing.JSeparator();
        jLabel62 = new javax.swing.JLabel();
        jSeparator34 = new javax.swing.JSeparator();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        Codigo_Interno1 = new javax.swing.JRadioButton();
        Nro_Documento1 = new javax.swing.JRadioButton();
        dato_Busqueda1 = new RSMaterialComponent.RSTextFieldTwo();
        _searchEmpleado_1 = new RSMaterialComponent.RSButtonMaterialRippleIcon();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText(" Empleados ");

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        rSLabelFecha1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSLabelHora1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSLabelFecha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                        .addComponent(rSLabelHora1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel17.setBackground(new java.awt.Color(51, 51, 51));
        jLabel17.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Total de Empleados");

        Total_Empleados.setBackground(new java.awt.Color(51, 51, 51));
        Total_Empleados.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        Total_Empleados.setForeground(new java.awt.Color(0, 114, 198));
        Total_Empleados.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Total_Empleados.setText("0");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Total_Empleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Total_Empleados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(24, 24, 24))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel18.setBackground(new java.awt.Color(51, 51, 51));
        jLabel18.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Masculino");

        Mas.setBackground(new java.awt.Color(51, 51, 51));
        Mas.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        Mas.setForeground(new java.awt.Color(0, 114, 198));
        Mas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mas.setText("0");

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/masc.png"))); // NOI18N

        _Masculino_.setBackground(new java.awt.Color(255, 255, 255));
        _Masculino_.setForeground(new java.awt.Color(47, 90, 115));
        _Masculino_.setValue(80);
        _Masculino_.setBorderPainted(false);
        _Masculino_.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(Mas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_Masculino_, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(_Masculino_, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(rSLabelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(Mas)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel18))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jLabel20.setBackground(new java.awt.Color(51, 51, 51));
        jLabel20.setFont(new java.awt.Font("Century Schoolbook", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Femenino");

        Fem.setBackground(new java.awt.Color(51, 51, 51));
        Fem.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        Fem.setForeground(new java.awt.Color(0, 114, 198));
        Fem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Fem.setText("0");

        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fem.png"))); // NOI18N

        _Femenino_.setBackground(new java.awt.Color(255, 255, 255));
        _Femenino_.setForeground(new java.awt.Color(223, 43, 106));
        _Femenino_.setValue(80);
        _Femenino_.setBorderPainted(false);
        _Femenino_.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(rSLabelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fem, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_Femenino_, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_Femenino_, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(Fem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20))
                    .addComponent(rSLabelImage3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btn_Register.setText("Nuevo ");
        btn_Register.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PERSON_ADD);
        btn_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterActionPerformed(evt);
            }
        });

        btn_Status.setText("Status");
        btn_Status.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btn_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StatusActionPerformed(evt);
            }
        });

        btn_update.setText("Actualizar");
        btn_update.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.UPDATE);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_consultar.setText("Consultar");
        btn_consultar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btn_consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultarActionPerformed(evt);
            }
        });

        btn_Listado.setText("Registro ");
        btn_Listado.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PERSON);
        btn_Listado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ListadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Listado, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Listado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel_home.setBackground(new java.awt.Color(255, 255, 255));

        rSLabelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Empleados.png"))); // NOI18N

        javax.swing.GroupLayout panel_homeLayout = new javax.swing.GroupLayout(panel_home);
        panel_home.setLayout(panel_homeLayout);
        panel_homeLayout.setHorizontalGroup(
            panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_homeLayout.createSequentialGroup()
                .addContainerGap(259, Short.MAX_VALUE)
                .addComponent(rSLabelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
        );
        panel_homeLayout.setVerticalGroup(
            panel_homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_homeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelImage2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(panel_home, "card2");

        panel_registroEmpleado.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Datos Personales");

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tipo de Documento:");

        tipo_documento.setForeground(new java.awt.Color(0, 0, 0));
        tipo_documento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "D.N.I", "RUC", "C.E" }));
        tipo_documento.setColorFondo(new java.awt.Color(255, 255, 255));
        tipo_documento.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Numero de documento:");

        txt_DNI.setForeground(new java.awt.Color(0, 0, 0));
        txt_DNI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_DNI.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_DNI.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_DNI.setPhColor(new java.awt.Color(51, 51, 51));
        txt_DNI.setPlaceholder("ejem: 99999999");

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nombres:");

        name.setForeground(new java.awt.Color(0, 0, 0));
        name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        name.setBorderColor(new java.awt.Color(51, 51, 51));
        name.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        name.setPhColor(new java.awt.Color(51, 51, 51));
        name.setPlaceholder("Ingrese Nombres Completos");

        jLabel6.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Apellidos:");

        lastname.setForeground(new java.awt.Color(0, 0, 0));
        lastname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lastname.setBorderColor(new java.awt.Color(51, 51, 51));
        lastname.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lastname.setPhColor(new java.awt.Color(51, 51, 51));
        lastname.setPlaceholder("Ingrese Nombres Completos");

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setForeground(new java.awt.Color(0, 0, 0));
        jDateChooser1.setDateFormatString("dd-MM-yyyy");

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Fecha de Nacimiento:");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("por ejem: 00-00-0000");

        jLabel9.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Sexo:");

        sexo.setForeground(new java.awt.Color(0, 0, 0));
        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Masculino", "Femenino" }));
        sexo.setColorFondo(new java.awt.Color(255, 255, 255));
        sexo.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Estado Civil: ");

        EstadoCivil.setForeground(new java.awt.Color(0, 0, 0));
        EstadoCivil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Soltero", "Casado", "Viudo", "Divorciado" }));
        EstadoCivil.setColorFondo(new java.awt.Color(255, 255, 255));
        EstadoCivil.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Domicilio:");

        domicilio.setForeground(new java.awt.Color(0, 0, 0));
        domicilio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        domicilio.setBorderColor(new java.awt.Color(51, 51, 51));
        domicilio.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        domicilio.setPhColor(new java.awt.Color(51, 51, 51));
        domicilio.setPlaceholder("Calle nro residencia , Departamento, Provincia");

        jLabel12.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Celular:");

        phone.setForeground(new java.awt.Color(0, 0, 0));
        phone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phone.setBorderColor(new java.awt.Color(51, 51, 51));
        phone.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        phone.setPhColor(new java.awt.Color(51, 51, 51));
        phone.setPlaceholder("999999999");

        jLabel13.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Operador: ");

        Operador_Movil.setForeground(new java.awt.Color(0, 0, 0));
        Operador_Movil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Movistar", "Claro", "Entel", "Bitel" }));
        Operador_Movil.setColorFondo(new java.awt.Color(255, 255, 255));
        Operador_Movil.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Correo Electronico: ");

        correo.setForeground(new java.awt.Color(0, 0, 0));
        correo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        correo.setBorderColor(new java.awt.Color(51, 51, 51));
        correo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        correo.setPhColor(new java.awt.Color(51, 51, 51));
        correo.setPlaceholder("micorreo@example.com");

        jLabel15.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Datos Laborales");

        jLabel16.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Ocupación");

        ocupacion.setForeground(new java.awt.Color(0, 0, 0));
        ocupacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Estudiante", "Profesional", "Tecnico", "No especifica" }));
        ocupacion.setColorFondo(new java.awt.Color(255, 255, 255));
        ocupacion.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Cargo que desempeñara:");

        cargo.setForeground(new java.awt.Color(0, 0, 0));
        cargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Cajero", "Vendedor", "Atencion al Cliente" }));
        cargo.setColorFondo(new java.awt.Color(255, 255, 255));
        cargo.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Acceso al sistema");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Para que este nuevo empleado tenga acceso al sistema, debe solicitarselo al administrador. ");

        rSButtonMaterialRippleIcon1.setBackground(new java.awt.Color(0, 114, 198));
        rSButtonMaterialRippleIcon1.setText("Registrar empleado");
        rSButtonMaterialRippleIcon1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        rSButtonMaterialRippleIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon1ActionPerformed(evt);
            }
        });

        rSButtonMaterialRippleIcon6.setBackground(new java.awt.Color(0, 114, 198));
        rSButtonMaterialRippleIcon6.setText("Solicitar acceso");
        rSButtonMaterialRippleIcon6.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.RECEIPT);
        rSButtonMaterialRippleIcon6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_registroEmpleadoLayout = new javax.swing.GroupLayout(panel_registroEmpleado);
        panel_registroEmpleado.setLayout(panel_registroEmpleadoLayout);
        panel_registroEmpleadoLayout.setHorizontalGroup(
            panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator14)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator12)
                    .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(correo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator16)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator15)
                    .addComponent(jSeparator17)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator18)
                    .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMaterialRippleIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipo_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DNI, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rSButtonMaterialRippleIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                        .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(EstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(domicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Operador_Movil, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ocupacion, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        panel_registroEmpleadoLayout.setVerticalGroup(
            panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(tipo_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txt_DNI, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(EstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(domicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(Operador_Movil, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(correo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(ocupacion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                        .addGroup(panel_registroEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rSButtonMaterialRippleIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(23, 23, 23))
                    .addGroup(panel_registroEmpleadoLayout.createSequentialGroup()
                        .addComponent(rSButtonMaterialRippleIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        rSPanelsSlider1.add(panel_registroEmpleado, "card3");

        panel_ListadoRegistros.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBackground(new java.awt.Color(0, 114, 198));

        Tabla_Empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Interno", "Documento", "Nro. Documento", "Nombres", "Apellidos", "Sexo", "Celular", "Email", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Empleados.setColorBorderRows(new java.awt.Color(153, 153, 153));
        Tabla_Empleados.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla_Empleados.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla_Empleados.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla_Empleados.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        Tabla_Empleados.setFontHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 11)); // NOI18N
        Tabla_Empleados.setWidthBorderHead(0);
        Tabla_Empleados.setWidthBorderRows(0);
        jScrollPane3.setViewportView(Tabla_Empleados);

        javax.swing.GroupLayout panel_ListadoRegistrosLayout = new javax.swing.GroupLayout(panel_ListadoRegistros);
        panel_ListadoRegistros.setLayout(panel_ListadoRegistrosLayout);
        panel_ListadoRegistrosLayout.setHorizontalGroup(
            panel_ListadoRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ListadoRegistrosLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        panel_ListadoRegistrosLayout.setVerticalGroup(
            panel_ListadoRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ListadoRegistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSPanelsSlider1.add(panel_ListadoRegistros, "card3");

        panel_EditarCliente.setBackground(new java.awt.Color(255, 255, 255));

        Panel_resumen.setBackground(new java.awt.Color(255, 255, 255));

        correo1.setForeground(new java.awt.Color(0, 0, 0));
        correo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        correo1.setBorderColor(new java.awt.Color(51, 51, 51));
        correo1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        correo1.setPhColor(new java.awt.Color(51, 51, 51));
        correo1.setPlaceholder("micorreo@example.com");

        jLabel35.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Correo Electronico: ");

        jLabel23.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Datos Personales");

        jLabel34.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Operador: ");

        phone1.setForeground(new java.awt.Color(0, 0, 0));
        phone1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phone1.setBorderColor(new java.awt.Color(51, 51, 51));
        phone1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        phone1.setPhColor(new java.awt.Color(51, 51, 51));
        phone1.setPlaceholder("999999999");

        jLabel33.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Celular:");

        domicilio1.setForeground(new java.awt.Color(0, 0, 0));
        domicilio1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        domicilio1.setBorderColor(new java.awt.Color(51, 51, 51));
        domicilio1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        domicilio1.setPhColor(new java.awt.Color(51, 51, 51));
        domicilio1.setPlaceholder("Calle nro residencia , Departamento, Provincia");

        jLabel32.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Domicilio:");

        EstadoCivil1.setForeground(new java.awt.Color(0, 0, 0));
        EstadoCivil1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Soltero", "Casado", "Viudo", "Divorciado" }));
        EstadoCivil1.setColorFondo(new java.awt.Color(255, 255, 255));
        EstadoCivil1.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Estado Civil: ");

        sexo1.setForeground(new java.awt.Color(0, 0, 0));
        sexo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Masculino", "Femenino" }));
        sexo1.setColorFondo(new java.awt.Color(255, 255, 255));
        sexo1.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        tipo_documento1.setForeground(new java.awt.Color(0, 0, 0));
        tipo_documento1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "D.N.I", "RUC", "C.E" }));
        tipo_documento1.setColorFondo(new java.awt.Color(255, 255, 255));
        tipo_documento1.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tipo de Documento:");

        Operador_Movil1.setForeground(new java.awt.Color(0, 0, 0));
        Operador_Movil1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Movistar", "Claro", "Entel", "Bitel" }));
        Operador_Movil1.setColorFondo(new java.awt.Color(255, 255, 255));
        Operador_Movil1.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Sexo:");

        jLabel40.setBackground(new java.awt.Color(255, 255, 255));
        jLabel40.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(102, 102, 102));
        jLabel40.setText("El usuario no posee acceso al sistema");

        jLabel39.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("Acceso al sistema");

        jLabel28.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Fecha de Nacimiento:");

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setForeground(new java.awt.Color(0, 0, 0));
        jDateChooser2.setDateFormatString("dd-MM-yyyy");

        cargo1.setForeground(new java.awt.Color(0, 0, 0));
        cargo1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Cajero", "Vendedor", "Atencion al Cliente" }));
        cargo1.setColorFondo(new java.awt.Color(255, 255, 255));
        cargo1.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        lastname1.setForeground(new java.awt.Color(0, 0, 0));
        lastname1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lastname1.setBorderColor(new java.awt.Color(51, 51, 51));
        lastname1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        lastname1.setPhColor(new java.awt.Color(51, 51, 51));
        lastname1.setPlaceholder("Ingrese Nombres Completos");

        jLabel38.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Cargo que desempeñara:");

        jLabel27.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Apellidos:");

        ocupacion1.setForeground(new java.awt.Color(0, 0, 0));
        ocupacion1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccione --", "Estudiante", "Profesional", "Tecnico", "No especifica" }));
        ocupacion1.setColorFondo(new java.awt.Color(255, 255, 255));
        ocupacion1.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N

        name1.setForeground(new java.awt.Color(0, 0, 0));
        name1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        name1.setBorderColor(new java.awt.Color(51, 51, 51));
        name1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        name1.setPhColor(new java.awt.Color(51, 51, 51));
        name1.setPlaceholder("Ingrese Nombres Completos");

        jLabel37.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Ocupación");

        jLabel26.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Nombres:");

        txt_DNI1.setForeground(new java.awt.Color(0, 0, 0));
        txt_DNI1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_DNI1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_DNI1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_DNI1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_DNI1.setPlaceholder("ejem: 99999999");

        jLabel36.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Datos Laborales");

        jLabel25.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Numero de documento:");

        jLabel29.setFont(new java.awt.Font("Yu Gothic", 1, 10)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 153, 153));
        jLabel29.setText("por ejem: 00-00-0000");

        rSButtonMaterialRippleIcon2.setBackground(new java.awt.Color(255, 51, 51));
        rSButtonMaterialRippleIcon2.setText("Cancelar");
        rSButtonMaterialRippleIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLOSE);
        rSButtonMaterialRippleIcon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon2ActionPerformed(evt);
            }
        });

        rSButtonMaterialRippleIcon4.setBackground(new java.awt.Color(0, 114, 198));
        rSButtonMaterialRippleIcon4.setText("Guardar cambios");
        rSButtonMaterialRippleIcon4.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        rSButtonMaterialRippleIcon4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_resumenLayout = new javax.swing.GroupLayout(Panel_resumen);
        Panel_resumen.setLayout(Panel_resumenLayout);
        Panel_resumenLayout.setHorizontalGroup(
            Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_resumenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_resumenLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rSButtonMaterialRippleIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMaterialRippleIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(Panel_resumenLayout.createSequentialGroup()
                        .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator23)
                            .addComponent(jSeparator24)
                            .addComponent(jSeparator22)
                            .addComponent(jSeparator21)
                            .addComponent(jSeparator19)
                            .addComponent(jSeparator20)
                            .addGroup(Panel_resumenLayout.createSequentialGroup()
                                .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel_resumenLayout.createSequentialGroup()
                                            .addComponent(jLabel35)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(correo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Panel_resumenLayout.createSequentialGroup()
                                                .addComponent(jLabel24)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tipo_documento1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel25)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txt_DNI1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel28)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Panel_resumenLayout.createSequentialGroup()
                                                .addComponent(jLabel37)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ocupacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel38)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cargo1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Panel_resumenLayout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel27)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lastname1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel30)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel31)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(EstadoCivil1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Panel_resumenLayout.createSequentialGroup()
                                                .addComponent(jLabel32)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(domicilio1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel33)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(phone1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel34)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Operador_Movil1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(5, 5, 5))))
        );
        Panel_resumenLayout.setVerticalGroup(
            Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_resumenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(tipo_documento1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(txt_DNI1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel28)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(lastname1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(sexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(EstadoCivil1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(domicilio1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(phone1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(Operador_Movil1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(correo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(ocupacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(cargo1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator24, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(Panel_resumenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSButtonMaterialRippleIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButtonMaterialRippleIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel41.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Busqueda de empleado ");

        jLabel42.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Tipo de Busqueda: ");

        jLabel43.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Buscar:");

        Codigo_Interno.setBackground(new java.awt.Color(255, 255, 255));
        Tipo_Busqueda.add(Codigo_Interno);
        Codigo_Interno.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        Codigo_Interno.setForeground(new java.awt.Color(0, 0, 0));
        Codigo_Interno.setText(" Codigo Interno");

        Nro_Documento.setBackground(new java.awt.Color(255, 255, 255));
        Tipo_Busqueda.add(Nro_Documento);
        Nro_Documento.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        Nro_Documento.setForeground(new java.awt.Color(0, 0, 0));
        Nro_Documento.setText("Nro de documento de Indentidad");

        dato_Busqueda.setForeground(new java.awt.Color(0, 0, 0));
        dato_Busqueda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dato_Busqueda.setBorderColor(new java.awt.Color(51, 51, 51));
        dato_Busqueda.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        dato_Busqueda.setPhColor(new java.awt.Color(51, 51, 51));
        dato_Busqueda.setPlaceholder("ejem: 99999999");

        _searchEmpleado_.setBackground(new java.awt.Color(0, 114, 198));
        _searchEmpleado_.setText("Realizar Busqueda");
        _searchEmpleado_.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        _searchEmpleado_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _searchEmpleado_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator26)
                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator25)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Codigo_Interno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Nro_Documento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dato_Busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(_searchEmpleado_, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator25, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator26, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43)
                        .addComponent(dato_Busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(_searchEmpleado_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(Codigo_Interno)
                        .addComponent(Nro_Documento)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_EditarClienteLayout = new javax.swing.GroupLayout(panel_EditarCliente);
        panel_EditarCliente.setLayout(panel_EditarClienteLayout);
        panel_EditarClienteLayout.setHorizontalGroup(
            panel_EditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_EditarClienteLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panel_EditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Panel_resumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        panel_EditarClienteLayout.setVerticalGroup(
            panel_EditarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_EditarClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_resumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSPanelsSlider1.add(panel_EditarCliente, "card3");

        panel_Consulta.setBackground(new java.awt.Color(255, 255, 255));

        Panel_resumen1.setBackground(new java.awt.Color(255, 255, 255));

        rSButtonMaterialRippleIcon3.setBackground(new java.awt.Color(255, 51, 51));
        rSButtonMaterialRippleIcon3.setText("cerrar");
        rSButtonMaterialRippleIcon3.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EXIT_TO_APP);
        rSButtonMaterialRippleIcon3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon3ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Datos Personales ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("DejaVu Serif", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel56.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Apellidos:");

        jLabel44.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Correo Electronico: ");

        jLabel54.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Fecha de Nacimiento:");

        jLabel51.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Sexo:");

        jLabel60.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Numero de documento:");

        jLabel49.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Estado Civil: ");

        jLabel58.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Nombres:");

        jLabel47.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Celular:");

        jLabel61.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Codigo de Empleado :");

        jLabel50.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Tipo de Documento:");

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/avatar-372-456324.png"))); // NOI18N

        jLabel48.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Domicilio:");

        _CodEmpleasod_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _CodEmpleasod_.setForeground(new java.awt.Color(0, 0, 0));
        _CodEmpleasod_.setText("0000000000");

        _NroDocumento_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _NroDocumento_.setForeground(new java.awt.Color(0, 0, 0));
        _NroDocumento_.setText("0000000000");

        _Apellidos_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _Apellidos_.setForeground(new java.awt.Color(0, 0, 0));
        _Apellidos_.setText("en espera...");

        _Nombres_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _Nombres_.setForeground(new java.awt.Color(0, 0, 0));
        _Nombres_.setText("en espera...");

        _email_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _email_.setForeground(new java.awt.Color(0, 0, 0));
        _email_.setText("en espera@dominio.es");

        _Domicilio_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _Domicilio_.setForeground(new java.awt.Color(0, 0, 0));
        _Domicilio_.setText("domicilio");

        _TipoDocu_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _TipoDocu_.setForeground(new java.awt.Color(0, 0, 0));
        _TipoDocu_.setText("En espera...");

        _FechaNacimientto_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _FechaNacimientto_.setForeground(new java.awt.Color(0, 0, 0));
        _FechaNacimientto_.setText("En espera...");

        _Celular_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _Celular_.setForeground(new java.awt.Color(0, 0, 0));
        _Celular_.setText("En espera...");

        _Sexo_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _Sexo_.setForeground(new java.awt.Color(0, 0, 0));
        _Sexo_.setText("En espera...");

        _EstadoCivil_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _EstadoCivil_.setForeground(new java.awt.Color(0, 0, 0));
        _EstadoCivil_.setText("En espera...");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_email_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_Nombres_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_Apellidos_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_NroDocumento_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_CodEmpleasod_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_Domicilio_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(_Sexo_, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(_Celular_, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_FechaNacimientto_, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_TipoDocu_, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_EstadoCivil_, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rSLabelImage4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61)
                                    .addComponent(_CodEmpleasod_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60)
                                    .addComponent(_NroDocumento_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel56)
                                    .addComponent(_Apellidos_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel58)
                                    .addComponent(_Nombres_))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel44)
                                    .addComponent(_email_)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel50)
                                    .addComponent(_TipoDocu_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel54)
                                    .addComponent(_FechaNacimientto_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel47)
                                    .addComponent(_Celular_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel51)
                                    .addComponent(_Sexo_))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel49)
                                    .addComponent(_EstadoCivil_))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(_Domicilio_))))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Datos Laborales ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("DejaVu Serif", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel57.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Ocupación: ");

        jLabel55.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Cargo que desempeñara:");

        jLabel66.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Status:");

        _Ocupacion_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _Ocupacion_.setForeground(new java.awt.Color(0, 0, 0));
        _Ocupacion_.setText("domicilio");

        _Cargo_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _Cargo_.setForeground(new java.awt.Color(0, 0, 0));
        _Cargo_.setText("domicilio");

        _Status_.setFont(new java.awt.Font("Yu Gothic", 0, 12)); // NOI18N
        _Status_.setForeground(new java.awt.Color(0, 0, 0));
        _Status_.setText("domicilio");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_Ocupacion_, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_Cargo_, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_Status_, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jLabel55)
                    .addComponent(jLabel66)
                    .addComponent(_Ocupacion_)
                    .addComponent(_Cargo_)
                    .addComponent(_Status_))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acceso al Sistema ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("DejaVu Serif", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel67.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(153, 0, 0));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Este empleado no cuenta con acceso al sistema");

        jLabel59.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Usuario:");

        jLabel65.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Contraseña: ");

        txt_DNI2.setEditable(false);
        txt_DNI2.setForeground(new java.awt.Color(0, 0, 0));
        txt_DNI2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_DNI2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_DNI2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_DNI2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_DNI2.setPlaceholder("UserSistema");

        txt_DNI3.setEditable(false);
        txt_DNI3.setForeground(new java.awt.Color(0, 0, 0));
        txt_DNI3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_DNI3.setText("**********");
        txt_DNI3.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_DNI3.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_DNI3.setPhColor(new java.awt.Color(51, 51, 51));
        txt_DNI3.setPlaceholder("*********");

        jLabel68.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 0, 102));
        jLabel68.setText("Restablecer ");
        jLabel68.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DNI2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DNI3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel67)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel65)
                        .addComponent(txt_DNI3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel68))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel59)
                        .addComponent(txt_DNI2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout Panel_resumen1Layout = new javax.swing.GroupLayout(Panel_resumen1);
        Panel_resumen1.setLayout(Panel_resumen1Layout);
        Panel_resumen1Layout.setHorizontalGroup(
            Panel_resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_resumen1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_resumen1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(Panel_resumen1Layout.createSequentialGroup()
                        .addGroup(Panel_resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_resumen1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(rSButtonMaterialRippleIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5))))
        );
        Panel_resumen1Layout.setVerticalGroup(
            Panel_resumen1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_resumen1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonMaterialRippleIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel62.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(51, 51, 51));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("Busqueda de empleado ");

        jLabel63.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Tipo de Busqueda: ");

        jLabel64.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Buscar:");

        Codigo_Interno1.setBackground(new java.awt.Color(255, 255, 255));
        Tipo_Busqueda.add(Codigo_Interno1);
        Codigo_Interno1.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        Codigo_Interno1.setForeground(new java.awt.Color(0, 0, 0));
        Codigo_Interno1.setSelected(true);
        Codigo_Interno1.setText(" Codigo Interno");

        Nro_Documento1.setBackground(new java.awt.Color(255, 255, 255));
        Tipo_Busqueda.add(Nro_Documento1);
        Nro_Documento1.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        Nro_Documento1.setForeground(new java.awt.Color(0, 0, 0));
        Nro_Documento1.setText("Nro de documento de Indentidad");

        dato_Busqueda1.setForeground(new java.awt.Color(0, 0, 0));
        dato_Busqueda1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dato_Busqueda1.setBorderColor(new java.awt.Color(51, 51, 51));
        dato_Busqueda1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        dato_Busqueda1.setPhColor(new java.awt.Color(51, 51, 51));
        dato_Busqueda1.setPlaceholder("ejem: 99999999");

        _searchEmpleado_1.setBackground(new java.awt.Color(0, 114, 198));
        _searchEmpleado_1.setText("Realizar Busqueda");
        _searchEmpleado_1.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        _searchEmpleado_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _searchEmpleado_1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator34)
                    .addComponent(jLabel62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator33)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Codigo_Interno1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Nro_Documento1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dato_Busqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(_searchEmpleado_1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator33, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator34, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel64)
                        .addComponent(dato_Busqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(_searchEmpleado_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel63)
                        .addComponent(Codigo_Interno1)
                        .addComponent(Nro_Documento1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_ConsultaLayout = new javax.swing.GroupLayout(panel_Consulta);
        panel_Consulta.setLayout(panel_ConsultaLayout);
        panel_ConsultaLayout.setHorizontalGroup(
            panel_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ConsultaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panel_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Panel_resumen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panel_ConsultaLayout.setVerticalGroup(
            panel_ConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel_resumen1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        rSPanelsSlider1.add(panel_Consulta, "card3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator13)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btn_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterActionPerformed
        panel_home.setVisible(false);
        panel_registroEmpleado.setVisible(true);
        panel_ListadoRegistros.setVisible(false);
        panel_EditarCliente.setVisible(false);
        panel_Consulta.setVisible(false);
    }//GEN-LAST:event_btn_RegisterActionPerformed

    private void btn_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_StatusActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        panel_home.setVisible(false);
        panel_registroEmpleado.setVisible(false);
        panel_ListadoRegistros.setVisible(false);
        panel_EditarCliente.setVisible(true);
        panel_Consulta.setVisible(false);
        DatosEditar();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultarActionPerformed
        panel_home.setVisible(false);
        panel_registroEmpleado.setVisible(false);
        panel_ListadoRegistros.setVisible(false);
        panel_EditarCliente.setVisible(false);
        panel_Consulta.setVisible(true);
    }//GEN-LAST:event_btn_consultarActionPerformed

    private void btn_ListadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ListadoActionPerformed
        if (Tabla_Empleados.getRowCount() == 0) {
            Message_confirmacion.Message = "<html>No existen datos registrados a mostrar</html>";
            Message_confirmacion.tipo_message = "advertencia";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        } else {
            panel_home.setVisible(false);
            panel_registroEmpleado.setVisible(false);
            panel_ListadoRegistros.setVisible(true);
            panel_EditarCliente.setVisible(false);
            panel_Consulta.setVisible(false);
        }

    }//GEN-LAST:event_btn_ListadoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaComoCadena = sdf.format(jDateChooser1.getDate());
        String fechaActual = sdf.format(new Date());
        System.out.println(fechaComoCadena);
        System.out.println(fechaActual);

        if (Integer.parseInt(fechaComoCadena.substring(5, 7)) <= Integer.parseInt(fechaActual.substring(5, 7))
                && Integer.parseInt(fechaComoCadena.substring((fechaComoCadena.length() - 2), fechaComoCadena.length())) <= Integer.parseInt(fechaActual.substring((fechaActual.length() - 2), fechaActual.length()))) {
            System.out.println("::Calculando años: " + (Integer.parseInt(String.valueOf(FechaActual).substring(0, 4)) - Integer.parseInt(fechaComoCadena.substring(0, 4))));
        } else {
            System.out.println("::Calculando años: " + (Integer.parseInt(String.valueOf(FechaActual).substring(0, 4)) - ((Integer.parseInt(fechaComoCadena.substring(0, 4))) + 1)));
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void rSButtonMaterialRippleIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon1ActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String FechaNacimiento = sdf.format(jDateChooser1.getDate());

        //tipo Documento, nro documento, fecha de nacimiento, nombres, Apellidos, sexo, estado civil,
        //domicilio, celular, operador, email, ocupacion,cargo
        Object[] Registro = {Cod_Empleado(), tipo_documento.getSelectedItem().toString(), txt_DNI.getText(), FechaNacimiento,
            name.getText().toUpperCase(), lastname.getText().toUpperCase(), sexo.getSelectedItem().toString(),
            EstadoCivil.getSelectedItem().toString(), domicilio.getText().toUpperCase(), Integer.parseInt(phone.getText()),
            Operador_Movil.getSelectedItem().toString(), correo.getText().toLowerCase(),
            ocupacion.getSelectedItem().toString(), cargo.getSelectedItem().toString(), "Activo",
            Ventana_Administrador.user, FechaActual, String.valueOf(horaActual).substring(0, 8)};
        Empleado = new Empleados(Registro);
        LCE.insertar(Empleado);
        try {
            Message_confirmacion.tipo_message = "confirmacion";
            Message_confirmacion.Message = "<html>Los datos han sido registrados con exito. Cod. Interno del empleado: " + Cod_Empleado() + "</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
            pro.adicionar(Empleado);
            listar();
            LimpiezaCampos();

        } catch (Exception e) {
            Message_confirmacion.tipo_message = "error";
            Message_confirmacion.Message = "<html>Ha ocurrido un error al registrar el Empleado, contactese con el soporte</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        }

        Reporte();
        ConteoMasculino = 0;
        ConteoFemenino = 0;
    }//GEN-LAST:event_rSButtonMaterialRippleIcon1ActionPerformed

    private void rSButtonMaterialRippleIcon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon2ActionPerformed
        DatosEditar();
    }//GEN-LAST:event_rSButtonMaterialRippleIcon2ActionPerformed

    private void rSButtonMaterialRippleIcon4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon4ActionPerformed
        Update();
        DatosEditar();
    }//GEN-LAST:event_rSButtonMaterialRippleIcon4ActionPerformed

    private void _searchEmpleado_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__searchEmpleado_ActionPerformed

        if (Codigo_Interno.isSelected()) {
            Empleado = LCE.buscarCodInterno(dato_Busqueda.getText());
            if (Empleado != null) {
                try {
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(Empleado.getFecha_Nacimiento());

                    Cod_Interno = Empleado.getCod_Interno();
                    tipo_documento1.setSelectedItem(Empleado.getTipo_Documento());
                    txt_DNI1.setText(Empleado.getNro_Documento());
                    jDateChooser2.setDate(date1);
                    name1.setText(Empleado.getNombres());
                    lastname1.setText(Empleado.getApellidos());
                    sexo1.setSelectedItem(Empleado.getSexo());
                    EstadoCivil1.setSelectedItem(Empleado.getEstado_Civil());
                    domicilio1.setText(Empleado.getDomicilio());
                    phone1.setText(String.valueOf(Empleado.getCelular()));
                    Operador_Movil1.setSelectedItem(Empleado.getOperador_Movil());
                    correo1.setText(Empleado.getEmail());
                    ocupacion1.setSelectedItem(Empleado.getOcupacion());
                    cargo1.setSelectedItem(Empleado.getCargo());
                    Panel_resumen.setVisible(true);
                    txt_DNI1.setEditable(false);
                } catch (Exception e) {
                }
            } else {
                Message_confirmacion.tipo_message = "advertencia";
                Message_confirmacion.Message = "<html>El código interno en busqueda no se encuentra registrado</html>";
                new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
            }
        }

        if (Nro_Documento.isSelected()) {
            Empleado = LCE.buscarDocumento(dato_Busqueda.getText());
            if (Empleado != null) {
                try {
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(Empleado.getFecha_Nacimiento());

                    Cod_Interno = Empleado.getCod_Interno();
                    tipo_documento1.setSelectedItem(Empleado.getTipo_Documento());
                    txt_DNI1.setText(Empleado.getNro_Documento());
                    jDateChooser2.setDate(date1);
                    name1.setText(Empleado.getNombres());
                    lastname1.setText(Empleado.getApellidos());
                    sexo1.setSelectedItem(Empleado.getSexo());
                    EstadoCivil1.setSelectedItem(Empleado.getEstado_Civil());
                    domicilio1.setText(Empleado.getDomicilio());
                    phone1.setText(String.valueOf(Empleado.getCelular()));
                    Operador_Movil1.setSelectedItem(Empleado.getOperador_Movil());
                    correo1.setText(Empleado.getEmail());
                    ocupacion1.setSelectedItem(Empleado.getOcupacion());
                    cargo1.setSelectedItem(Empleado.getCargo());
                    Panel_resumen.setVisible(true);
                    txt_DNI1.setEditable(false);
                } catch (Exception e) {
                }
            } else {
                Message_confirmacion.tipo_message = "advertencia";
                Message_confirmacion.Message = "<html>El número del documento ingresado no se encuentra registrado</html>";
                new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
            }
        }

    }//GEN-LAST:event__searchEmpleado_ActionPerformed

    private void rSButtonMaterialRippleIcon6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonMaterialRippleIcon6ActionPerformed

    private void rSButtonMaterialRippleIcon3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon3ActionPerformed
        Panel_resumen1.setVisible(false);
    }//GEN-LAST:event_rSButtonMaterialRippleIcon3ActionPerformed

    private void _searchEmpleado_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__searchEmpleado_1ActionPerformed
        if (Codigo_Interno1.isSelected()) {
            Empleado = LCE.buscarCodInterno(dato_Busqueda1.getText());
            if (Empleado != null) {
                try {
                    _FechaNacimientto_.setText(Empleado.getFecha_Nacimiento());
                    _CodEmpleasod_.setText(Empleado.getCod_Interno());
                    _TipoDocu_.setText(Empleado.getTipo_Documento());
                    _NroDocumento_.setText(Empleado.getNro_Documento());
                    _Nombres_.setText(Empleado.getNombres());
                    _Apellidos_.setText(Empleado.getApellidos());
                    _Sexo_.setText(Empleado.getSexo());
                    _EstadoCivil_.setText(Empleado.getEstado_Civil());
                    _Domicilio_.setText(Empleado.getDomicilio());
                    _Celular_.setText(String.valueOf(Empleado.getCelular()) + "  -  " + Empleado.getOperador_Movil());
                    _email_.setText(Empleado.getEmail());
                    _Ocupacion_.setText(Empleado.getOcupacion());
                    _Cargo_.setText(Empleado.getCargo());
                    Panel_resumen1.setVisible(true);
                    dato_Busqueda1.setText(null);
                } catch (Exception e) {
                }
            } else {
                Message_confirmacion.tipo_message = "advertencia";
                Message_confirmacion.Message = "<html>El código interno en busqueda no se encuentra registrado</html>";
                new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
            }
        }

        if (Nro_Documento1.isSelected()) {
            Empleado = LCE.buscarDocumento(dato_Busqueda1.getText());
            if (Empleado != null) {
                try {
                    _FechaNacimientto_.setText(Empleado.getFecha_Nacimiento());
                    _CodEmpleasod_.setText(Empleado.getCod_Interno());
                    _TipoDocu_.setText(Empleado.getTipo_Documento());
                    _NroDocumento_.setText(Empleado.getNro_Documento());
                    _Nombres_.setText(Empleado.getNombres());
                    _Apellidos_.setText(Empleado.getApellidos());
                    _Sexo_.setText(Empleado.getSexo());
                    _EstadoCivil_.setText(Empleado.getEstado_Civil());
                    _Domicilio_.setText(Empleado.getDomicilio());
                    _Celular_.setText(String.valueOf(Empleado.getCelular()) + "  -  " + Empleado.getOperador_Movil());
                    _email_.setText(Empleado.getEmail());
                    _Ocupacion_.setText(Empleado.getOcupacion());
                    _Cargo_.setText(Empleado.getCargo());
                    Panel_resumen1.setVisible(true);
                    dato_Busqueda1.setText(null);
                } catch (Exception e) {
                }
            } else {
                Message_confirmacion.tipo_message = "advertencia";
                Message_confirmacion.Message = "<html>El número del documento ingresado no se encuentra registrado</html>";
                new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
            }
        }
    }//GEN-LAST:event__searchEmpleado_1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton Codigo_Interno;
    private javax.swing.JRadioButton Codigo_Interno1;
    private rojerusan.RSComboMetro EstadoCivil;
    private rojerusan.RSComboMetro EstadoCivil1;
    public static javax.swing.JLabel Fem;
    public static javax.swing.JLabel Mas;
    private javax.swing.JRadioButton Nro_Documento;
    private javax.swing.JRadioButton Nro_Documento1;
    private rojerusan.RSComboMetro Operador_Movil;
    private rojerusan.RSComboMetro Operador_Movil1;
    private javax.swing.JPanel Panel_resumen;
    private javax.swing.JPanel Panel_resumen1;
    public static rojerusan.RSTableMetro Tabla_Empleados;
    private javax.swing.ButtonGroup Tipo_Busqueda;
    public static javax.swing.JLabel Total_Empleados;
    private javax.swing.JLabel _Apellidos_;
    private javax.swing.JLabel _Cargo_;
    private javax.swing.JLabel _Celular_;
    private javax.swing.JLabel _CodEmpleasod_;
    private javax.swing.JLabel _Domicilio_;
    private javax.swing.JLabel _EstadoCivil_;
    private javax.swing.JLabel _FechaNacimientto_;
    public static rojerusan.componentes.RSProgressCircle _Femenino_;
    public static rojerusan.componentes.RSProgressCircle _Masculino_;
    private javax.swing.JLabel _Nombres_;
    private javax.swing.JLabel _NroDocumento_;
    private javax.swing.JLabel _Ocupacion_;
    private javax.swing.JLabel _Sexo_;
    private javax.swing.JLabel _Status_;
    private javax.swing.JLabel _TipoDocu_;
    private javax.swing.JLabel _email_;
    private RSMaterialComponent.RSButtonMaterialRippleIcon _searchEmpleado_;
    private RSMaterialComponent.RSButtonMaterialRippleIcon _searchEmpleado_1;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_Listado;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_Register;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_Status;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_consultar;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_update;
    private rojerusan.RSComboMetro cargo;
    private rojerusan.RSComboMetro cargo1;
    private RSMaterialComponent.RSTextFieldTwo correo;
    private RSMaterialComponent.RSTextFieldTwo correo1;
    private RSMaterialComponent.RSTextFieldTwo dato_Busqueda;
    private RSMaterialComponent.RSTextFieldTwo dato_Busqueda1;
    private RSMaterialComponent.RSTextFieldTwo domicilio;
    private RSMaterialComponent.RSTextFieldTwo domicilio1;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private RSMaterialComponent.RSTextFieldTwo lastname;
    private RSMaterialComponent.RSTextFieldTwo lastname1;
    private RSMaterialComponent.RSTextFieldTwo name;
    private RSMaterialComponent.RSTextFieldTwo name1;
    private rojerusan.RSComboMetro ocupacion;
    private rojerusan.RSComboMetro ocupacion1;
    private javax.swing.JPanel panel_Consulta;
    private javax.swing.JPanel panel_EditarCliente;
    private javax.swing.JPanel panel_ListadoRegistros;
    private javax.swing.JPanel panel_home;
    private javax.swing.JPanel panel_registroEmpleado;
    private RSMaterialComponent.RSTextFieldTwo phone;
    private RSMaterialComponent.RSTextFieldTwo phone1;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon1;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon2;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon3;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon4;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon6;
    private rojeru_san.rsdate.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage1;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage2;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage3;
    private rojeru_san.rslabel.RSLabelImage rSLabelImage4;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSComboMetro sexo;
    private rojerusan.RSComboMetro sexo1;
    private rojerusan.RSComboMetro tipo_documento;
    private rojerusan.RSComboMetro tipo_documento1;
    private RSMaterialComponent.RSTextFieldTwo txt_DNI;
    private RSMaterialComponent.RSTextFieldTwo txt_DNI1;
    private RSMaterialComponent.RSTextFieldTwo txt_DNI2;
    private RSMaterialComponent.RSTextFieldTwo txt_DNI3;
    // End of variables declaration//GEN-END:variables
}
