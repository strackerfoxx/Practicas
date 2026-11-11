
/**
 * Contiene metodos para validar paquetes, cotizar envios
 * y determinar si un paquete requiere seguro.
 *
 * @author Alex
 * @version 1.0
 */
public class CotizadorEnvio {

    /**
     * Verifica que un paquete exista y tenga datos validos.
     *
     * @param p paquete que se desea validar
     * @return true si el paquete es valido, false en caso contrario
     */
    public static boolean esPaqueteValido(Paquete p) {

        if (p == null) {
            return false;
        }

        if (p.getCodigoRastreo() == null ||
                p.getCodigoRastreo().trim().isEmpty()) {
            return false;
        }

        if (p.getPesoFisico() <= 0 ||
                p.getAlto() <= 0 ||
                p.getAncho() <= 0 ||
                p.getLargo() <= 0 ||
                p.getValorDeclarado() < 0) {
            return false;
        }

        return true;
    }

    /**
     * Calcula el costo del envio de acuerdo con el peso cobrable.
     *
     * @param p paquete que se desea cotizar
     * @return costo del envio o -1.0 si el paquete no es valido
     */
    public static double cotizarEnvio(Paquete p) {

        if (!esPaqueteValido(p)) {
            return -1.0;
        }

        double peso = p.obtenerPesoCobrable();
        double costo;

        if (peso <= 5) {
            costo = 120.0;

        } else if (peso <= 15) {
            costo = 120.0 + (peso - 5) * 18.0;

        } else {
            costo = 300.0 + (peso - 15) * 25.0;
        }

        return costo;
    }

    /**
     * Determina si un paquete necesita seguro.
     *
     * @param p paquete que se desea evaluar
     * @return true si requiere seguro, false en caso contrario
     */
    public static boolean requiereSeguro(Paquete p) {

        if (!esPaqueteValido(p)) {
            return false;
        }

        boolean valorAlto = p.getValorDeclarado() > 3000;

        boolean dimensionExcedida =
                p.getAlto() > 60 ||
                p.getAncho() > 60 ||
                p.getLargo() > 60;

        double sumaDimensiones =
                p.getAlto() + p.getAncho() + p.getLargo();

        boolean volumenExcedido = sumaDimensiones > 150;

        return valorAlto || dimensionExcedida || volumenExcedido;
    }
}
