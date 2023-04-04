package controlador;

import modelo.factory_settings;

public class Nodo_Settings {

    private factory_settings informacion;
    private Nodo_Settings enlace;

    public Nodo_Settings(factory_settings fs) {
        this.informacion = fs;
        this.enlace = this;
    }

    public factory_settings getInformacion() {
        return informacion;
    }

    public void setInformacion(factory_settings informacion) {
        this.informacion = informacion;
    }

    public Nodo_Settings getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo_Settings enlace) {
        this.enlace = enlace;
    }

}
