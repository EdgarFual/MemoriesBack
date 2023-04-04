
package controlador;

import modelo.Inventario;

public class Nodo_Inventario {
    private Inventario informacion;
    private Nodo_Inventario enlace;

    public Nodo_Inventario(Inventario inventario) {
        this.informacion = inventario;
        this.enlace = this;
    }

    public Inventario getInformacion() {
        return informacion;
    }

    public void setInformacion(Inventario informacion) {
        this.informacion = informacion;
    }

    public Nodo_Inventario getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo_Inventario enlace) {
        this.enlace = enlace;
    }

    
    
}
