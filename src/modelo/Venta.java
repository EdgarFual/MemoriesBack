package modelo;

import Funciones.Decimales_2Digitos;

public class Venta {

    private String Tipe_Comprobante, Nro_Comprobante, Nro_Operacion, Cliente, Medio_Pago,
            usuario, fecha_Reg, Hora_Reg;
    private double Total;

    // Decimales
    Decimales_2Digitos d2 = new Decimales_2Digitos();

    public Venta() {
    }

    public Venta(Object[] ventas) {
        this.Tipe_Comprobante = ventas[0].toString();
        this.Nro_Comprobante = ventas[1].toString();
        this.Nro_Operacion = ventas[2].toString();
        this.Cliente = ventas[3].toString();
        this.Medio_Pago = ventas[4].toString();
        this.Total = Double.parseDouble(ventas[5].toString());
        this.usuario = ventas[6].toString();
        this.fecha_Reg = ventas[7].toString();
        this.Hora_Reg = ventas[8].toString();
    }

    //Obtener Informacion
    public Object[] getInformacionVentas() {
        Object arreglo[] = {getTipe_Comprobante(), getNro_Comprobante(), getNro_Operacion(), getCliente(), getMedio_Pago(),
            d2.Decimal_DobleS(getTotal()), getUsuario(), getFecha_Reg(), getHora_Reg()};
        return arreglo;
    }

    public String getTipe_Comprobante() {
        return Tipe_Comprobante;
    }

    public void setTipe_Comprobante(String Tipe_Comprobante) {
        this.Tipe_Comprobante = Tipe_Comprobante;
    }

    public String getNro_Comprobante() {
        return Nro_Comprobante;
    }

    public void setNro_Comprobante(String Nro_Comprobante) {
        this.Nro_Comprobante = Nro_Comprobante;
    }

    public String getNro_Operacion() {
        return Nro_Operacion;
    }

    public void setNro_Operacion(String Nro_Operacion) {
        this.Nro_Operacion = Nro_Operacion;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getMedio_Pago() {
        return Medio_Pago;
    }

    public void setMedio_Pago(String Medio_Pago) {
        this.Medio_Pago = Medio_Pago;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha_Reg() {
        return fecha_Reg;
    }

    public void setFecha_Reg(String fecha_Reg) {
        this.fecha_Reg = fecha_Reg;
    }

    public String getHora_Reg() {
        return Hora_Reg;
    }

    public void setHora_Reg(String Hora_Reg) {
        this.Hora_Reg = Hora_Reg;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

}
