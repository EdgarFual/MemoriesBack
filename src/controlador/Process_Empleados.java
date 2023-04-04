package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Empleados;

public class Process_Empleados {

    String barra = File.separator; //Es lo mismo que decir \\
    String ruta = "Empleados" + barra + "bd_empleados.txt";
    Empleados objPer;
    FileReader fr;  //lector de archivos
    BufferedReader br;    //recepctor de memoria
    FileWriter fw;        //escribir
    File f;

    public ArrayList<Empleados> listado = new ArrayList();

    public void adicionar(Empleados e) throws FileNotFoundException, IOException {
        //f = new File(ruta);
        fw = new FileWriter(ruta, true);
        //tipo Documento, nro documento, fecha de nacimiento, nombres, Apellidos, sexo, estado civil,
        //domicilio, celular, operador, email, ocupacion,cargo
        String cad = e.getCod_Interno() + "\t" + e.getTipo_Documento() + "\t" + e.getNro_Documento() + "\t" + e.getFecha_Nacimiento()
                + "\t" + e.getNombres() + "\t" + e.getApellidos() + "\t" + e.getSexo() + "\t" + e.getEstado_Civil()
                + "\t" + e.getDomicilio() + "\t" + e.getCelular() + "\t" + e.getOperador_Movil()
                + "\t" + e.getEmail() + "\t" + e.getOcupacion() + "\t" + e.getCargo() + "\t" + e.getStatus()
                + "\t" + e.getRegistrador_Usuario() + "\t" + e.getFecha_Registro() + "\t" + e.getHora_Registro();
        fw.write(cad);
        fw.write(13); //hace un salto de linea
        fw.close();
    }

    public void leer() throws FileNotFoundException, IOException {
        listado.clear();
        fr = new FileReader(ruta);
        br = new BufferedReader(fr);
        String cad = br.readLine();
        String[] vec;
        while (cad != null) {
            vec = cad.split("\t");  //separar la cadena en un vector
            Object[] Registro = {vec[0], vec[1], vec[2], vec[3], vec[4], vec[5], vec[6], vec[7], vec[8],
                Integer.parseInt(vec[9]), vec[10], vec[11], vec[12], vec[13], vec[14], vec[15], vec[16], vec[17]};
            objPer = new Empleados(Registro);
            listado.add(objPer);
            cad = br.readLine();
        }
        br.close();
    }

    public void actualizar(ArrayList<Empleados> array) throws FileNotFoundException, IOException {
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

        for (Empleados e : array) {
            String cad = e.getCod_Interno() + "\t" + e.getTipo_Documento() + "\t" + e.getNro_Documento() + "\t" + e.getFecha_Nacimiento()
                    + "\t" + e.getNombres() + "\t" + e.getApellidos() + "\t" + e.getSexo() + "\t" + e.getEstado_Civil()
                    + "\t" + e.getDomicilio() + "\t" + e.getCelular() + "\t" + e.getOperador_Movil()
                    + "\t" + e.getEmail() + "\t" + e.getOcupacion() + "\t" + e.getCargo() + "\t" + e.getStatus()
                    + "\t" + e.getRegistrador_Usuario() + "\t" + e.getFecha_Registro() + "\t" + e.getHora_Registro();
            fw.write(cad);
            fw.write(13); //hace un salto de linea
        }
        fw.close();
    }

}
