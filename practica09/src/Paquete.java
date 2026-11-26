/**
 * Modela un paquete postal con sus dimensiones, peso y valor declarado.
 *
 * @author Alexis Eduardo Munoz Trujillo
 * @version 1.1
 */
public class Paquete {
    private String codigoRastreo;
    private double peso;
    private double alto;
    private double ancho;
    private double largo;
    private double valorDeclarado;
    private boolean envioExpress;

    /** Crea un paquete con valores predeterminados. */
    public Paquete() {
        this.codigoRastreo = "ND-000";
        this.peso = 1.0;
        this.alto = 10.0;
        this.ancho = 10.0;
        this.largo = 10.0;
        this.valorDeclarado = 100.0;
        this.envioExpress = false;
    }

    /** Crea un paquete con los datos indicados. */
    public Paquete(String codigoRastreo, double peso, double alto, double ancho,
                   double largo, double valorDeclarado) {
        this(codigoRastreo, peso, alto, ancho, largo, valorDeclarado, false);
    }

    /** Crea un paquete con los datos indicados y modalidad express. */
    public Paquete(String codigoRastreo, double peso, double alto, double ancho,
                   double largo, double valorDeclarado, boolean envioExpress) {
        this.codigoRastreo = (codigoRastreo != null && !codigoRastreo.trim().isEmpty())
                ? codigoRastreo : "SIN-CODIGO";
        this.peso = peso > 0.0 ? peso : 1.0;
        this.alto = alto > 0.0 ? alto : 10.0;
        this.ancho = ancho > 0.0 ? ancho : 10.0;
        this.largo = largo > 0.0 ? largo : 10.0;
        this.valorDeclarado = valorDeclarado >= 0.0 ? valorDeclarado : 0.0;
        this.envioExpress = envioExpress;
    }

    /** Copia los valores de otro paquete; si es null usa los valores predeterminados solicitados. */
    public Paquete(Paquete otro) {
        if (otro == null) {
            this.codigoRastreo = "ND-000";
            this.peso = 1.0;
            this.alto = 10.0;
            this.ancho = 10.0;
            this.largo = 10.0;
            this.valorDeclarado = 100.0;
            this.envioExpress = false;
        } else {
            this.codigoRastreo = otro.codigoRastreo;
            this.peso = otro.peso;
            this.alto = otro.alto;
            this.ancho = otro.ancho;
            this.largo = otro.largo;
            this.valorDeclarado = otro.valorDeclarado;
            this.envioExpress = otro.envioExpress;
        }
    }

    public String getCodigoRastreo() { return codigoRastreo; }
    public void setCodigoRastreo(String codigoRastreo) {
        if (codigoRastreo != null && !codigoRastreo.trim().isEmpty()) this.codigoRastreo = codigoRastreo;
    }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { if (peso > 0.0) this.peso = peso; }
    public double getAlto() { return alto; }
    public void setAlto(double alto) { if (alto > 0.0) this.alto = alto; }
    public double getAncho() { return ancho; }
    public void setAncho(double ancho) { if (ancho > 0.0) this.ancho = ancho; }
    public double getLargo() { return largo; }
    public void setLargo(double largo) { if (largo > 0.0) this.largo = largo; }
    public double getValorDeclarado() { return valorDeclarado; }
    public void setValorDeclarado(double valorDeclarado) { if (valorDeclarado >= 0.0) this.valorDeclarado = valorDeclarado; }
    public boolean isEnvioExpress() { return envioExpress; }
    public void setEnvioExpress(boolean envioExpress) { this.envioExpress = envioExpress; }

    /** Calcula el peso volumetrico usando el factor 5000. */
    public double calculaPesoVolumetrico() { return (largo * ancho * alto) / 5000.0; }

    /** Regresa el mayor entre el peso fisico y el volumetrico. */
    public double obtenerPesoCobrable() { return Math.max(peso, calculaPesoVolumetrico()); }

    /** @return Ficha tecnica del paquete. */
    @Override
    public String toString() {
        return "Paquete: " + codigoRastreo
                + "\n  Peso fisico: " + peso + " kg"
                + "\n  Peso volumetrico: " + calculaPesoVolumetrico() + " kg"
                + "\n  Peso cobrable: " + obtenerPesoCobrable() + " kg"
                + "\n  Dimensiones (alto x ancho x largo): " + alto + " x " + ancho + " x " + largo + " cm"
                + "\n  Valor declarado: $" + valorDeclarado + " MXN"
                + "\n  Envio express: " + (envioExpress ? "Si" : "No");
    }
}
