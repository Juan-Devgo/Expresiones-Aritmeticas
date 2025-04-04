package co.edu.uniquindio;

import java.util.LinkedList;
import java.util.Stack;

public class ConvertidorPostfija {
    private static final Stack<Character> pilaOperadores = new Stack<>();
    private static final LinkedList<Character> pilaResultado = new LinkedList<>();

    public static String convertir(String expresionInfija) {
        StringBuilder sb = new StringBuilder();

        for (Character c : expresionInfija.toCharArray()) {

            if (c != ' ') {
                if (verificarEsOperador(c)){
                    pilaOperadores.push(c);

                    if (c == ')') {
                        agregarOperacionAResultado();
                    }
                } else {
                    if (verificarEsOperando(c)){
                        pilaResultado.push(c);
                    } else {
                        throw new ExpresionAritmeticaInvalida();
                    }
                }
            }
        }

        if(!pilaOperadores.isEmpty()){
            while (!pilaOperadores.isEmpty()){
                pilaResultado.push(pilaOperadores.pop());
            }
        }

        while (!pilaResultado.isEmpty()){
            sb.append(pilaResultado.pollLast());
        }

        return sb.toString();
    }

    private static void agregarOperacionAResultado() {
        pilaOperadores.pop();
        while(!pilaOperadores.isEmpty() && !pilaOperadores.peek().equals('(')) {
            pilaResultado.push(pilaOperadores.pop());
        }
        if(pilaOperadores.peek().equals('(')) {
            pilaOperadores.pop();
        }
    }

    private static boolean verificarEsOperador(Character caracter) {
        return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/' || caracter == '%' ||
                caracter == '(' || caracter == ')' ;
    }

    private static boolean verificarEsOperando(Character caracter) {
        return Character.isDigit(caracter);
    }
}
