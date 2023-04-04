package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Categoria;
import modelo.Inventario;

public class Process_Inventario {

    String barra = File.separator; //Es lo mismo que decir \\
    String ruta = "Inventario" + barra + "bd_inventario.txt";
    String rutaCategoria = "Inventario" + barra + "bd_categorias.txt";

    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    File f;

    // Inventario
    public ArrayList<Inventario> lista = new ArrayList();
    // Categoria
    public ArrayList<Categoria> arrayCat = new ArrayList();

    public void insertar(Inventario i) throws FileNotFoundException, IOException {
        fw = new FileWriter(ruta, true);
        // codigo,descripcion,categoria,stock,precio,fecha,usuario
        fw.write(i.getCodigo() + "\t" + i.getDescripcion() + "\t" + i.getCategoria() + "\t" + i.getCantidad_Stock()
                + "\t" + i.getPrecio() + "\t" + i.getFecha_Registro() + "\t" + i.getUsuario() + "\n");
        fw.close();
    }

    public void leer() throws FileNotFoundException, IOException {
        lista.clear();
        fr = new FileReader(ruta);
        br = new BufferedReader(fr);
        String cad = br.readLine();
        String[] vec;
        while (cad != null) {
            vec = cad.split("\t");
            // codigo,descripcion,categoria,stock,precio,fecha,usuario
            Object[] fila = {vec[0], vec[1], vec[2], Integer.parseInt(vec[3]), Double.parseDouble(vec[4]), vec[5], vec[6]};
            lista.add(new Inventario(fila));
            cad = br.readLine();
        }
        fr.close();
    }

    public void actualizar(ArrayList<Inventario> array) throws FileNotFoundException, IOException {
        f = new File(ruta);
        fw = new FileWriter(ruta, true);
        //BORRAR EL ARCHIVO
        fw.close();
        f.delete();

        //CREAR UN NUEVO ARCHIVO
        f = new File(ruta);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        fw = new FileWriter(f, true);
        for (Inventario i : array) { //recorrer todo el arraylist 1 al ultimo          
            fw.write(i.getCodigo() + "\t" + i.getDescripcion() + "\t" + i.getCategoria() + "\t" + i.getCantidad_Stock()
                    + "\t" + i.getPrecio() + "\t" + i.getFecha_Registro() + "\t" + i.getUsuario() + "\n");
        }
        fw.close();

    }

//---------------------------------------------------------------------------------------------------
    
}
