package controlador;

import java.util.ArrayList;
import modelo.Categoria;

public class Cola_Categorias {

    private Nodo_Categoria frente, fincola;

    public Cola_Categorias() {
        frente = fincola = null;
    }

    public boolean empty() {
        if (frente == null) {
            return true;
        } else {
            return false;
        }
    }

    public void encolar(Categoria objCategoria) { //insertar un elemento al final de la cola
        Nodo_Categoria nuevo = new Nodo_Categoria(objCategoria);

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
            Nodo_Categoria aux = frente;
            frente = frente.getSiguiente();
            aux.setSiguiente(null);
        }
    }

    public ArrayList<Categoria> listado() {
        Nodo_Categoria aux = frente;
        ArrayList<Categoria> array = new ArrayList();
        while (aux != null) {
            array.add(aux.getInformacion());
            aux = aux.getSiguiente();
        }
        return array;
    }

    public Categoria buscar(String descrip) {
        Nodo_Categoria aux = frente;
        while (aux != null && !descrip.equalsIgnoreCase(aux.getInformacion().getDescipcion())) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            return aux.getInformacion();
        } else {
            return null;
        }
    }

    public boolean actualizar(Categoria objCategoria) {
        Nodo_Categoria aux = frente;
        while (aux != null && !objCategoria.getDescipcion().equalsIgnoreCase(aux.getInformacion().getDescipcion())) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            aux.setInformacion(objCategoria);
            return true;
        } else {
            return false;
        }
    }

}
