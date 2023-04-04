package panels;

import VentanasEmegentes.Register_Categoria;
import controlador.ListaCircular_Inventario;
import controlador.Process_Inventario;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import message.Message_confirmacion;
import modelo.Inventario;
import views.Ventana_Administrador;

public class Pnl_inventario extends javax.swing.JPanel {

    int generar;
    Inventario inventario;
    Process_Inventario processInve = new Process_Inventario();
    ListaCircular_Inventario listCircInv = new ListaCircular_Inventario();
    LocalDate FechaActual = LocalDate.now();
    Register_Categoria RC = new Register_Categoria(new javax.swing.JFrame(), true);
    String[] cab = {"Codigo", "Descripcion", "Categoria", "Cantidad", "Precio", "Fecha de Registro", "Registrado por"};
    DefaultTableModel modelo = new DefaultTableModel(null, cab);

    public Pnl_inventario() {
        initComponents();
        Tabla_Inventario.setModel(modelo);
        cargar();
        info();
        GenerarCod();
    }

    // Generador de codigo automatico
    private void GenerarCod() {
        if (RB_Auto.isSelected()) {

            if (listCircInv.Longitud() == 0) {
                generar = 7000000;
                txt_Cod.setText(String.valueOf(generar));
            }

            if (listCircInv.Longitud() > 0) {

                inventario = listCircInv.buscar(txt_Cod.getText());
                if (inventario != null) {
                    generar = 10000 + Integer.parseInt(listCircInv.ObteniendoCodigo());
                    txt_Cod.setText(String.valueOf(generar));
                } else {
                    generar = 7000000 + listCircInv.Longitud();
                    txt_Cod.setText(String.valueOf(generar));
                }

            }

            txt_Cod.setEditable(false);

        }

        if (RB_Manual.isSelected()) {
            txt_Cod.setText(null);
            txt_Cod.setEditable(true);
        }

    }
    // Informacion general

    private void info() {
        Pnl_home._TotalProductos_.setText(String.valueOf(listCircInv.TotalProductos()));
        Ventana_Administrador.pocentajeInventario = ((int) ((listCircInv.Longitud() * 100) / 150));
    }

    // Campos Limpios Registro
    public void CamposLimpios() {
        txt_descrip.setText(null);
        txt_cant.setText(null);
        txt_precio.setText(null);
        jcombo_cat.setSelectedIndex(0);
    }

    // Campos Limpios Registro
    public void CamposLimpiosEdit() {
        txt_Cod1.setText(null);
        txt_descrip1.setText(null);
        txt_cant1.setText(null);
        txt_precio1.setText(null);
        jcombo_cat1.setSelectedIndex(0);
    }

    // Campos Limpios Registro
    private void CamposLimpiosDelete() {
        txt_Cod2.setText(null);
        txt_descrip2.setText(null);
        txt_cant2.setText(null);
        txt_precio2.setText(null);
        txt_categoria.setText(null);
    }

    //Activar botones
    public void ActiveBotones(boolean f) {
        txt_descrip1.setEditable(f);
        txt_cant1.setEditable(f);
        txt_precio1.setEditable(f);
    }

    // Listar en Tabla
    private void listar() {
        modelo.setRowCount(0);
        for (Inventario inve : listCircInv.listado()) {
            modelo.addRow(inve.getInformacionInventario());
        }
    }

    private void Delete() {
        if (listCircInv.eliminar(txt_Cod2.getText())) {
            listar();
            info();
            Message_confirmacion.tipo_message = "confirmacion";
            Message_confirmacion.Message = "<html>" + "El codigo " + txt_Cod2.getText() + " se elimino con exito" + "</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
            try {
                processInve.actualizar(listCircInv.listado());
            } catch (Exception e) {
            }
        } else {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>El codigo ingresado no se encuentra registrado en el inventario</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        }
    }

    // Cargar Lista
    private void cargar() {
        modelo.setRowCount(0);
        try {
            processInve.leer();
            for (Inventario inve : processInve.lista) {
                modelo.addRow(inve.getInformacionInventario());
                listCircInv.insertar(inve);
            }
        } catch (Exception e) {
        }
    }

