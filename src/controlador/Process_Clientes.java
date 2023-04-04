package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Clientes;

public class Process_Clientes {

    String barra = File.separator; //Es lo mismo que decir \\
    String ruta = "RegistroClientes" + barra + "bd_clientes.txt";
   
    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    File f;

    public ArrayList<Clientes> lista = new ArrayList();

    public void insertar(Clientes c) throws FileNotFoundException, IOException {
        fw = new FileWriter(ruta, true);
        // Tipo de Documeto, nro de documento, nombres, apellidos, sexo, celular, e-mail, direccion, usuario, fecha, hora
        fw.write(c.getTip_Docu() + "\t" + c.getDNI() + "\t" + c.getNombres() + "\t" + c.getApellidos() + "\t" + c.getSexo()
                + "\t" + c.getCelular() + "\t" + c.getEmail() + "\t" + c.getDireccion() + "\t" + c.getUsuario() + "\t"
                + c.getFecha_Reg() + "\t" + c.getHora_Reg() + "\n");
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
            // Tipo de Documeto, nro de documento, nombres, apellidos, sexo, celular, e-mail, direccion, usuario, fecha, hora
            Object[] fila = {vec[0], vec[1], vec[2], vec[3], vec[4], Integer.parseInt(vec[5]),
                vec[6], vec[7], vec[8], vec[9], vec[10]};
            lista.add(new Clientes(fila));
            cad = br.readLine();

        }
        fr.close();
    }

    public void actualizar(ArrayList<Clientes> array) throws FileNotFoundException, IOException {
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
        for (Clientes c : array) { //recorrer todo el arraylist        
            fw.write(c.getTip_Docu() + "\t" + c.getDNI() + "\t" + c.getNombres() + "\t" + c.getApellidos() + "\t" + c.getSexo()
                    + "\t" + c.getCelular() + "\t" + c.getEmail() + "\t" + c.getDireccion() + "\t" + c.getUsuario() + "\t"
                    + c.getFecha_Reg() + "\t" + c.getHora_Reg() + "\n");
        }
        fw.close();

    }
}
