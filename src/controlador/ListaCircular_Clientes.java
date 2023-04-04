package controlador;

import java.util.ArrayList;
import modelo.Clientes;

public class ListaCircular_Clientes {

    private Nodo_Clientes lc;

    public ListaCircular_Clientes() {
        lc = null;
    }

    public boolean vacio() {
        if (lc == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insertar(Clientes client) {
        Nodo_Clientes nuevo = new Nodo_Clientes(client);
        if (!vacio()) {
            nuevo.setEnlace(lc.getEnlace());
            lc.setEnlace(nuevo);
        }
        lc = nuevo;
    }

    public ArrayList<Clientes> listado() {
        ArrayList<Clientes> array = new ArrayList();
        Nodo_Clientes p;
        if (lc != null) {
            p = lc.getEnlace();
            do {
                array.add(p.getInformacion());
                p = p.getEnlace();
            } while (p != lc.getEnlace());
        }
        return array;
    }

    // Calculando el Longuitud
    public int Longitud() {
        int suma = 0;
        Nodo_Clientes p;
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
    public String ObteniendoDNI() {
        String cod = "";
        Nodo_Clientes p;
        if (lc != null) {
            p = lc.getEnlace();
            do {
                cod = p.getInformacion().getDNI();
                p = p.getEnlace();
            } while (p != lc.getEnlace());
        }
        return cod;
    }

    public Clientes buscar(String dni) {
        Nodo_Clientes actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = dni.equals(actual.getInformacion().getDNI());
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = dni.equals(actual.getInformacion().getDNI()); //verificar que haya sido encontrado

        if (encontrado) {
            return actual.getInformacion();
        } else {
            return null;
        }
    }

    public boolean actualizar(Clientes client) {
        Nodo_Clientes actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = client.getDNI().equals(actual.getInformacion().getDNI());
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = client.getDNI().equals(actual.getInformacion().getDNI()); //verificar que haya sido encontrado

        if (encontrado) {
            actual.setInformacion(client);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminar(String dni) {
        Nodo_Clientes actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = actual.getEnlace().getInformacion().getDNI().equalsIgnoreCase(dni);
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = actual.getEnlace().getInformacion().getDNI().equalsIgnoreCase(dni);

        if (encontrado) {
            Nodo_Clientes p = actual.getEnlace();
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
