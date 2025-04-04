package co.edu.uniquindio;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ConvertidorPrefija {
    private static final Stack<Character> pilaOperadores = new Stack<>();
    private static final Deque<Character> pilaResultado = new ArrayDeque<>();

    public static String convertir(String expresionInfija) {
        StringBuilder sb = new StringBuilder();

        for (int i = expresionInfija.length() - 1; i >= 0; i--) {
            Character c = expresionInfija.charAt(i);

            if (c != ' ') {
                if (verificarEsOperador(c)) {
                    pilaOperadores.push(c);

                    if (c == '(') {
                        agregarOperacionAResultado();
                    }
                } else {
                    if (verificarEsOperando(c)) {
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

        while(!pilaResultado.isEmpty()){
            sb.append(pilaResultado.poll());
        }

        return sb.toString();
    }

    private static void agregarOperacionAResultado() {
        pilaOperadores.pop();
        while(!pilaOperadores.isEmpty() && !pilaOperadores.peek().equals(')')) {
            pilaResultado.push(pilaOperadores.pop());
        }
        if(pilaOperadores.peek().equals(')')) {
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
