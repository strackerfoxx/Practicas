
import java.util.Scanner;

public class CabanaDelMisterio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Declaracion de variables
        String nombreCliente;
        char tipoCliente;
        String tipoTexto;
        double capital = 0;
        double tasaBase = 0;
        double tasaAplicada;
        int plazo = 0;
        int opcion;
        int operaciones = 0;
        int errores = 0;
        double interesSimple;
        double totalSimple;
        double saldo;
        double tasaMensual;
        double interesCompuesto;
        double totalCompuesto;
        double diferencia;
        boolean valido;
        boolean terminar = false;
        String motivo = "";

        // Registro del nombre del cliente
        System.out.println("Ingresa el nombre completo del cliente:");
        nombreCliente = sc.nextLine();

        while (nombreCliente.trim().isEmpty()) {
            System.out.println("Error, el nombre no puede estar vacio");
            System.out.println("Ingresa nuevamente el nombre:");
            nombreCliente = sc.nextLine();
        }

        // Registro del tipo de cliente
        System.out.println("Ingresa el tipo de cliente (P, E o R):");
        tipoTexto = sc.nextLine();

        while (tipoTexto.length() != 1 ||
                (tipoTexto.charAt(0) != 'P' && tipoTexto.charAt(0) != 'p' &&
                tipoTexto.charAt(0) != 'E' && tipoTexto.charAt(0) != 'e' &&
                tipoTexto.charAt(0) != 'R' && tipoTexto.charAt(0) != 'r')) {

            System.out.println("Error, el tipo debe ser P, E o R");
            System.out.println("Ingresa nuevamente el tipo de cliente:");
            tipoTexto = sc.nextLine();
        }

        tipoCliente = Character.toUpperCase(tipoTexto.charAt(0));

        // Registro del capital
        System.out.println("Ingresa el capital del prestamo:");

        valido = false;
        while (!valido) {
            if (sc.hasNextDouble()) {
                capital = sc.nextDouble();
                sc.nextLine();

                if (capital > 0) {
                    valido = true;
                } else {
                    System.out.println("Error, el capital debe ser mayor que cero");
                    System.out.println("Ingresa nuevamente el capital:");
                }
            } else {
                System.out.println("Error, debes ingresar un numero");
                sc.nextLine();
                System.out.println("Ingresa nuevamente el capital:");
            }
        }

        // Registro de la tasa anual base
        System.out.println("Ingresa la tasa anual base (porcentaje):");

        valido = false;
        while (!valido) {
            if (sc.hasNextDouble()) {
                tasaBase = sc.nextDouble();
                sc.nextLine();

                if (tasaBase >= 0) {
                    valido = true;
                } else {
                    System.out.println("Error, la tasa no puede ser negativa");
                    System.out.println("Ingresa nuevamente la tasa:");
                }
            } else {
                System.out.println("Error, debes ingresar un numero");
                sc.nextLine();
                System.out.println("Ingresa nuevamente la tasa:");
            }
        }

        tasaBase = tasaBase / 100.0;

        // Registro del plazo
        System.out.println("Ingresa el plazo en meses:");

        valido = false;
        while (!valido) {
            if (sc.hasNextInt()) {
                plazo = sc.nextInt();
                sc.nextLine();

                if (plazo > 0) {
                    valido = true;
                } else {
                    System.out.println("Error, el plazo debe ser mayor que cero");
                    System.out.println("Ingresa nuevamente el plazo:");
                }
            } else {
                System.out.println("Error, debes ingresar un numero entero");
                sc.nextLine();
                System.out.println("Ingresa nuevamente el plazo:");
            }
        }

        // Ajustar la tasa segun el tipo de cliente
        tasaAplicada = tasaBase;

        if (tipoCliente == 'P') {
            tasaAplicada = tasaBase - 0.02;
        } else if (tipoCliente == 'R') {
            tasaAplicada = tasaBase + 0.03;
        }

        // Mostrar menu principal
        while (!terminar) {
            System.out.println();
            System.out.println("===== Sistema de prestamos de la Cabana del Misterio =====");
            System.out.println("1) Cotizar con interes simple");
            System.out.println("2) Cotizar con interes compuesto");
            System.out.println("3) Comparar interes simple y compuesto");
            System.out.println("4) Cambiar el capital del prestamo");
            System.out.println("0) Salir");
            System.out.println("Elige una opcion:");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine();

                if (opcion >= 0 && opcion <= 4) {

                    if (opcion == 0) {
                        motivo = "El usuario eligio salir";
                        terminar = true;

                    } else {
                        operaciones++;

                        // Calculo de interes simple
                        interesSimple = capital * tasaAplicada * plazo / 12.0;
                        totalSimple = capital + interesSimple;

                        // Calculo de interes compuesto
                        tasaMensual = tasaAplicada / 12.0;
                        saldo = capital;

                        if (opcion == 1) {
                            System.out.println();
                            System.out.println("===== Cotizacion de interes simple =====");
                            System.out.println("Nombre: " + nombreCliente);
                            System.out.println("Tipo de cliente: " + tipoCliente);
                            System.out.printf("Capital: %.2f%n", capital);
                            System.out.printf("Tasa anual aplicada: %.2f%%%n", tasaAplicada * 100);
                            System.out.println("Plazo en meses: " + plazo);
                            System.out.printf("Interes simple: %.2f%n", interesSimple);
                            System.out.printf("Total a pagar: %.2f%n", totalSimple);

                        } else if (opcion == 2) {
                            System.out.println();
                            System.out.println("===== Cotizacion de interes compuesto =====");
                            System.out.println("Nombre: " + nombreCliente);
                            System.out.println("Tipo de cliente: " + tipoCliente);
                            System.out.printf("Capital: %.2f%n", capital);
                            System.out.printf("Tasa anual aplicada: %.2f%%%n", tasaAplicada * 100);
                            System.out.println("Plazo en meses: " + plazo);

                            for (int mes = 1; mes <= plazo; mes++) {
                                saldo = saldo * (1 + tasaMensual);
                                System.out.printf("Mes %d = %.2f%n", mes, saldo);
                            }

                            totalCompuesto = saldo;
                            interesCompuesto = totalCompuesto - capital;

                            System.out.printf("Interes compuesto: %.2f%n", interesCompuesto);
                            System.out.printf("Total a pagar: %.2f%n", totalCompuesto);

                        } else if (opcion == 3) {
                            saldo = capital;

                            for (int mes = 1; mes <= plazo; mes++) {
                                saldo = saldo * (1 + tasaMensual);
                            }

                            totalCompuesto = saldo;
                            interesCompuesto = totalCompuesto - capital;
                            diferencia = Math.abs(interesCompuesto - interesSimple);

                            System.out.println();
                            System.out.println("===== Comparacion de intereses =====");
                            System.out.printf("Interes simple: %.2f%n", interesSimple);
                            System.out.printf("Interes compuesto: %.2f%n", interesCompuesto);
                            System.out.printf("Diferencia: %.2f%n", diferencia);

                            if (interesSimple > interesCompuesto) {
                                System.out.println("El interes simple es mayor");
                            } else if (interesCompuesto > interesSimple) {
                                System.out.println("El interes compuesto es mayor");
                            } else {
                                System.out.println("Ambos intereses son iguales");
                            }

                        } else if (opcion == 4) {
                            System.out.println("Ingresa el nuevo capital:");

                            valido = false;
                            while (!valido) {
                                if (sc.hasNextDouble()) {
                                    capital = sc.nextDouble();
                                    sc.nextLine();

                                    if (capital > 0) {
                                        valido = true;
                                    } else {
                                        System.out.println("Error, el capital debe ser mayor que cero");
                                        System.out.println("Ingresa nuevamente el capital:");
                                    }
                                } else {
                                    System.out.println("Error, debes ingresar un numero");
                                    sc.nextLine();
                                    System.out.println("Ingresa nuevamente el capital:");
                                }
                            }

                            System.out.printf("El nuevo capital es: %.2f%n", capital);
                        }

                        // Revisar limite de operaciones
                        if (operaciones == 5) {
                            motivo = "Se alcanzo el limite de cinco operaciones";
                            System.out.println("Se alcanzo el limite de operaciones");
                            terminar = true;
                        }
                    }

                } else {
                    errores++;
                    System.out.println("Error, esa opcion no existe");
                }

            } else {
                errores++;
                sc.nextLine();
                System.out.println("Error, debes ingresar un numero entero");
            }

            // Revisar limite de errores
            if (errores >= 3) {
                motivo = "Se alcanzo el limite de tres errores";
                System.out.println("Se alcanzo el limite de errores");
                terminar = true;
            }
        }

        // Resumen final
        System.out.println();
        System.out.println("===== Resumen del prestamo =====");
        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Operaciones efectivas: " + operaciones);
        System.out.println("Errores en el menu: " + errores);
        System.out.printf("Capital final: %.2f%n", capital);
        System.out.println("Motivo de finalizacion: " + motivo);
        System.out.println("Gracias por utilizar el sistema de la Cabana del Misterio");

        sc.close();
    }
}