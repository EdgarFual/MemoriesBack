package modelo;

import Funciones.Decimales_2Digitos;

public class Inventario {

    private String Codigo, Descripcion, Fecha_Registro, Categoria, usuario;
    private int cantidad_Stock;
    private double precio;

    // Decimales
    Decimales_2Digitos d2 = new Decimales_2Digitos();

    // Constructores
    public Inventario() {
    }

    public Inventario(Object[] datosInventario) {
        // codigo,descripcion,categoria,stock,precio,fecha,usuario
        this.Codigo = datosInventario[0].toString();
        this.Descripcion = datosInventario[1].toString();
        this.Categoria = datosInventario[2].toString();
        this.cantidad_Stock = Integer.parseInt(datosInventario[3].toString());
        this.precio = Double.parseDouble(datosInventario[4].toString());
        this.Fecha_Registro = datosInventario[5].toString();
        this.usuario = datosInventario[6].toString();
    }

    // Obteniendo informacion
    public Object[] getInformacionInventario() {
        Object[] arreglo = {getCodigo(), getDescripcion(), getCategoria(), getCantidad_Stock(), d2.Decimal_DobleS(getPrecio()),
            getFecha_Registro(), getUsuario()};
        return arreglo;
    }

    // Metodos Getter y Setter  
    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getFecha_Registro() {
        return Fecha_Registro;
    }

    public void setFecha_Registro(String Fecha_Registro) {
        this.Fecha_Registro = Fecha_Registro;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCantidad_Stock() {
        return cantidad_Stock;
    }

    public void setCantidad_Stock(int cantidad_Stock) {
        this.cantidad_Stock = cantidad_Stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
