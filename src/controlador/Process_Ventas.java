package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Venta;

public class Process_Ventas {

    String barra = File.separator; //Es lo mismo que decir \\
    String rutaCategoria = "System_MB" + barra + "sales.txt";

    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    File f;

    // Categoria
    public ArrayList<Venta> arrayv = new ArrayList();

    //Registrar Categoria
    public void insertar(Venta i) throws FileNotFoundException, IOException {
        fw = new FileWriter(rutaCategoria, true);
        fw.write(i.getTipe_Comprobante() + "\t" + i.getNro_Comprobante() + "\t" + i.getNro_Operacion()
                + "\t" + i.getCliente() + "\t" + i.getMedio_Pago() + "\t" + i.getTotal() + "\t" + i.getUsuario() + "\t" + i.getFecha_Reg()
                + "\t" + i.getHora_Reg() + "\n");
        fw.close();
    }

    // leer las categorias
    public void leer() throws FileNotFoundException, IOException {
        arrayv.clear();
        fr = new FileReader(rutaCategoria);
        br = new BufferedReader(fr);
        String cad = br.readLine();
        String[] vec;
        while (cad != null) {
            vec = cad.split("\t");
            //Tipo comprobante, Nro comprobante, Nro Operacion, Cliente, medio de pago, Total, Registrado por, Fecha actual, hora actual
            Object[] fila = {vec[0], vec[1], vec[2], vec[3], vec[4], Double.parseDouble(vec[5]), vec[6], vec[7], vec[8]};
            arrayv.add(new Venta(fila));
            cad = br.readLine();
        }
        fr.close();
    }

    public void actualizar(ArrayList<Venta> array) throws FileNotFoundException, IOException {
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

        for (Venta i : array) { //recorrer todo el arraylist 1 al ultimo          
            fw.write(i.getTipe_Comprobante() + "\t" + i.getNro_Comprobante() + "\t" + i.getNro_Operacion()
                    + "\t" + i.getCliente() + "\t" + i.getMedio_Pago() + "\t" + i.getTotal() + "\t" + i.getUsuario() + "\t" + i.getFecha_Reg()
                    + "\t" + i.getHora_Reg() + "\n");
            fw.close();
        }

        fw.close();

    }

}
