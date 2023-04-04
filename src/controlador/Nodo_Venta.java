package controlador;

import modelo.Venta;

public class Nodo_Venta {

    private Venta informacion;
    private Nodo_Venta siguiente;

    public Nodo_Venta(Venta v) {
        this.informacion = v;
        this.siguiente = null;
    }

    public Venta getInformacion() {
        return informacion;
    }

    public void setInformacion(Venta informacion) {
        this.informacion = informacion;
    }

    public Nodo_Venta getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_Venta siguiente) {
        this.siguiente = siguiente;
    }

    

}
