package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import modelo.factory_settings;

public class Process_factorySettings {

    String barra = File.separator; //Es lo mismo que decir \\
    String ruta = "System_MB" + barra + "factorySettings.txt";

    FileReader fr;
    BufferedReader br;
    FileWriter fw;
    File f;

    public ArrayList<factory_settings> lista = new ArrayList();

    public void insertar(factory_settings fs) throws FileNotFoundException, IOException {
        fw = new FileWriter(ruta, true);
        /*nombre usuario, contraseña usuario, tipo de usuario, nombre de compañia, registrado por, fecha de registro,
          Hora de registro
         */
        fw.write(fs.getCodigoEmpleado() + "\t" + fs.getName_user() + "\t" + fs.getPassword_user() + "\t" + fs.getType_of_user() + "\t"
                + fs.getCompany_name() + "\t" + fs.getRegistered_by() + "\t" + fs.getRegistration_date() + "\t"
                + fs.getRegistration_time() + "\n");
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
            /*nombre usuario, contraseña usuario, tipo de usuario, nombre de compañia, registrado por, fecha de registro,
              Dia de registro, Hora de registro
             */
            Object[] fila = {vec[0], vec[1], vec[2], vec[3], vec[4], vec[5], vec[6], vec[7]};
            lista.add(new factory_settings(fila));
            cad = br.readLine();

        }
        fr.close();
    }

    public void actualizar(ArrayList<factory_settings> array) throws FileNotFoundException, IOException {
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
        for (factory_settings fs : array) { //recorrer todo el arraylist        
            fw.write(fs.getCodigoEmpleado() + "\t" + fs.getName_user() + "\t" + fs.getPassword_user() + "\t" + fs.getType_of_user() + "\t"
                    + fs.getCompany_name() + "\t" + fs.getRegistered_by() + "\t" + fs.getRegistration_date() + "\t"
                    + fs.getRegistration_time() + "\n");
        }
        fw.close();

    }
}
