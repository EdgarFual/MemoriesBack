package controlador;

import modelo.Ingresos_Ventas;

public class Nodo_Ingresos {

    private Ingresos_Ventas informacion;
    private Nodo_Ingresos siguiente;

    public Nodo_Ingresos(Ingresos_Ventas i) {
        this.informacion = i;
        this.siguiente = null;
    }

    public Ingresos_Ventas getInformacion() {
        return informacion;
    }

    public void setInformacion(Ingresos_Ventas informacion) {
        this.informacion = informacion;
    }

    public Nodo_Ingresos getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_Ingresos siguiente) {
        this.siguiente = siguiente;
    }

}
