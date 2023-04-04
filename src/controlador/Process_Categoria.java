package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Categoria;

public class Process_Categoria {

    String barra = File.separator; //Es lo mismo que decir \\
    String rutaCategoria = "Inventario" + barra + "bd_categorias.txt";

    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    File f;

    // Categoria
    public ArrayList<Categoria> arrayCat = new ArrayList();

    //Registrar Categoria
    public void insertarCat(Categoria c) throws FileNotFoundException, IOException {
        fw = new FileWriter(rutaCategoria, true);
        fw.write(c.getDescipcion() + "\n");
        fw.close();
    }

    // leer las categorias
    public void leerCat() throws FileNotFoundException, IOException {
        arrayCat.clear();
        fr = new FileReader(rutaCategoria);
        br = new BufferedReader(fr);
        String cad = br.readLine();
        String[] vec;
        while (cad != null) {
            vec = cad.split("-");
            Object[] fila = {vec[0]};
            arrayCat.add(new Categoria(fila));
            cad = br.readLine();
        }
        fr.close();
    }

    public void actualizarCat(ArrayList<Categoria> array) throws FileNotFoundException, IOException {
        f = new File(rutaCategoria);
        fw = new FileWriter(rutaCategoria, true);
        //BORRAR EL ARCHIVO
        fw.close();
        f.delete();

        //CREAR UN NUEVO ARCHIVO
        f = new File(rutaCategoria);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        fw = new FileWriter(f, true);

        for (Categoria c : array) { //recorrer todo el arraylist 1 al ultimo          
            fw.write(c.getDescipcion() + "\n");
        }

        fw.close();

    }

}
