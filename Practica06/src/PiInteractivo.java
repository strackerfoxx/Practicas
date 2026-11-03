import java.util.Scanner;

public class PiInteractivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Declaracion de variables
        int cantidadDardos = 0;
        int dardosDentro = 0;
        double x;
        double y;
        double pi;

        // Solicitar la cantidad de dardos
        System.out.println("Ingresa la cantidad de dardos:");

        while (cantidadDardos <= 0) {
            if (sc.hasNextInt()) {
                cantidadDardos = sc.nextInt();

                if (cantidadDardos <= 0) {
                    System.out.println("Error, la cantidad debe ser mayor que cero");
                    System.out.println("Ingresa nuevamente la cantidad de dardos:");
                }
            } else {
                System.out.println("Error, debes ingresar un numero entero");
                sc.next();
                System.out.println("Ingresa nuevamente la cantidad de dardos:");
            }
        }

        // Generar los dardos y contar los que caen dentro del cuarto de circulo
        int contador = 0;

        while (contador < cantidadDardos) {
            x = Math.random();
            y = Math.random();

            if (x * x + y * y <= 1) {
                dardosDentro++;
            }

            contador++;
        }

        // Calcular la estimacion de pi
        pi = 4.0 * dardosDentro / cantidadDardos;

        // Mostrar resultados
        System.out.println("Cantidad de dardos: " + cantidadDardos);
        System.out.println("Dardos dentro del circulo: " + dardosDentro);
        System.out.printf("Estimacion de pi: %.6f%n", pi);

        sc.close();
    }
}
