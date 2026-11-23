
/**
 * Modela un punto en el plano cartesiano bidimensional (2D).
 *
 * @author Alexis Eduardo Muñoz Trujillo
 * @version 1.1
 */
public class Punto {

    // ATRIBUTOS PRIVADOS
    private double x;
    private double y;

    // CONSTRUCTORES

    /**
     * Inicializa el punto exactamente en el origen de coordenadas (0.0, 0.0).
     */
    public Punto() {
        this.x = 0.0;
        this.y = 0.0;
    }

    /**
     * Inicializa el punto en las coordenadas cartesianas especificadas.
     *
     * @param x Coordenada en el eje X.
     * @param y Coordenada en el eje Y.
     */
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // METODOS DE CONSULTA Y ASIGNACION (GETTERS Y SETTERS)

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // METODOS CALCULADORES [ OPERACIONES GEOMETRICAS ]

    /**
     * Desplaza el punto sumando desplazamientos a sus coordenadas actuales.
     *
     * @param deltaX Desplazamiento horizontal.
     * @param deltaY Desplazamiento vertical.
     */
    public void desplazar(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    /**
     * Calcula la distancia euclidiana entre este punto y otro punto recibido.
     *
     * @param otroPunto El punto destino.
     * @return La distancia entre ambos puntos. Si otroPunto es null, regresa -1.0.
     */
    public double distancia(Punto otroPunto) {
        if (otroPunto == null) {
            return -1.0;
        }

        double diferenciaX = otroPunto.getX() - this.x;
        double diferenciaY = otroPunto.getY() - this.y;

        return Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY);
    }

    /**
     * Calcula y retorna el punto medio entre este punto y otro punto recibido.
     *
     * @param otro El otro punto para calcular el punto medio.
     * @return Una nueva instancia de Punto con las coordenadas medias.
     *         Si otro es null, regresa null.
     */
    public Punto puntoMedio(Punto otro) {
        if (otro == null) {
            return null;
        }

        double medioX = (this.x + otro.getX()) / 2.0;
        double medioY = (this.y + otro.getY()) / 2.0;

        return new Punto(medioX, medioY);
    }

    /**
     * Determina el cuadrante del plano cartesiano en el que se ubica el punto.
     *
     * @return 1, 2, 3 o 4 segun el cuadrante; 0 si esta sobre un eje.
     */
    public int cuadrante() {
        if (this.x == 0.0 || this.y == 0.0) {
            return 0;
        } else if (this.x > 0.0 && this.y > 0.0) {
            return 1;
        } else if (this.x < 0.0 && this.y > 0.0) {
            return 2;
        } else if (this.x < 0.0 && this.y < 0.0) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * Determina si este punto y otros dos puntos recibidos son colineales.
     *
     * @param p2 Segundo punto a evaluar.
     * @param p3 Tercer punto a evaluar.
     * @return true si los tres puntos estan alineados; false en caso contrario.
     */
    public boolean estanAlineados(Punto p2, Punto p3) {
        if (p2 == null || p3 == null) {
            return false;
        }

        double delta = (p2.getX() - this.x) * (p3.getY() - this.y)
                     - (p2.getY() - this.y) * (p3.getX() - this.x);

        return Math.abs(delta) < 1e-9;
    }

    /**
     * Regresa una representacion textual del punto en formato (x, y).
     *
     * @return Cadena con las coordenadas del punto.
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * Compara si este punto es equivalente a otro objeto recibido.
     *
     * @param obj El objeto a comparar.
     * @return true si el objeto es un Punto con coordenadas equivalentes.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof Punto)) {
            return false;
        }

        Punto otro = (Punto) obj;
        double tolerancia = 1e-6;

        return Math.abs(this.x - otro.getX()) < tolerancia
            && Math.abs(this.y - otro.getY()) < tolerancia;
    }
}
