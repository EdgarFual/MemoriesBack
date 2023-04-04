package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Ingresos_Ventas;

public class Cola_Ingresos {

    private Nodo_Ingresos frente, fincola;
    LocalDate FechaActual = LocalDate.now();

    public Cola_Ingresos() {
        frente = fincola = null;
    }

    public boolean empty() {
        if (frente == null) {
            return true;
        } else {
            return false;
        }
    }

    public void encolar(Ingresos_Ventas objIngresos) { //insertar un elemento al final de la cola
        Nodo_Ingresos nuevo = new Nodo_Ingresos(objIngresos);

        if (empty()) {
            frente = nuevo;
        } else {
            fincola.setSiguiente(nuevo);
        }
        fincola = nuevo;
        fincola.setSiguiente(null);
    }

    public void desencolar() {   //eliminar el elemento que se encuentra en el frente
        if (!empty()) {
            Nodo_Ingresos aux = frente;
            frente = frente.getSiguiente();
            aux.setSiguiente(null);
        }
    }

    // Conteo de cantidad de Operaciones de Ingresos
    public int nro_Operaciones() {
        Nodo_Ingresos aux = frente;
        int suma = 0;
        while (aux != null) {
            suma++;
            aux = aux.getSiguiente();
        }
        return suma;
    }

    // Calculando el importe Neto
    public double ImporteActual() {
        Nodo_Ingresos aux = frente;
        double importe = 0;
        while (aux != null) {
            if (aux.getInformacion().getFecha_actual().equals(String.valueOf(FechaActual))) {
                importe = importe + aux.getInformacion().getTotal();
            }
            aux = aux.getSiguiente();
        }
        return importe;
    }

    // Calculando el importe Subtotal
    public double SubTotalActual() {
        Nodo_Ingresos aux = frente;
        double subt = 0;
        while (aux != null) {
            if (aux.getInformacion().getFecha_actual().equals(String.valueOf(FechaActual))) {
                subt = subt + aux.getInformacion().getImporte_venta();
            }
            aux = aux.getSiguiente();
        }
        return subt;
    }

    // Calculando el importe descuento
    public double DescuentoActual() {
        Nodo_Ingresos aux = frente;
        double descuento = 0;
        while (aux != null) {
            if (aux.getInformacion().getFecha_actual().equals(String.valueOf(FechaActual))) {
                descuento = descuento + aux.getInformacion().getDescuento();
            }
            aux = aux.getSiguiente();
        }
        return descuento;
    }

    // Sacando tipos de Pagos con tarjeta
    public double Cont_Tarjeta() {
        Nodo_Ingresos aux = frente;
        double sumaTarjeta = 0;
        while (aux != null) {
            if (aux.getInformacion().getTipo_pago().equals("Tarjeta de Debito y/o Credito")) {
                sumaTarjeta++;
            }
            aux = aux.getSiguiente();
        }
        return sumaTarjeta;
    }

    // Sacando tipo de Pago con efectivo
    public double Cont_efectivo() {
        Nodo_Ingresos aux = frente;
        double sumaPagoEfectivo = 0;
        while (aux != null) {
            if (aux.getInformacion().getTipo_pago().equals("Pago en Efectivo")) {
                sumaPagoEfectivo++;
            }
            aux = aux.getSiguiente();
        }
        return sumaPagoEfectivo;
    }

    public ArrayList<Ingresos_Ventas> listado() {
        Nodo_Ingresos aux = frente;
        ArrayList<Ingresos_Ventas> array = new ArrayList();
        while (aux != null) {
            array.add(aux.getInformacion());
            aux = aux.getSiguiente();
        }
        return array;
    }

    public Ingresos_Ventas buscar(String no_operacion) {
        Nodo_Ingresos aux = frente;
        while (aux != null && !no_operacion.equalsIgnoreCase(aux.getInformacion().getNo_Operacion())) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            return aux.getInformacion();
        } else {
            return null;
        }
    }

    public boolean actualizar(Ingresos_Ventas objIngresos) {
        Nodo_Ingresos aux = frente;
        while (aux != null && !objIngresos.getNo_Operacion().equalsIgnoreCase(aux.getInformacion().getNo_Operacion())) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            aux.setInformacion(objIngresos);
            return true;
        } else {
            return false;
        }
    }

}
