import java.util.UUID;

/**
 * Guia postal que agrega remitente, destinatario y paquete.
 *
 * @author Alexis Eduardo Munoz Trujillo
 * @version 1.1
 */
public class Envio {
    public static final double CARGO_FORANEO = 80.0;
    private static int totalGuiasEmitidas = 0;

    private Persona remitente;
    private Persona destinatario;
    private Paquete paquete;
    private String folioGuia;

    /** Emite una guia nueva validando las referencias recibidas. */
    public Envio(Persona remitente, Persona destinatario, Paquete paquete) {
        this.remitente = remitente != null ? remitente : new Persona();
        this.destinatario = destinatario != null ? destinatario : new Persona();
        this.paquete = CotizadorEnvio.esPaqueteValido(paquete) ? paquete : new Paquete();
        this.folioGuia = generarFolio();
    }

    /** Crea una guia nueva conservando remitente y destinatario de otra guia. */
    public Envio(Envio guiaBase, Paquete nuevoPaquete) {
        if (guiaBase == null) {
            this.remitente = new Persona();
            this.destinatario = new Persona();
        } else {
            this.remitente = guiaBase.remitente;
            this.destinatario = guiaBase.destinatario;
        }
        this.paquete = CotizadorEnvio.esPaqueteValido(nuevoPaquete) ? nuevoPaquete : new Paquete();
        this.folioGuia = generarFolio();
    }

    private static String generarFolio() {
        totalGuiasEmitidas++;
        String aleatorio = UUID.randomUUID().toString().replace("-", "").substring(0, 3).toUpperCase();
        return "GUIA-" + aleatorio + "-" + totalGuiasEmitidas;
    }

    public String getFolioGuia() { return folioGuia; }
    public Persona getRemitente() { return remitente; }
    public Persona getDestinatario() { return destinatario; }
    public Paquete getPaquete() { return paquete; }
    public static int getTotalGuiasEmitidas() { return totalGuiasEmitidas; }

    public boolean esEnvioForaneo() { return !remitente.mismaUbicacionQue(destinatario); }
    public boolean esAutoEnvio() { return remitente.esLaMismaPersona(destinatario); }

    /** Cotiza el paquete y suma el cargo fijo si las ubicaciones son distintas. */
    public double calcularCostoTotal() {
        double costoBase = CotizadorEnvio.cotizarEnvio(paquete);
        if (costoBase < 0.0) return -1.0;
        return costoBase + (esEnvioForaneo() ? CARGO_FORANEO : 0.0);
    }

    /** Cambia el destinatario si la referencia recibida es valida. */
    public boolean cambiarDestinatario(Persona nuevoDestinatario) {
        if (nuevoDestinatario == null) return false;
        destinatario = nuevoDestinatario;
        return true;
    }

    /** Crea una guia inversa y un paquete independiente con prefijo RET-. */
    public Envio generarGuiaRetorno() {
        Paquete paqueteRetorno = new Paquete(paquete);
        paqueteRetorno.setCodigoRastreo("RET-" + paquete.getCodigoRastreo());
        return new Envio(destinatario, remitente, paqueteRetorno);
    }

    @Override
    public String toString() {
        String tipo = esEnvioForaneo() ? "FORANEO" : "LOCAL";
        return "========== GUIA DE ENVIO ==========\n"
                + "Folio: " + folioGuia + "\nTipo de ruta: " + tipo
                + "\nAutoenvio: " + (esAutoEnvio() ? "Si" : "No")
                + "\n\nREMITENTE\n" + remitente
                + "\n\nDESTINATARIO\n" + destinatario
                + "\n\nMERCANCIA\n" + paquete
                + "\n\nCosto total: $" + calcularCostoTotal() + " MXN"
                + "\n===================================\n";
    }
}