    // Registrar
    private void Registrar() {
        Object[] arreglo = {txt_Cod.getText(), txt_descrip.getText(), jcombo_cat.getSelectedItem().toString(),
            Integer.parseInt(txt_cant.getText()), Double.parseDouble(txt_precio.getText()), FechaActual, Ventana_Administrador.user};
        inventario = new Inventario(arreglo);
        listCircInv.insertar(inventario);
        try {
            processInve.insertar(inventario);
            listar();
            info();
            GenerarCod();
            CamposLimpios();
            Message_confirmacion.Message = "<html>El producto se ha registrado con exito</html>";
            Message_confirmacion.tipo_message = "confirmacion";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        } catch (Exception e) {
            System.out.println("error al registrar");
        }
    }

    // Editar
    private void Update() {
        Object[] arreglo = {txt_Cod1.getText(), txt_descrip1.getText(), jcombo_cat1.getSelectedItem().toString(),
            Integer.parseInt(txt_cant1.getText()), Double.parseDouble(txt_precio1.getText()), FechaActual, Ventana_Administrador.user};
        inventario = new Inventario(arreglo);
        listCircInv.actualizar(inventario);
        listar();
        info();
        GenerarCod();
        Message_confirmacion.tipo_message = "confirmacion";
        Message_confirmacion.Message = "<html>" + "Los datos del codigo " + txt_Cod1.getText() + " se han actualizado"
                + " con exito" + "</html>";
        new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        CamposLimpiosEdit();
        ActiveBotones(false);
        try {
            processInve.actualizar(listCircInv.listado());
        } catch (Exception e) {
        }
    }

    // Busqueda
    private void searchEditar() {
        inventario = listCircInv.buscar(txt_Cod1.getText());
        if (inventario != null) {
            txt_descrip1.setText(inventario.getDescripcion());
            txt_cant1.setText(String.valueOf(inventario.getCantidad_Stock()));
            txt_precio1.setText(String.valueOf(inventario.getPrecio()));
            label_modific.setText(inventario.getFecha_Registro());
            jcombo_cat1.setSelectedItem(inventario.getCategoria());

            ActiveBotones(true);

        } else {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>El codigo ingresado no se encuentra registrado</html>";
            new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
        }
    }

    // Busqueda
    private void searchDelete() {
        inventario = listCircInv.buscar(txt_Cod2.getText());
        if (inventario != null) {
            txt_descrip2.setText(inventario.getDescripcion());
            txt_cant2.setText(String.valueOf(inventario.getCantidad_Stock()));
            txt_precio2.setText(String.valueOf(inventario.getPrecio()));
            txt_fecha2.setText(inventario.getFecha_Registro());
            txt_categoria.setText(inventario.getCategoria());
        } else {
            Message_confirmacion.tipo_message = "advertencia";
            Message_confirmacion.Message = "<html>El codigo ingresado no se encuentra registrado</html>";
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

        Grup_IngCodigo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rSLabelFecha1 = new rojeru_san.rsdate.RSLabelFecha();
        rSLabelHora1 = new rojeru_san.rsdate.RSLabelHora();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Inventario = new rojerusan.RSTableMetro();
        jLabel9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btn_register = new rojeru_san.rsbutton.RSButtonCustom();
        btn_edit = new rojeru_san.rsbutton.RSButtonCustom();
        btn_delete = new rojeru_san.rsbutton.RSButtonCustom();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        Panel_RegisterInventario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Cod = new RSMaterialComponent.RSTextFieldTwo();
        jLabel4 = new javax.swing.JLabel();
        txt_descrip = new RSMaterialComponent.RSTextFieldTwo();
        jLabel5 = new javax.swing.JLabel();
        jcombo_cat = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel6 = new javax.swing.JLabel();
        txt_cant = new RSMaterialComponent.RSTextFieldTwo();
        jLabel7 = new javax.swing.JLabel();
        txt_precio = new RSMaterialComponent.RSTextFieldTwo();
        RB_Manual = new javax.swing.JRadioButton();
        RB_Auto = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        btn_Register = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        Panel_EditorInventario = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_Cod1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel11 = new javax.swing.JLabel();
        txt_descrip1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel12 = new javax.swing.JLabel();
        jcombo_cat1 = new RSMaterialComponent.RSComboBoxMaterial();
        jLabel13 = new javax.swing.JLabel();
        txt_cant1 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel14 = new javax.swing.JLabel();
        txt_precio1 = new RSMaterialComponent.RSTextFieldTwo();
        btn_saveEdit = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_search = new RSMaterialComponent.RSButtonIconUno();
        jLabel15 = new javax.swing.JLabel();
        label_modific = new javax.swing.JLabel();
        Panel_DeleteInventario = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txt_Cod2 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel18 = new javax.swing.JLabel();
        txt_descrip2 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_cant2 = new RSMaterialComponent.RSTextFieldTwo();
        jLabel21 = new javax.swing.JLabel();
        txt_precio2 = new RSMaterialComponent.RSTextFieldTwo();
        rSButtonMaterialRippleIcon2 = new RSMaterialComponent.RSButtonMaterialRippleIcon();
        btn_Busqueda = new RSMaterialComponent.RSButtonIconUno();
        jLabel22 = new javax.swing.JLabel();
        txt_fecha2 = new javax.swing.JLabel();
        txt_categoria = new RSMaterialComponent.RSTextFieldTwo();
        btn_SearchProducto = new RSMaterialComponent.RSButtonMaterialRippleIcon();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 114, 198));

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText(" Almacen e Inventario");

