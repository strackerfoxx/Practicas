/**
 * Modela una estacion meteorologica universitaria que almacena y procesa
 * las temperaturas diarias (en grados Celsius) de un periodo de monitoreo.
 *
 * @author Alexis Eduardo Muñoz Trujillo
 * @version 1.1
 */
public class EstacionMeteorologica {

    /** Valor utilizado para indicar que un dia no tiene lectura registrada. */
    private static final double SIN_LECTURA = -300.0;

    /** Temperatura minima fisicamente valida, en grados Celsius. */
    private static final double TEMPERATURA_MINIMA = -90.0;

    /** Temperatura maxima fisicamente valida, en grados Celsius. */
    private static final double TEMPERATURA_MAXIMA = 60.0;

    /** Nombre de la estacion meteorologica. */
    private String nombreEstacion;

    /** Arreglo con las temperaturas diarias en grados Celsius. */
    private double[] temperaturas;


    // CONSTRUCTORES

    /**
     * Inicializa la estacion con el nombre predeterminado "Estacion UNAM"
     * y reserva espacio para una semana (7 dias), marcando cada posicion
     * inicialmente sin lectura registrada (-300.0).
     */
    public EstacionMeteorologica() {
        nombreEstacion = "Estacion UNAM";
        temperaturas = new double[7];

        // Inicializa cada dia como pendiente de captura.
        for (int i = 0; i < temperaturas.length; i++) {
            temperaturas[i] = SIN_LECTURA;
        }
    }

    /**
     * Crea una estacion con nombre y duracion personalizados.
     *
     * @param nombreEstacion Nombre de la estacion; si es null o vacio se usa
     *                       "Estacion UNAM".
     * @param numDias Cantidad de dias del monitoreo; si no es positiva se usan 7.
     */
    public EstacionMeteorologica(String nombreEstacion, int numDias) {
        if (nombreEstacion == null || nombreEstacion.trim().isEmpty()) {
            this.nombreEstacion = "Estacion UNAM";
        } else {
            this.nombreEstacion = nombreEstacion.trim();
        }

        if (numDias <= 0) {
            numDias = 7;
        }

        temperaturas = new double[numDias];

        // Ningun dia tiene lectura al crear la estacion.
        for (int i = 0; i < temperaturas.length; i++) {
            temperaturas[i] = SIN_LECTURA;
        }
    }

    // METODOS DE CONSULTA Y REGISTRO (PARTE 1)

    /**
     * Devuelve el nombre de la estacion.
     *
     * @return Nombre de la estacion.
     */
    public String getNombreEstacion() {
        return nombreEstacion;
    }

    /**
     * Devuelve la cantidad de dias del periodo de monitoreo.
     *
     * @return Longitud del arreglo de temperaturas.
     */
    public int getNumeroDias() {
        return temperaturas.length;
    }

    /**
     * Registra la temperatura de un dia especifico en grados Celsius.
     * Solo modifica el arreglo si el indice es valido y la temperatura esta
     * dentro del rango fisico ambiental [-90.0, 60.0].
     *
     * @param indiceDia Posicion del dia a registrar (desde 0 hasta N - 1).
     * @param temp Temperatura ambiental registrada en grados Celsius.
     * @return true si el dato fue valido y se almaceno; false en caso contrario.
     */
    public boolean registrarTemperatura(int indiceDia, double temp) {

        // Comprueba primero que el indice pertenezca al arreglo.
        if (indiceDia < 0 || indiceDia >= temperaturas.length) {
            return false;
        }

        // Rechaza temperaturas fuera del rango y valores NaN.
        if (temp < TEMPERATURA_MINIMA || temp > TEMPERATURA_MAXIMA
                || Double.isNaN(temp)) {
            return false;
        }

        temperaturas[indiceDia] = temp;
        return true;
    }

    public double obtenerTemperatura(int indiceDia) {
        // POR HACER (1.5): Devuelve la lectura en indiceDia, o -300.0 si el indice es invalido.
        if (indiceDia < 0 || indiceDia >= temperaturas.length) {
            return SIN_LECTURA;
        }
        return temperaturas[indiceDia];
    }

    // METODOS ESTADISTICOS (PARTE 2)

    /**
     * Calcula el promedio aritmetico de las temperaturas del periodo.
     * Este metodo supone que todos los dias ya tienen una lectura.
     *
     * @return Promedio de las temperaturas en grados Celsius.
     */
    public double calcularPromedio() {
        double suma = 0.0;

        // Acumula las temperaturas de todos los dias.
        for (int i = 0; i < temperaturas.length; i++) {
            suma += temperaturas[i];
        }

        return suma / temperaturas.length;
    }

    /**
     * Encuentra la temperatura maxima del periodo.
     * Este metodo supone que todos los dias ya tienen una lectura.
     *
     * @return Temperatura maxima en grados Celsius.
     */
    public double obtenerMaxima() {
        // Se usa la primera lectura para contemplar periodos solo negativos.
        double maxima = temperaturas[0];

        for (int i = 1; i < temperaturas.length; i++) {
            if (temperaturas[i] > maxima) {
                maxima = temperaturas[i];
            }
        }

        return maxima;
    }

    /**
     * Cuenta los dias cuya temperatura supera estrictamente un umbral.
     *
     * @param umbral Temperatura limite en grados Celsius.
     * @return Cantidad de dias que superan el umbral.
     */
    public int contarDiasCalurosos(double umbral) {
        int contador = 0;

        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] > umbral) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Construye un resumen estadistico de la estacion.
     *
     * @return Nombre, cantidad de dias, promedio y maxima.
     */
    @Override
    public String toString() {
        return "Estacion: " + nombreEstacion
                + " | Dias: " + temperaturas.length
                + " | Promedio: " + calcularPromedio()
                + " C | Maxima: " + obtenerMaxima() + " C";
    }

    // CONVERSION DE ESCALAS (PARTE 3)

    /**
     * Convierte una temperatura de Celsius a Fahrenheit.
     *
     * @param celsius Temperatura en grados Celsius.
     * @return Temperatura equivalente en grados Fahrenheit.
     */
    public double convertirAFahrenheit(double celsius) {
        return celsius * 1.8 + 32.0;
    }

     /**
     * Calcula el promedio del periodo en la escala solicitada.
     *
     * @param escala Escala de salida; si es FAHRENHEIT convierte el promedio,
     *               en cualquier otro caso devuelve Celsius.
     * @return Promedio en la escala solicitada.
     */
    public double calcularPromedio(Escala escala) {
        double promedio = calcularPromedio();

        if (escala == Escala.FAHRENHEIT) {
            return convertirAFahrenheit(promedio);
        }

        return promedio;
    }

    // RETOS (PARTE 4)

        /**
     * Busca el primer dia cuya temperatura sea igual o inferior a cero.
     *
     * @return Indice de la primera helada o -1 si no existe.
     */
    public int buscarPrimerDiaHelada() {
        // El recorrido se detiene al encontrar la primera temperatura <= 0.
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] <= 0.0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Calcula la mayor diferencia absoluta entre dias consecutivos.
     *
     * @return Mayor cambio en grados Celsius; 0.0 si hay menos de dos dias.
     */
    public double mayorCambioConsecutivo() {
        double mayorCambio = 0.0;

        // Cada iteracion compara el dia actual con el siguiente.
        for (int i = 0; i < temperaturas.length - 1; i++) {
            double cambio = Math.abs(temperaturas[i + 1] - temperaturas[i]);

            if (cambio > mayorCambio) {
                mayorCambio = cambio;
            }
        }

        return mayorCambio;
    }
}
