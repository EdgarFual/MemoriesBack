package controlador;

import java.util.ArrayList;
import modelo.Clientes;
import modelo.Empleados;

public class ListaCircular_Empleados {

    private Nodo_Empleados lc;

    public ListaCircular_Empleados() {
        lc = null;
    }

    public boolean vacio() {
        if (lc == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insertar(Empleados client) {
        Nodo_Empleados nuevo = new Nodo_Empleados(client);
        if (!vacio()) {
            nuevo.setEnlace(lc.getEnlace());
            lc.setEnlace(nuevo);
        }
        lc = nuevo;
    }

    public ArrayList<Empleados> listado() {
        ArrayList<Empleados> array = new ArrayList();
        Nodo_Empleados p;
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
        Nodo_Empleados p;
        if (lc != null) {
            p = lc.getEnlace();
            do {
                suma++;
                p = p.getEnlace();
            } while (p != lc.getEnlace());
        }
        return suma;
    }

    public String ObteniendoDNI() {
        String cod = "";
        Nodo_Empleados p;
        if (lc != null) {
            p = lc.getEnlace();
            do {
                cod = p.getInformacion().getNro_Documento();
                p = p.getEnlace();
            } while (p != lc.getEnlace());
        }
        return cod;
    }

    // Buscar por Nro Documento
    public Empleados buscarDocumento(String dni) {
        Nodo_Empleados actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = dni.equals(actual.getInformacion().getNro_Documento());
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = dni.equals(actual.getInformacion().getNro_Documento()); //verificar que haya sido encontrado

        if (encontrado) {
            return actual.getInformacion();
        } else {
            return null;
        }
    }

    // Buscar por codigo Interno
    public Empleados buscarCodInterno(String dni) {
        Nodo_Empleados actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = dni.equals(actual.getInformacion().getCod_Interno());
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = dni.equals(actual.getInformacion().getCod_Interno()); //verificar que haya sido encontrado

        if (encontrado) {
            return actual.getInformacion();
        } else {
            return null;
        }
    }

    public boolean actualizar(Empleados client) {
        Nodo_Empleados actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = client.getNro_Documento().equals(actual.getInformacion().getNro_Documento());
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = client.getNro_Documento().equals(actual.getInformacion().getNro_Documento()); //verificar que haya sido encontrado

        if (encontrado) {
            actual.setInformacion(client);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminar(String dni) {
        Nodo_Empleados actual = lc;
        boolean encontrado = false;
        while ((actual.getEnlace() != lc) && (!encontrado)) {
            encontrado = actual.getEnlace().getInformacion().getNro_Documento().equalsIgnoreCase(dni);
            if (!encontrado) {
                actual = actual.getEnlace();
            }
        }

        encontrado = actual.getEnlace().getInformacion().getNro_Documento().equalsIgnoreCase(dni);

        if (encontrado) {
            Nodo_Empleados p = actual.getEnlace();
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
