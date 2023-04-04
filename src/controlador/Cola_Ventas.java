package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import modelo.Venta;

public class Cola_Ventas {

    private Nodo_Venta frente, fincola;
    LocalDate FechaActual = LocalDate.now();

    public Cola_Ventas() {
        frente = fincola = null;
    }

    public boolean empty() {
        if (frente == null) {
            return true;
        } else {
            return false;
        }
    }

    public void encolar(Venta objIngresos) { //insertar un elemento al final de la cola
        Nodo_Venta nuevo = new Nodo_Venta(objIngresos);

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
            Nodo_Venta aux = frente;
            frente = frente.getSiguiente();
            aux.setSiguiente(null);
        }
    }

    // Conteo de cantidad de ventas
    public int nro_ventas() {
        Nodo_Venta aux = frente;
        int suma = 0;
        while (aux != null) {
            suma++;
            aux = aux.getSiguiente();
        }
        return suma;
    }

    // Calculando el total de Boletas
    public double TotalBoleta() {
        Nodo_Venta aux = frente;
        double nroBolet = 0;
        while (aux != null) {
            if (aux.getInformacion().getTipe_Comprobante().equals("Boleta")) {
                nroBolet++;
            }
            aux = aux.getSiguiente();
        }
        return nroBolet;
    }

    // Calculando el total de Facturas
    public double TotalFactura() {
        Nodo_Venta aux = frente;
        double nroFactur = 0;
        while (aux != null) {
            if (aux.getInformacion().getTipe_Comprobante().equals("Factura")) {
                nroFactur++;
            }
            aux = aux.getSiguiente();
        }
        return nroFactur;
    }

    public ArrayList<Venta> listado() {
        Nodo_Venta aux = frente;
        ArrayList<Venta> array = new ArrayList();
        while (aux != null) {
            array.add(aux.getInformacion());
            aux = aux.getSiguiente();
        }
        return array;
    }

    public Venta buscar(String no_comprobante) {
        Nodo_Venta aux = frente;
        while (aux != null && !no_comprobante.equalsIgnoreCase(aux.getInformacion().getNro_Comprobante())) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            return aux.getInformacion();
        } else {
            return null;
        }
    }

    public boolean actualizar(Venta objVenta) {
        Nodo_Venta aux = frente;
        while (aux != null && !objVenta.getNro_Comprobante().equalsIgnoreCase(aux.getInformacion().getNro_Comprobante())) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            aux.setInformacion(objVenta);
            return true;
        } else {
            return false;
        }
    }

}
