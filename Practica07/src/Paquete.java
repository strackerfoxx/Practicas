/**
 * Clase que modela un paquete para una empresa de envios postales y paqueteria.
 * Permite registrar sus dimensiones espaciales, peso fisico y valor declarado,
 * asi como calcular su peso volumetrico y determinar el peso cobrable final.
 * 
 * @author Alex
 * @version 1.0
 */
public class Paquete {

    // *** ATRIBUTOS ***
    // POR HACER: Declara los atributos necesarios de acuerdo a tu diseno y a la documentacion.
    // Recuerda aplicar el principio de encapsulamiento (declararlos privados).

    private String codigoRastreo;
    private double pesoFisico;
    private double alto;
    private double ancho;
    private double largo;
    private double valorDeclarado;
    private boolean envioExpress;

    // *** CONSTRUCTORES ***
    // POR HACER: Agrega el constructor por defecto y el constructor con parametros.
    // En el constructor con parametros, asegura que el paquete cuente con dimensiones validas.

    public Paquete() {
        codigoRastreo = "SIN-CODIGO";
        pesoFisico = 1.0;
        alto = 1.0;
        ancho = 1.0;
        largo = 1.0;
        valorDeclarado = 0.0;
        envioExpress = false;
    }

    /**
     * Constructor que recibe los datos del paquete.
     *
     * @param codigoRastreo codigo que identifica al paquete
     * @param pesoFisico peso real del paquete en kilogramos
     * @param alto altura del paquete en centimetros
     * @param ancho anchura del paquete en centimetros
     * @param largo longitud del paquete en centimetros
     * @param valorDeclarado valor monetario del contenido
     */
    
    public Paquete(String codigoRastreo, double pesoFisico,
                   double alto, double ancho, double largo,
                   double valorDeclarado) {

        // Inicializar con valores validos
        this.codigoRastreo = "SIN-CODIGO";
        this.pesoFisico = 1.0;
        this.alto = 1.0;
        this.ancho = 1.0;
        this.largo = 1.0;
        this.valorDeclarado = 0.0;
        this.envioExpress = false;

        // Asignar los valores recibidos mediante los setters
        setCodigoRastreo(codigoRastreo);
        setPesoFisico(pesoFisico);
        setAlto(alto);
        setAncho(ancho);
        setLargo(largo);
        setValorDeclarado(valorDeclarado);
    }


    // *** METODOS DE ACCESO Y ASIGNACION ***
    // POR HACER: Agrega los metodos de consulta (getters) y de modificacion (setters) necesarios.

    public String getCodigoRastreo() {
        return codigoRastreo;
    }

    public void setCodigoRastreo(String codigoRastreo) {
        if (codigoRastreo != null && !codigoRastreo.trim().isEmpty()) {
            this.codigoRastreo = codigoRastreo;
        }
    }

    public double getPesoFisico() {
        return pesoFisico;
    }

    public void setPesoFisico(double pesoFisico) {
        if (pesoFisico > 0) {
            this.pesoFisico = pesoFisico;
        }
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        if (alto > 0) {
            this.alto = alto;
        }
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        if (ancho > 0) {
            this.ancho = ancho;
        }
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        if (largo > 0) {
            this.largo = largo;
        }
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        if (valorDeclarado >= 0) {
            this.valorDeclarado = valorDeclarado;
        }
    }

    public boolean getEnvioExpress() {
        return envioExpress;
    }

    public void setEnvioExpress(boolean envioExpress) {
        this.envioExpress = envioExpress;
    }


    // *** METODOS CALCULADORES BASE ***

    /**
     * Calcula el peso volumetrico del paquete segun el estandar comercial (IATA):
     * (largo * ancho * alto) / 5000.
     * 
     * @return El peso volumetrico equivalente en kilogramos.
     */
    public double calculaPesoVolumetrico() {
        return (largo * ancho * alto) / 5000.0;
    }

    /**
     * Determina el peso cobrable final para efectos de tarificacion postal,
     * el cual corresponde al valor maximo entre el peso fisico y el volumetrico.
     * 
     * @return El mayor entre el peso real y el peso volumetrico.
     */
    public double obtenerPesoCobrable() {
        if (pesoFisico > calculaPesoVolumetrico()) {
            return pesoFisico;
        } else {
            return calculaPesoVolumetrico();
        }
    }


    // *** PROPUESTA PERSONAL ***
    // POR HACER: Agrega aqui al menos un nuevo metodo calculador util para la empresa
    // (ejemplo: recargo por envio express, costo por material fragil, descuento local, etc.)
    // Incluye sus comentarios Javadoc y los atributos necesarios.

    public double calcularRecargoExpress(double costoEnvio) {
        if (envioExpress) {
            return costoEnvio * 0.30;
        } else {
            return 0.0;
        }
    }
}