        rSLabelFecha1.setForeground(new java.awt.Color(255, 255, 255));

        rSLabelHora1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(rSLabelFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSLabelHora1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText(":: Administración del inventario :");

        Tabla_Inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripcion", "cant", "Categoria", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_Inventario.setBackgoundHover(new java.awt.Color(255, 255, 255));
        Tabla_Inventario.setColorPrimaryText(new java.awt.Color(0, 0, 0));
        Tabla_Inventario.setColorSecondary(new java.awt.Color(255, 255, 255));
        Tabla_Inventario.setColorSecundaryText(new java.awt.Color(0, 0, 0));
        Tabla_Inventario.setForegroundHover(new java.awt.Color(0, 0, 0));
        Tabla_Inventario.setGridColor(new java.awt.Color(255, 255, 255));
        Tabla_Inventario.setSelectionBackground(new java.awt.Color(204, 204, 204));
        Tabla_Inventario.setSelectionForeground(new java.awt.Color(0, 0, 0));
        Tabla_Inventario.setShowVerticalLines(false);
        Tabla_Inventario.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tabla_Inventario);
        if (Tabla_Inventario.getColumnModel().getColumnCount() > 0) {
            Tabla_Inventario.getColumnModel().getColumn(0).setResizable(false);
            Tabla_Inventario.getColumnModel().getColumn(1).setResizable(false);
            Tabla_Inventario.getColumnModel().getColumn(3).setResizable(false);
            Tabla_Inventario.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText(":: Listado de Productos :");

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

        rSPanelsSlider1.setBackground(new java.awt.Color(255, 255, 255));

        Panel_RegisterInventario.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Código del Producto: ");

        txt_Cod.setEditable(false);
        txt_Cod.setForeground(new java.awt.Color(0, 0, 0));
        txt_Cod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Cod.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_Cod.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_Cod.setPhColor(new java.awt.Color(51, 51, 51));
        txt_Cod.setPlaceholder(" Ingrese Codigo del Producto ");

        jLabel4.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Descripción : ");

        txt_descrip.setForeground(new java.awt.Color(0, 0, 0));
        txt_descrip.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_descrip.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_descrip.setPhColor(new java.awt.Color(51, 51, 51));
        txt_descrip.setPlaceholder(" Ingrese Descripcion del producto   ");

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Categoría :  ");

        jcombo_cat.setForeground(new java.awt.Color(51, 51, 51));
        jcombo_cat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccionar --" }));
        jcombo_cat.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Cantidad en Stock : ");

        txt_cant.setForeground(new java.awt.Color(0, 0, 0));
        txt_cant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cant.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_cant.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_cant.setPhColor(new java.awt.Color(51, 51, 51));
        txt_cant.setPlaceholder(" Ingrese cantidad");

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Precio Unitario : ");

        txt_precio.setForeground(new java.awt.Color(0, 0, 0));
        txt_precio.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_precio.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_precio.setPhColor(new java.awt.Color(51, 51, 51));
        txt_precio.setPlaceholder(" Ingrese Precio");

        RB_Manual.setBackground(new java.awt.Color(255, 255, 255));
        Grup_IngCodigo.add(RB_Manual);
        RB_Manual.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        RB_Manual.setForeground(new java.awt.Color(0, 0, 0));
        RB_Manual.setText(" Manual");
        RB_Manual.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                RB_ManualStateChanged(evt);
            }
        });
        RB_Manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB_ManualActionPerformed(evt);
            }
        });

        RB_Auto.setBackground(new java.awt.Color(255, 255, 255));
        Grup_IngCodigo.add(RB_Auto);
        RB_Auto.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        RB_Auto.setForeground(new java.awt.Color(0, 0, 0));
        RB_Auto.setSelected(true);
        RB_Auto.setText("Automatico");
        RB_Auto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                RB_AutoStateChanged(evt);
            }
        });
        RB_Auto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB_AutoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Ingreso del Código: ");

        btn_Register.setText("Registrar Producto");
        btn_Register.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btn_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel_RegisterInventarioLayout = new javax.swing.GroupLayout(Panel_RegisterInventario);
        Panel_RegisterInventario.setLayout(Panel_RegisterInventarioLayout);
        Panel_RegisterInventarioLayout.setHorizontalGroup(
            Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_RegisterInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jcombo_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_RegisterInventarioLayout.createSequentialGroup()
                        .addComponent(RB_Manual, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(RB_Auto))
                    .addComponent(txt_Cod, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_RegisterInventarioLayout.createSequentialGroup()
                        .addComponent(txt_descrip, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_RegisterInventarioLayout.createSequentialGroup()
                        .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                        .addComponent(btn_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Panel_RegisterInventarioLayout.setVerticalGroup(
            Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_RegisterInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RB_Manual)
                    .addComponent(RB_Auto)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_RegisterInventarioLayout.createSequentialGroup()
                        .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_Cod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_descrip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jcombo_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(Panel_RegisterInventarioLayout.createSequentialGroup()
                        .addGroup(Panel_RegisterInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cant, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );

        rSPanelsSlider1.add(Panel_RegisterInventario, "card2");

        Panel_EditorInventario.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Código del Producto: ");

        txt_Cod1.setForeground(new java.awt.Color(0, 0, 0));
        txt_Cod1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Cod1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_Cod1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_Cod1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_Cod1.setPlaceholder(" Ingrese Codigo del Producto ");

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Descripción : ");

        txt_descrip1.setEditable(false);
        txt_descrip1.setForeground(new java.awt.Color(0, 0, 0));
        txt_descrip1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_descrip1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_descrip1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_descrip1.setPlaceholder(" Ingrese Descripcion del producto   ");

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Categoría :  ");

        jcombo_cat1.setForeground(new java.awt.Color(51, 51, 51));
        jcombo_cat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Seleccionar --" }));
        jcombo_cat1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Cantidad en Stock : ");

        txt_cant1.setEditable(false);
        txt_cant1.setForeground(new java.awt.Color(0, 0, 0));
        txt_cant1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cant1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_cant1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_cant1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_cant1.setPlaceholder(" Ingrese cantidad");

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Precio Unitario : ");

        txt_precio1.setEditable(false);
        txt_precio1.setForeground(new java.awt.Color(0, 0, 0));
        txt_precio1.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_precio1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_precio1.setPhColor(new java.awt.Color(51, 51, 51));
        txt_precio1.setPlaceholder(" Ingrese Precio");

        btn_saveEdit.setText("Guardar cambios");
        btn_saveEdit.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.EDIT);
        btn_saveEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveEditActionPerformed(evt);
            }
        });

        btn_search.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Fecha de Modificación :");

        label_modific.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        label_modific.setForeground(new java.awt.Color(51, 51, 51));
        label_modific.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_modific.setText("--/--/----");

        javax.swing.GroupLayout Panel_EditorInventarioLayout = new javax.swing.GroupLayout(Panel_EditorInventario);
        Panel_EditorInventario.setLayout(Panel_EditorInventarioLayout);
        Panel_EditorInventarioLayout.setHorizontalGroup(
            Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_EditorInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_EditorInventarioLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txt_Cod1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(Panel_EditorInventarioLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcombo_cat1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(txt_cant1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_EditorInventarioLayout.createSequentialGroup()
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descrip1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_EditorInventarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_saveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_EditorInventarioLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_precio1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_modific, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Panel_EditorInventarioLayout.setVerticalGroup(
            Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_EditorInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Cod1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_descrip1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_precio1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_cant1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_modific, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_EditorInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jcombo_cat1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_saveEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(Panel_EditorInventario, "card2");

        Panel_DeleteInventario.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Código del Producto: ");

        txt_Cod2.setForeground(new java.awt.Color(0, 0, 0));
        txt_Cod2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Cod2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_Cod2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_Cod2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_Cod2.setPlaceholder(" Ingrese Codigo del Producto ");

        jLabel18.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Descripción : ");

        txt_descrip2.setEditable(false);
        txt_descrip2.setForeground(new java.awt.Color(0, 0, 0));
        txt_descrip2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_descrip2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_descrip2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_descrip2.setPlaceholder("  Descripcion del producto   ");

        jLabel19.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Categoría :  ");

        jLabel20.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Cantidad en Stock : ");

        txt_cant2.setEditable(false);
        txt_cant2.setForeground(new java.awt.Color(0, 0, 0));
        txt_cant2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cant2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_cant2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_cant2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_cant2.setPlaceholder("  cantidad");

        jLabel21.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Precio Unitario : ");

        txt_precio2.setEditable(false);
        txt_precio2.setForeground(new java.awt.Color(0, 0, 0));
        txt_precio2.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_precio2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_precio2.setPhColor(new java.awt.Color(51, 51, 51));
        txt_precio2.setPlaceholder("  Precio");

        rSButtonMaterialRippleIcon2.setText("Eliminar");
        rSButtonMaterialRippleIcon2.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DELETE);
        rSButtonMaterialRippleIcon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMaterialRippleIcon2ActionPerformed(evt);
            }
        });

        btn_Busqueda.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btn_Busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BusquedaActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Fecha de Modificación :");

        txt_fecha2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        txt_fecha2.setForeground(new java.awt.Color(51, 51, 51));
        txt_fecha2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_fecha2.setText("--/--/----");

        txt_categoria.setEditable(false);
        txt_categoria.setForeground(new java.awt.Color(0, 0, 0));
        txt_categoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_categoria.setBorderColor(new java.awt.Color(51, 51, 51));
        txt_categoria.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        txt_categoria.setPhColor(new java.awt.Color(51, 51, 51));
        txt_categoria.setPlaceholder("  categoria del producto");

        javax.swing.GroupLayout Panel_DeleteInventarioLayout = new javax.swing.GroupLayout(Panel_DeleteInventario);
        Panel_DeleteInventario.setLayout(Panel_DeleteInventarioLayout);
        Panel_DeleteInventarioLayout.setHorizontalGroup(
            Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DeleteInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DeleteInventarioLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txt_Cod2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(Panel_DeleteInventarioLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(txt_cant2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                    .addGroup(Panel_DeleteInventarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))
                .addGap(12, 12, 12)
                .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_DeleteInventarioLayout.createSequentialGroup()
                        .addComponent(btn_Busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_descrip2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_DeleteInventarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(rSButtonMaterialRippleIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel_DeleteInventarioLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_precio2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Panel_DeleteInventarioLayout.setVerticalGroup(
            Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DeleteInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Cod2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_descrip2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_Busqueda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_precio2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_cant2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DeleteInventarioLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(Panel_DeleteInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Panel_DeleteInventarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rSButtonMaterialRippleIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(Panel_DeleteInventario, "card2");

        btn_SearchProducto.setText(" Agregar Categoria");
        btn_SearchProducto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btn_SearchProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SearchProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_register, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_SearchProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_register, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SearchProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        Panel_RegisterInventario.setVisible(false);
        Panel_EditorInventario.setVisible(true);
        Panel_DeleteInventario.setVisible(false);
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registerActionPerformed
        Panel_RegisterInventario.setVisible(true);
        Panel_EditorInventario.setVisible(false);
        Panel_DeleteInventario.setVisible(false);
    }//GEN-LAST:event_btn_registerActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        Panel_RegisterInventario.setVisible(false);
        Panel_EditorInventario.setVisible(false);
        Panel_DeleteInventario.setVisible(true);
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterActionPerformed
        try {
            if (listCircInv.TotalProductos() == 0) {
                Registrar();
            } else {
                inventario = listCircInv.buscar(txt_Cod.getText());
                if (inventario != null) {
                    Message_confirmacion.tipo_message = "advertencia";
                    Message_confirmacion.Message = "<html>El codigo " + txt_Cod.getText() + " ya se encuentra registrado en el inventario</html>";
                    new Message_confirmacion(new javax.swing.JFrame(), true).setVisible(true);
                } else {
                    Registrar();
                }
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un error al registrar " + e);
        }

    }//GEN-LAST:event_btn_RegisterActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        searchEditar();
    }//GEN-LAST:event_btn_searchActionPerformed

    private void RB_AutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB_AutoActionPerformed
        RB_Manual.setSelected(false);
        GenerarCod();
    }//GEN-LAST:event_RB_AutoActionPerformed

    private void RB_ManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB_ManualActionPerformed
        RB_Auto.setSelected(false);
        GenerarCod();
    }//GEN-LAST:event_RB_ManualActionPerformed

    private void RB_AutoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_RB_AutoStateChanged
        RB_Manual.setSelected(false);
        GenerarCod();
    }//GEN-LAST:event_RB_AutoStateChanged

    private void RB_ManualStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_RB_ManualStateChanged
        RB_Auto.setSelected(false);
        GenerarCod();
    }//GEN-LAST:event_RB_ManualStateChanged

    private void btn_saveEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveEditActionPerformed
        Update();
    }//GEN-LAST:event_btn_saveEditActionPerformed

    private void btn_SearchProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchProductoActionPerformed
        new Register_Categoria(new javax.swing.JFrame(), true).setVisible(true);
    }//GEN-LAST:event_btn_SearchProductoActionPerformed

    private void btn_BusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BusquedaActionPerformed
        searchDelete();
    }//GEN-LAST:event_btn_BusquedaActionPerformed

    private void rSButtonMaterialRippleIcon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMaterialRippleIcon2ActionPerformed
        Delete();
        CamposLimpiosDelete();
    }//GEN-LAST:event_rSButtonMaterialRippleIcon2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Grup_IngCodigo;
    private javax.swing.JPanel Panel_DeleteInventario;
    private javax.swing.JPanel Panel_EditorInventario;
    private javax.swing.JPanel Panel_RegisterInventario;
    private javax.swing.JRadioButton RB_Auto;
    private javax.swing.JRadioButton RB_Manual;
    public static rojerusan.RSTableMetro Tabla_Inventario;
    private RSMaterialComponent.RSButtonIconUno btn_Busqueda;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_Register;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_SearchProducto;
    private rojeru_san.rsbutton.RSButtonCustom btn_delete;
    private rojeru_san.rsbutton.RSButtonCustom btn_edit;
    private rojeru_san.rsbutton.RSButtonCustom btn_register;
    private RSMaterialComponent.RSButtonMaterialRippleIcon btn_saveEdit;
    private RSMaterialComponent.RSButtonIconUno btn_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public static RSMaterialComponent.RSComboBoxMaterial jcombo_cat;
    public static RSMaterialComponent.RSComboBoxMaterial jcombo_cat1;
    private javax.swing.JLabel label_modific;
    private RSMaterialComponent.RSButtonMaterialRippleIcon rSButtonMaterialRippleIcon2;
    private rojeru_san.rsdate.RSLabelFecha rSLabelFecha1;
    private rojeru_san.rsdate.RSLabelHora rSLabelHora1;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private RSMaterialComponent.RSTextFieldTwo txt_Cod;
    private RSMaterialComponent.RSTextFieldTwo txt_Cod1;
    private RSMaterialComponent.RSTextFieldTwo txt_Cod2;
    private RSMaterialComponent.RSTextFieldTwo txt_cant;
    private RSMaterialComponent.RSTextFieldTwo txt_cant1;
    private RSMaterialComponent.RSTextFieldTwo txt_cant2;
    private RSMaterialComponent.RSTextFieldTwo txt_categoria;
    private RSMaterialComponent.RSTextFieldTwo txt_descrip;
    private RSMaterialComponent.RSTextFieldTwo txt_descrip1;
    private RSMaterialComponent.RSTextFieldTwo txt_descrip2;
    private javax.swing.JLabel txt_fecha2;
    private RSMaterialComponent.RSTextFieldTwo txt_precio;
    private RSMaterialComponent.RSTextFieldTwo txt_precio1;
    private RSMaterialComponent.RSTextFieldTwo txt_precio2;
    // End of variables declaration//GEN-END:variables
}
