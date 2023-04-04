package controlador;

import java.util.ArrayList;
import modelo.Inventario;

public class ListaCircular_Inventario {

    private Nodo_Inventario lc;

    public ListaCircular_Inventario() {
        lc = null;
    }

    public boolean vacio() {
        if (lc == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insertar(Inventario invent) {
        Nodo_Inventario nuevo = new Nodo_Inventario(invent);
        if (!vacio()) {
            nuevo.setEnlace(lc.getEnlace());
            lc.setEnlace(nuevo);
        }
        lc = nuevo;
    }

    public ArrayList<Inventario> listado() {
        ArrayList<Inventario> array = new ArrayList();
        Nodo_Inventario p;
        if (lc != null) {
            p = lc.getEnlace();
            do {
                array.add(p.getInformacion());
                p = p.getEnlace();
            } while (p != lc.getEnlace());
        }
        return array;
    }

    // Calculando el total de Stock 
    public int TotalProductos() {
        int suma = 0;
        Nodo_Inventario p;
        if (lc != null) {
            p = lc.getEnlace();
            do {
                suma = suma + p.getInformacion().getCantidad_Stock();
                p = p.getEnlace();
            } while (p != lc.getEnlace());
        }
        return suma;
    }
    
    // Calculando el Longuitud
    public int Longitud() {
        int suma = 0;
        Nodo_Inventario p;
        if (lc != null) {
            p = lc.getEnlace();
            do {
                suma++;
                p = p.getEnlace();
            } while (p != lc.getEnlace());
        }
        return suma;
    }
    
    // Calculando el sueldo por concepto
    public String ObteniendoCodigo() {
        String cod = "";
        Nodo_Inventario p;
        if (lc != null) {
            p = lc.getEnlace();
            do {
                cod = p.getInformacion().getCodigo();
                p = p.getEnlace();
            } while (p != lc.getEnlace());
        }
        return cod;
    }

    public Inventario buscar(String codigo) {
        Nodo_Inventario actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = codigo.equals(actual.getInformacion().getCodigo());
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = codigo.equals(actual.getInformacion().getCodigo()); //verificar que haya sido encontrado

        if (encontrado) {
            return actual.getInformacion();
        } else {
            return null;
        }
    }

    public boolean actualizar(Inventario invent) {
        Nodo_Inventario actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = invent.getCodigo().equals(actual.getInformacion().getCodigo());
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = invent.getCodigo().equals(actual.getInformacion().getCodigo()); //verificar que haya sido encontrado

        if (encontrado) {
            actual.setInformacion(invent);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminar(String codigo) {
        Nodo_Inventario actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = actual.getEnlace().getInformacion().getCodigo().equalsIgnoreCase(codigo);
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = actual.getEnlace().getInformacion().getCodigo().equalsIgnoreCase(codigo);

        if (encontrado) {
            Nodo_Inventario p = actual.getEnlace();
            if (lc == lc.getEnlace()) {  //Lista Circular es de 1 solo nodo
                lc = null;
            } else {
                if (p == lc) {  //Nodo a eliminar es ultimo (cola)
                    lc = actual;
                }
                actual.setEnlace(p.getEnlace());  //eliminar nodo intermedio           
            }
            return true;
        } else {
            return false;
        }
    }
}
