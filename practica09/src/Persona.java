/**
 * Representa a una persona que participa como remitente o destinatario.
 *
 * @author Alexis Eduardo Munoz Trujillo
 * @version 1.1
 */
public class Persona {
    private String identificador;
    private String nombreCompleto;
    private String ubicacion;

    /** Crea una persona con datos ficticios predeterminados. */
    public Persona() {
        this("PER-000", "Persona sin registrar", "Ciudad de Mexico");
    }

    /** Crea una persona validando que sus cadenas no sean nulas ni vacias. */
    public Persona(String identificador, String nombreCompleto, String ubicacion) {
        this.identificador = validarTexto(identificador, "PER-000");
        this.nombreCompleto = validarTexto(nombreCompleto, "Persona sin registrar");
        this.ubicacion = validarTexto(ubicacion, "Ubicacion no especificada");
    }

    private String validarTexto(String texto, String valorAlternativo) {
        return texto != null && !texto.trim().isEmpty() ? texto.trim() : valorAlternativo;
    }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) {
        if (identificador != null && !identificador.trim().isEmpty()) this.identificador = identificador.trim();
    }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto != null && !nombreCompleto.trim().isEmpty()) this.nombreCompleto = nombreCompleto.trim();
    }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) {
        if (ubicacion != null && !ubicacion.trim().isEmpty()) this.ubicacion = ubicacion.trim();
    }

    /** Compara ubicaciones sin distinguir mayusculas y minusculas. */
    public boolean mismaUbicacionQue(Persona otra) {
        return otra != null && ubicacion.equalsIgnoreCase(otra.ubicacion);
    }

    /** Compara la identidad mediante el identificador unico. */
    public boolean esLaMismaPersona(Persona otra) {
        return otra != null && identificador.equalsIgnoreCase(otra.identificador);
    }

    @Override
    public String toString() {
        return "Persona: " + nombreCompleto + " [" + identificador + "]\n  Ubicacion: " + ubicacion;
    }
}
