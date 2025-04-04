package co.edu.uniquindio;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        String expresion = "(1*(2-3)) + (4+5)";

        System.out.println(ConvertidorPrefija.convertir(expresion));

        System.out.println(ConvertidorPostfija.convertir(expresion));
    }
}
