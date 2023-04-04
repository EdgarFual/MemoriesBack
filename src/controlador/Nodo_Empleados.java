package controlador;

import modelo.Empleados;

public class Nodo_Empleados {

    private Empleados informacion;
    private Nodo_Empleados enlace;

    public Nodo_Empleados(Empleados clientes) {
        this.informacion = clientes;
        this.enlace = this;
    }

    public Empleados getInformacion() {
        return informacion;
    }

    public void setInformacion(Empleados informacion) {
        this.informacion = informacion;
    }

    public Nodo_Empleados getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo_Empleados enlace) {
        this.enlace = enlace;
    }

}
