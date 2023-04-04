package modelo;

import Funciones.Decimales_2Digitos;

public class Ingresos_Ventas {

    private String Tipo_pago, Fecha_actual, Hora_actual, Cupon_descuento, Registrador_por, No_Operacion;
    private double Importe_venta, descuento, Total;

    // Decimales
    Decimales_2Digitos d2 = new Decimales_2Digitos();

    public Ingresos_Ventas() {
    }

    public Ingresos_Ventas(Object[] ingresos) {
        this.No_Operacion = ingresos[0].toString();
        this.Tipo_pago = ingresos[1].toString();
        this.Importe_venta = Double.parseDouble(ingresos[2].toString());
        this.Cupon_descuento = ingresos[3].toString();
        this.descuento = Double.parseDouble(ingresos[4].toString());
        this.Total = Double.parseDouble(ingresos[5].toString());
        this.Registrador_por = ingresos[6].toString();
        this.Fecha_actual = ingresos[7].toString();
        this.Hora_actual = ingresos[8].toString();
    }

    // Obteniendo informacion
    public Object[] getInformacionIngresos() {
        //No. Operacion, Medio de Pago, Importe, Cupon, Descuento, Total, Registrado por, Fecha actual, hora actual
        Object[] arreglo = {getNo_Operacion(), getTipo_pago(), d2.Decimal_DobleS(getImporte_venta()), getCupon_descuento(),
            d2.Decimal_DobleS(getDescuento()), d2.Decimal_DobleS(getTotal()), getRegistrador_por(), getFecha_actual(), getHora_actual()};
        return arreglo;
    }

    public String getTipo_pago() {
        return Tipo_pago;
    }

    public void setTipo_pago(String Tipo_pago) {
        this.Tipo_pago = Tipo_pago;
    }

    public String getFecha_actual() {
        return Fecha_actual;
    }

    public void setFecha_actual(String Fecha_actual) {
        this.Fecha_actual = Fecha_actual;
    }

    public String getCupon_descuento() {
        return Cupon_descuento;
    }

    public void setCupon_descuento(String Cupon_descuento) {
        this.Cupon_descuento = Cupon_descuento;
    }

    public String getRegistrador_por() {
        return Registrador_por;
    }

    public void setRegistrador_por(String Registrador_por) {
        this.Registrador_por = Registrador_por;
    }

    public String getNo_Operacion() {
        return No_Operacion;
    }

    public void setNo_Operacion(String No_Operacion) {
        this.No_Operacion = No_Operacion;
    }

    public double getImporte_venta() {
        return Importe_venta;
    }

    public void setImporte_venta(double Importe_venta) {
        this.Importe_venta = Importe_venta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public String getHora_actual() {
        return Hora_actual;
    }

    public void setHora_actual(String Hora_actual) {
        this.Hora_actual = Hora_actual;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

}
