package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Ingresos_Ventas;

public class Process_Ingresos {

    String barra = File.separator; //Es lo mismo que decir \\
    String rutaCategoria = "System_MB" + barra + "TradeIncome.txt";

    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    File f;

    // Categoria
    public ArrayList<Ingresos_Ventas> arrayCat = new ArrayList();

    //Registrar Categoria
    public void insertar(Ingresos_Ventas i) throws FileNotFoundException, IOException {
        fw = new FileWriter(rutaCategoria, true);
        fw.write(i.getNo_Operacion() + "\t" + i.getTipo_pago() + "\t" + i.getImporte_venta()
                + "\t" + i.getCupon_descuento() + "\t" + i.getDescuento() + "\t" + i.getTotal() + "\t" + i.getRegistrador_por() + "\t" + i.getFecha_actual()
                + "\t" + i.getHora_actual() + "\n");
        fw.close();
    }

    // leer las categorias
    public void leer() throws FileNotFoundException, IOException {
        arrayCat.clear();
        fr = new FileReader(rutaCategoria);
        br = new BufferedReader(fr);
        String cad = br.readLine();
        String[] vec;
        while (cad != null) {
            vec = cad.split("\t");
            //No. Operacion, Medio de Pago, Importe, Cupon, Descuento, Total, Registrado por, Fecha actual, hora actual
            Object[] fila = {vec[0], vec[1], Double.parseDouble(vec[2]), vec[3], Double.parseDouble(vec[4]),
                Double.parseDouble(vec[5]), vec[6], vec[7], vec[8]};
            arrayCat.add(new Ingresos_Ventas(fila));
            cad = br.readLine();
        }
        fr.close();
    }

    public void actualizar(ArrayList<Ingresos_Ventas> array) throws FileNotFoundException, IOException {
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

        for (Ingresos_Ventas i : array) { //recorrer todo el arraylist 1 al ultimo          
            fw.write(i.getNo_Operacion() + "\t" + i.getTipo_pago() + "\t" + i.getImporte_venta()
                    + "\t" + i.getCupon_descuento() + "\t" + i.getDescuento() + "\t" + i.getTotal() + "\t" + i.getRegistrador_por() + "\t" + i.getFecha_actual()
                    + "\t" + i.getHora_actual() + "\n");
            fw.close();
        }

        fw.close();

    }

}
