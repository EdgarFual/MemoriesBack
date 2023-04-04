
package controlador;

import modelo.Clientes;

public class Nodo_Clientes {
    private Clientes informacion;
    private Nodo_Clientes enlace;

    public Nodo_Clientes(Clientes clientes) {
        this.informacion = clientes;
        this.enlace = this;
    }

    public Clientes getInformacion() {
        return informacion;
    }

    public void setInformacion(Clientes informacion) {
        this.informacion = informacion;
    }

    public Nodo_Clientes getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo_Clientes enlace) {
        this.enlace = enlace;
    } 
       
}
