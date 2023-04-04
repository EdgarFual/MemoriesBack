package controlador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import panels.Pnl_ventas;
import views.Comprobante;

public class Process_Comprobante {

    public String barra = File.separator; //Es lo mismo que decir \\
    public String ubicacion = "Comprobantes" + barra + Pnl_ventas.txt_NroRecibo.getText() + ".txt"; //Ubicacion de la creacion de la carpeta
    public static String ruta;

    public Process_Comprobante() {
    }

    public void Crear() throws IOException {

        FileWriter fichero = new FileWriter(ubicacion);
        fichero.write(Comprobante.Vista_Boleta.getText());
        fichero.close();

    }

}
