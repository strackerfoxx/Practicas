import java.util.Scanner;

/**
 * Menu interactivo para administrar guias postales.
 *
 * @author Alexis Eduardo Munoz Trujillo
 * @version 1.1
 */
public class MenuPostal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Envio guiaActiva = null;
        boolean continuar = true;
        System.out.println("================================================================");
        System.out.println("  SISTEMA INTERACTIVO DE GUIAS POSTALES | ICC 2027-1");
        System.out.println("================================================================");
        while (continuar) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Emitir nueva guia de envio");
            System.out.println("2. Clonar guia activa con un nuevo paquete");
            System.out.println("3. Clonar paquete de la guia activa para otro destinatario");
            System.out.println("4. Generar guia de retorno");
            System.out.println("5. Mostrar detalle y costo de la guia activa");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1": guiaActiva = emitirNuevaGuia(sc); break;
                case "2": guiaActiva = clonarGuiaActiva(sc, guiaActiva); break;
                case "3": guiaActiva = enviarPaqueteClonado(sc, guiaActiva); break;
                case "4": guiaActiva = generarRetorno(guiaActiva); break;
                case "5": mostrarGuiaActiva(guiaActiva); break;
                case "0": continuar = false; System.out.println("Saliendo del sistema postal..."); break;
                default: System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
        sc.close();
    }

    private static Paquete capturarPaquete(Scanner sc) {
        System.out.println("\n--- Captura de datos del Paquete ---");
        System.out.print("Codigo de rastreo: ");
        String codigo = sc.nextLine().trim();
        System.out.print("Peso fisico en kg: ");
        double peso = leerDouble(sc, 1.0);
        System.out.print("Alto en cm: ");
        double alto = leerDouble(sc, 10.0);
        System.out.print("Ancho en cm: ");
        double ancho = leerDouble(sc, 10.0);
        System.out.print("Largo en cm: ");
        double largo = leerDouble(sc, 10.0);
        System.out.print("Valor declarado en MXN: ");
        double valor = leerDouble(sc, 100.0);
        System.out.print("Es envio express? (s/n): ");
        boolean express = sc.nextLine().trim().equalsIgnoreCase("s");
        return new Paquete(codigo, peso, alto, ancho, largo, valor, express);
    }

    private static double leerDouble(Scanner sc, double valorPorOmision) {
        if (sc.hasNextDouble()) {
            double valor = sc.nextDouble();
            sc.nextLine();
            return valor;
        }
        sc.nextLine();
        System.out.println("Dato no numerico; se usara " + valorPorOmision + ".");
        return valorPorOmision;
    }

    private static Persona capturarPersona(Scanner sc) {
        System.out.print("Identificador (ej. PER-01): ");
        String id = sc.nextLine().trim();
        System.out.print("Nombre completo ficticio: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Ciudad o codigo postal: ");
        String ubicacion = sc.nextLine().trim();
        return new Persona(id, nombre, ubicacion);
    }

    private static Envio emitirNuevaGuia(Scanner sc) {
        System.out.println("\n--- Captura del REMITENTE ---");
        Persona remitente = capturarPersona(sc);
        System.out.println("\n--- Captura del DESTINATARIO ---");
        Persona destinatario = capturarPersona(sc);
        Paquete paquete = capturarPaquete(sc);
        Envio nuevaGuia = new Envio(remitente, destinatario, paquete);
        System.out.println("\nGuia emitida con exito:\n" + nuevaGuia);
        return nuevaGuia;
    }

    private static Envio clonarGuiaActiva(Scanner sc, Envio guiaActiva) {
        if (guiaActiva == null) {
            System.out.println("Aviso: Primero debe emitir una guia.");
            return null;
        }
        System.out.println("Capture los datos del nuevo paquete:");
        Envio nuevaGuia = new Envio(guiaActiva, capturarPaquete(sc));
        System.out.println("Nueva guia creada:\n" + nuevaGuia);
        return nuevaGuia;
    }

    private static Envio enviarPaqueteClonado(Scanner sc, Envio guiaActiva) {
        if (guiaActiva == null) {
            System.out.println("Aviso: Primero debe emitir una guia.");
            return null;
        }
        System.out.println("Capture los datos del nuevo destinatario:");
        Persona destinatario = capturarPersona(sc);
        Paquete copia = new Paquete(guiaActiva.getPaquete());
        copia.setCodigoRastreo("CLON-" + copia.getCodigoRastreo());
        Envio nuevaGuia = new Envio(guiaActiva.getRemitente(), destinatario, copia);
        System.out.println("Nueva guia creada con paquete clonado:\n" + nuevaGuia);
        return nuevaGuia;
    }

    private static Envio generarRetorno(Envio guiaActiva) {
        if (guiaActiva == null) {
            System.out.println("Aviso: Primero debe emitir una guia.");
            return null;
        }
        Envio retorno = guiaActiva.generarGuiaRetorno();
        System.out.println("Guia de retorno generada:\n" + retorno);
        return retorno;
    }

    private static void mostrarGuiaActiva(Envio guiaActiva) {
        if (guiaActiva == null) {
            System.out.println("Aviso: No hay ninguna guia activa.");
        } else {
            System.out.println(guiaActiva);
            System.out.println("Costo calculado: $" + guiaActiva.calcularCostoTotal() + " MXN");
        }
    }
}
