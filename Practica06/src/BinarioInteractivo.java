
import java.util.Scanner;

public class BinarioInteractivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Declaracion de variables
        int numero = 0;
        int residuo;
        String binario;
        char respuesta = 's';

        // Ciclo para convertir varios numeros
        while (respuesta == 's' || respuesta == 'S') {

            // Solicitar un numero entero
            System.out.println("Ingresa un numero entero no negativo:");

            boolean valido = false;

            while (!valido) {
                if (sc.hasNextInt()) {
                    numero = sc.nextInt();

                    if (numero >= 0) {
                        valido = true;
                    } else {
                        System.out.println("Error, el numero no puede ser negativo");
                        System.out.println("Ingresa nuevamente el numero:");
                    }
                } else {
                    System.out.println("Error, debes ingresar un numero entero");
                    sc.next();
                    System.out.println("Ingresa nuevamente el numero:");
                }
            }

            // Convertir el numero decimal a binario
            binario = "";

            if (numero == 0) {
                binario = "0";
            } else {
                while (numero > 0) {
                    residuo = numero % 2;
                    binario = residuo + binario;
                    numero = numero / 2;
                }
            }

            // Mostrar el resultado
            System.out.println("El numero en binario es: " + binario);

            // Preguntar si desea convertir otro numero
            System.out.println("Deseas convertir otro numero? (s/n)");
            respuesta = sc.next().charAt(0);
        }

        System.out.println("Gracias por utilizar el programa");
        sc.close();
    }
}
