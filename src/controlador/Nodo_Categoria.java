package controlador;

import modelo.Categoria;

public class Nodo_Categoria {

    private Categoria informacion;
    private Nodo_Categoria siguiente;

    public Nodo_Categoria(Categoria c) {
        this.informacion = c;
        this.siguiente = null;
    }

    public Categoria getInformacion() {
        return informacion;
    }

    public void setInformacion(Categoria informacion) {
        this.informacion = informacion;
    }

    public Nodo_Categoria getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_Categoria siguiente) {
        this.siguiente = siguiente;
    }  
    
}
