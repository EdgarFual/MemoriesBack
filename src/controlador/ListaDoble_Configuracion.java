package controlador;

import java.util.ArrayList;
import modelo.factory_settings;

public class ListaDoble_Configuracion {

    private Nodo_Settings inicio;

    public ListaDoble_Configuracion() {
        inicio = null;
    }

    public boolean vacio() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insertar(factory_settings fs) {
        Nodo_Settings nuevo = new Nodo_Settings(fs);
        if (!vacio()) {
            nuevo.setEnlace(inicio.getEnlace());
            inicio.setEnlace(nuevo);
        }
        inicio = nuevo;
    }

    public ArrayList<factory_settings> listado() {
        ArrayList<factory_settings> array = new ArrayList();
        Nodo_Settings p;
        if (inicio != null) {
            p = inicio.getEnlace();
            do {
                array.add(p.getInformacion());
                p = p.getEnlace();
            } while (p != inicio.getEnlace());
        }
        return array;
    }

    public factory_settings buscar(String cod) {
        Nodo_Settings aux = inicio;
        boolean encontrado = false;
        while ((aux.getEnlace() != inicio) && (!encontrado)) {
            encontrado = cod.equals(aux.getInformacion().getName_user());
            if (!encontrado) {
                aux = aux.getEnlace();
            }
        }
        encontrado = cod.equals(aux.getInformacion().getName_user());
        if (encontrado) {
            return aux.getInformacion();
        } else {
            return null;
        }
    }

    public boolean actualizar(factory_settings fs) {
        Nodo_Settings aux = inicio;
        boolean encontrado = false;
        while (aux.getEnlace() != inicio && !encontrado) {
            encontrado = fs.getName_user().equals(aux.getInformacion().getName_user());
            if (!encontrado) {
                aux = aux.getEnlace();
            }
        }
        encontrado = fs.getName_user().equals(aux.getInformacion().getName_user());
        if (encontrado) {
            aux.setInformacion(fs);
        }
        return encontrado;
    }

    public boolean eliminar(String name_user) {
        Nodo_Settings actual = inicio;
        boolean encontrado = false;
        while ((actual.getEnlace() != inicio) && (!encontrado)) {
            encontrado = actual.getEnlace().getInformacion().getName_user().equalsIgnoreCase(name_user);
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = actual.getEnlace().getInformacion().getName_user().equalsIgnoreCase(name_user);

        if (encontrado) {
            Nodo_Settings p = actual.getEnlace();
            if (inicio == inicio.getEnlace()) {  //Lista Circular es de 1 solo nodo
                inicio = null;
            } else {
                if (p == inicio) {  //Nodo a eliminar es ultimo (cola)
                    inicio = actual;
                }
                actual.setEnlace(p.getEnlace());  //eliminar nodo intermedio           
            }
            return true;
        } else {
            return false;
        }
    }
}
