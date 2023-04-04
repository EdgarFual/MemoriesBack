package Funciones;

// Autor @BrowmPC
public class Decimales_2Digitos {

    private char caracter;

    public Decimales_2Digitos() {
    }

    public String Decimal_DobleS(double number) {
        // Obteniendo el caracter .
        caracter = String.valueOf((double) Math.round((number) * 100d) / 100).charAt(String.valueOf((double) Math.round((number) * 100d) / 100).length() - 2);
        // Si el despues del caracter tiene una cifra se agrega un 0
        if (caracter == '.') {
            // retornamos el dato ingresado con 2 cifras despues del caracter .
            return String.valueOf(((double) Math.round(number * 100d) / 100)) + 0;
        }
        // Si el despues del caracter tiene dos cifra se retorna el mismo valor
        return String.valueOf(((double) Math.round(number * 100d) / 100));
    }

}
