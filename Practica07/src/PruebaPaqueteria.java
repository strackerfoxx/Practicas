
/**
 * Programa de prueba para verificar el funcionamiento de las clases
 * Paquete y CotizadorEnvio.
 *
 * @author Alex
 * @version 1.0
 */
public class PruebaPaqueteria {

    /**
     * Ejecuta los casos de prueba del sistema de paqueteria.
     *
     * @param args argumentos de la linea de comandos
     */
    public static void main(String[] args) {

        System.out.println("======================================================");
        System.out.println("  PRUEBAS DEL SISTEMA DE ENVIOS POSTALES (PRACTICA 7) ");
        System.out.println("======================================================\n");

        // CASO 1: Paquete estandar pequeno
        System.out.println("--- Caso 1: Paquete pequeno estandar ---");

        Paquete p1 = new Paquete("PK-001", 3.5, 20, 15, 10, 800);

        mostrarDetallePaquete(p1);
        System.out.println("Es valido?: " + CotizadorEnvio.esPaqueteValido(p1));
        System.out.printf("Costo de envio cotizado: $%.2f (Esperado: $120.00)%n",
                CotizadorEnvio.cotizarEnvio(p1));
        System.out.println("Requiere seguro?: " + CotizadorEnvio.requiereSeguro(p1));
        System.out.println();

        // CASO 2: Paquete voluminoso
        System.out.println("--- Caso 2: Paquete voluminoso (volumen predomina) ---");

        Paquete p2 = new Paquete("PK-002", 2.0, 50, 40, 50, 1500);

        mostrarDetallePaquete(p2);

        if (CotizadorEnvio.esPaqueteValido(p2)) {
            System.out.printf("Peso volumetrico calculado: %.2f kg%n",
                    p2.calculaPesoVolumetrico());
            System.out.printf("Peso cobrable final: %.2f kg%n",
                    p2.obtenerPesoCobrable());
        }

        System.out.printf("Costo de envio cotizado: $%.2f (Esperado: $425.00)%n",
                CotizadorEnvio.cotizarEnvio(p2));
        System.out.println("Requiere seguro?: " + CotizadorEnvio.requiereSeguro(p2));
        System.out.println();

        // CASO 3: Paquete de alto valor comercial
        System.out.println("--- Caso 3: Paquete de alto valor monetario ---");

        Paquete p3 = new Paquete("PK-003", 4.0, 30, 25, 20, 6500);

        mostrarDetallePaquete(p3);
        System.out.printf("Costo de envio: $%.2f%n",
                CotizadorEnvio.cotizarEnvio(p3));
        System.out.println("Requiere seguro?: " +
                CotizadorEnvio.requiereSeguro(p3) + " (Esperado: true)");
        System.out.println();

        // CASO 4: Paquete sobredimensionado
        System.out.println("--- Caso 4: Paquete sobredimensionado ---");

        Paquete p4 = new Paquete("PK-004", 5.0, 70, 30, 20, 1200);

        mostrarDetallePaquete(p4);
        System.out.println("Requiere seguro?: " +
                CotizadorEnvio.requiereSeguro(p4) + " (Esperado: true por dimensiones)");
        System.out.println();

        // CASO 5: Referencia nula
        System.out.println("--- Caso 5: Validacion de referencia nula (null) ---");

        Paquete pNulo = null;

        System.out.println("Es valido un paquete null?: " +
                CotizadorEnvio.esPaqueteValido(pNulo) + " (Esperado: false)");
        System.out.printf("Cotizacion de un paquete null: $%.2f (Esperado: -1.00)%n",
                CotizadorEnvio.cotizarEnvio(pNulo));
        System.out.println("Requiere seguro un paquete null?: " +
                CotizadorEnvio.requiereSeguro(pNulo) + " (Esperado: false)");
        System.out.println();

        // CASO 6: Pruebas de la propuesta personal
        System.out.println("======================================================");
        System.out.println("        PRUEBAS DE TU PROPUESTA PERSONAL              ");
        System.out.println("======================================================");

        Paquete pExpress = new Paquete("PK-005", 3.5, 20, 15, 10, 800);

        // Activar el envio express
        pExpress.setEnvioExpress(true);

        double costoNormal = CotizadorEnvio.cotizarEnvio(pExpress);
        double recargo = pExpress.calcularRecargoExpress(costoNormal);
        double costoTotal = costoNormal + recargo;

        System.out.println("Codigo: " + pExpress.getCodigoRastreo());
        System.out.println("Envio express: " + pExpress.getEnvioExpress());
        System.out.printf("Costo normal: $%.2f%n", costoNormal);
        System.out.printf("Recargo express (30%%): $%.2f%n", recargo);
        System.out.printf("Costo total con express: $%.2f%n", costoTotal);

        System.out.println();

        // CASO ADICIONAL: Envio sin modalidad express
        System.out.println("--- Prueba sin envio express ---");

        Paquete pNormal = new Paquete("PK-006", 3.5, 20, 15, 10, 800);

        double costoNormalSinExpress = CotizadorEnvio.cotizarEnvio(pNormal);
        double recargoNormal = pNormal.calcularRecargoExpress(costoNormalSinExpress);

        System.out.printf("Costo normal: $%.2f%n", costoNormalSinExpress);
        System.out.printf("Recargo express: $%.2f (Esperado: $0.00)%n", recargoNormal);
    }

    /**
     * Imprime los datos basicos de un paquete.
     *
     * @param p paquete cuyos datos se desean mostrar
     */
    private static void mostrarDetallePaquete(Paquete p) {

        if (!CotizadorEnvio.esPaqueteValido(p)) {
            System.out.println("  [Paquete no valido o no instanciado]");
            return;
        }

        System.out.println("Codigo de rastreo: " + p.getCodigoRastreo());
        System.out.println("Peso fisico: " + p.getPesoFisico() + " kg");
        System.out.println("Dimensiones: " + p.getAlto() + " x " +
                p.getAncho() + " x " + p.getLargo() + " cm");
        System.out.println("Valor declarado: $" + p.getValorDeclarado());
    }
}
