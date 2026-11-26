/**
 * Clase utilitaria para la cotizacion de tarifas de envio
 * y verificacion de integridad de paquetes en el sistema postal.
 * 
 * @author ICC 2027-1
 * @version 1.0
 */
public class CotizadorEnvio {

    /**
     * Valida que la referencia recibida corresponda a una instancia existente
     * y con atributos en rangos coherentes.
     * 
     * @param p El paquete a evaluar.
     * @return true si la referencia no es nula y sus dimensiones y peso son validos;
     *         false si es null o presenta datos inconsistentes.
     */
    public static boolean esPaqueteValido(Paquete p) {
        if (p == null) {
            return false;
        }
        return p.getCodigoRastreo() != null 
            && !p.getCodigoRastreo().trim().isEmpty()
            && p.getPeso() > 0.0
            && p.getAlto() > 0.0
            && p.getAncho() > 0.0
            && p.getLargo() > 0.0
            && p.getValorDeclarado() >= 0.0;
    }

    /**
     * Cotiza el costo del envio con base en una tarifa escalonada sobre el peso cobrable:
     * <ul>
     *   <li>Hasta 5 kg: cuota fija de $120.00.</li>
     *   <li>Mas de 5 kg y hasta 15 kg: $120.00 mas $18.00 por cada kg que exceda los 5 kg.</li>
     *   <li>Mas de 15 kg: $300.00 mas $25.00 por cada kg que exceda los 15 kg.</li>
     * </ul>
     * 
     * @param p El paquete a cotizar.
     * @return El importe total del envio en pesos, o -1.0 si el paquete es invalido o null.
     */
    public static double cotizarEnvio(Paquete p) {
        if (!esPaqueteValido(p)) {
            return -1.0;
        }

        double pesoCobrable = p.obtenerPesoCobrable();

        if (pesoCobrable <= 5.0) {
            return 120.0;
        } else if (pesoCobrable <= 15.0) {
            return 120.0 + ((pesoCobrable - 5.0) * 18.0);
        } else {
            return 300.0 + ((pesoCobrable - 15.0) * 25.0);
        }
    }
}

