/**
 * Pruebas automatizadas para verificar la clase EstacionMeteorologica
 * en la Mini-Practica 10 (Arreglos de Tipos Primitivos).
 *
 * @author Alexis Eduardo Muñoz Trujillo
 * @version 1.1
 */
public class PruebaEstacionMeteorologica {

    private static int pruebasExitosas = 0;
    private static int totalPruebas = 0;

    /**
     * Constructor privado para evitar instanciar la clase de pruebas.
     */
    private PruebaEstacionMeteorologica() {
    }

    /**
     * Ejecuta las pruebas automatizadas y una prueba personalizada.
     *
     * @param args Argumentos recibidos desde la terminal.
     */
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  ICC 2027-1 | VERIFICACION AUTOMATIZADA: ESTACION METEOROLOGICA (MINI-LAB 10)");
        System.out.println("================================================================================\n");

        // ---------------------------------------------------------------------
        // 1. Constructores, Inicializacion en -300.0 y Consultas Basicas
        // ---------------------------------------------------------------------
        System.out.println(">>> 1. PROBANDO CONSTRUCTORES, INICIALIZACION Y ACCESO SEGURO:");
        EstacionMeteorologica estOmision = new EstacionMeteorologica();
        verificar("Constructor por omision asigna \"Estacion UNAM\" y 7 dias",
                  "Estacion UNAM".equals(estOmision.getNombreEstacion()) && estOmision.getNumeroDias() == 7,
                  "Nombre: '" + estOmision.getNombreEstacion() + "', Dias: " + estOmision.getNumeroDias());

        verificar("Constructor por omision inicializa las casillas en -300.0 (sin lectura)",
                  Math.abs(estOmision.obtenerTemperatura(0) - (-300.0)) < 1e-9
                  && Math.abs(estOmision.obtenerTemperatura(6) - (-300.0)) < 1e-9,
                  "Obtenido en [0]: " + estOmision.obtenerTemperatura(0));

        EstacionMeteorologica estInvalida = new EstacionMeteorologica(null, -4);
        verificar("Constructor con parametros valida nombre null y numDias <= 0 (usa 7)",
                  "Estacion UNAM".equals(estInvalida.getNombreEstacion()) && estInvalida.getNumeroDias() == 7,
                  "Nombre: '" + estInvalida.getNombreEstacion() + "', Dias: " + estInvalida.getNumeroDias());

        EstacionMeteorologica estParcial = new EstacionMeteorologica("Ajusco", 10);
        boolean ok0 = estParcial.registrarTemperatura(0, 14.5);
        verificar("registrarTemperatura guarda valor valido en [0] y mantiene sin captura [5] en -300.0",
                  ok0
                  && Math.abs(estParcial.obtenerTemperatura(0) - 14.5) < 1e-9
                  && Math.abs(estParcial.obtenerTemperatura(5) - (-300.0)) < 1e-9,
                  "Obtenido [0]=" + estParcial.obtenerTemperatura(0) + ", [5]=" + estParcial.obtenerTemperatura(5));

        verificar("registrarTemperatura rechaza indices fuera de rango (-1 y 10)",
                  !estParcial.registrarTemperatura(-1, 20.0) && !estParcial.registrarTemperatura(10, 20.0),
                  "Se acepto un indice invalido");

        verificar("registrarTemperatura rechaza lecturas fuera de [-90.0, 60.0]",
                  !estParcial.registrarTemperatura(1, -95.0) && !estParcial.registrarTemperatura(1, 65.0),
                  "Se acepto una temperatura fisicamente invalida");

        verificar("obtenerTemperatura devuelve -300.0 en indices fuera de rango (-1 y 10)",
                  Math.abs(estParcial.obtenerTemperatura(-1) - (-300.0)) < 1e-9
                  && Math.abs(estParcial.obtenerTemperatura(10) - (-300.0)) < 1e-9,
                  "Obtenido en [10]: " + estParcial.obtenerTemperatura(10));

        // ---------------------------------------------------------------------
        // 2. Reportes Estadisticos y toString (Datos de la tabla del enunciado)
        // ---------------------------------------------------------------------
        System.out.println("\n>>> 2. PROBANDO ESTADISTICAS BASICAS Y toString():");
        EstacionMeteorologica estCU = new EstacionMeteorologica("CU-UNAM", 5);
        estCU.registrarTemperatura(0, 18.5);
        estCU.registrarTemperatura(1, 21.0);
        estCU.registrarTemperatura(2, 19.5);
        estCU.registrarTemperatura(3, 23.0);
        estCU.registrarTemperatura(4, 17.0);

        verificar("obtenerTemperatura(3) coincide con el ejemplo de la tabla (23.0)",
                  Math.abs(estCU.obtenerTemperatura(3) - 23.0) < 1e-9,
                  "Esperado: 23.0, Obtenido: " + estCU.obtenerTemperatura(3));

        verificar("calcularPromedio de {18.5, 21.0, 19.5, 23.0, 17.0} es 19.8",
                  Math.abs(estCU.calcularPromedio() - 19.8) < 1e-9,
                  "Esperado: 19.8, Obtenido: " + estCU.calcularPromedio());

        verificar("obtenerMaxima de {18.5, 21.0, 19.5, 23.0, 17.0} es 23.0",
                  Math.abs(estCU.obtenerMaxima() - 23.0) < 1e-9,
                  "Esperado: 23.0, Obtenido: " + estCU.obtenerMaxima());

        EstacionMeteorologica estMontana = new EstacionMeteorologica("Nevado", 4);
        estMontana.registrarTemperatura(0, -8.5);
        estMontana.registrarTemperatura(1, -3.2);
        estMontana.registrarTemperatura(2, -12.0);
        estMontana.registrarTemperatura(3, -4.0);
        verificar("obtenerMaxima funciona cuando TODAS las lecturas son negativas (-3.2)",
                  Math.abs(estMontana.obtenerMaxima() - (-3.2)) < 1e-9,
                  "Esperado: -3.2, Obtenido: " + estMontana.obtenerMaxima());

        verificar("contarDiasCalurosos(> 20.0) en CU-UNAM devuelve 2",
                  estCU.contarDiasCalurosos(20.0) == 2,
                  "Esperado: 2, Obtenido: " + estCU.contarDiasCalurosos(20.0));

        verificar("contarDiasCalurosos(> 30.0) devuelve 0 cuando ninguno supera el umbral",
                  estCU.contarDiasCalurosos(30.0) == 0,
                  "Esperado: 0, Obtenido: " + estCU.contarDiasCalurosos(30.0));

        String esperadoStr = "Estacion: CU-UNAM | Dias: 5 | Promedio: 19.8 C | Maxima: 23.0 C";
        verificar("toString() devuelve el resumen estadistico exacto",
                  esperadoStr.equals(estCU.toString()),
                  "Esperado: \"" + esperadoStr + "\", Obtenido: \"" + estCU.toString() + "\"");

        // ---------------------------------------------------------------------
        // 3. Conversion de Escalas y Enumeracion Escala
        // ---------------------------------------------------------------------
        System.out.println("\n>>> 3. PROBANDO CONVERSION A FAHRENHEIT Y ESCALAS:");
        verificar("convertirAFahrenheit(0.0) devuelve 32.0 y convertirAFahrenheit(20.0) devuelve 68.0",
                  Math.abs(estCU.convertirAFahrenheit(0.0) - 32.0) < 1e-9
                  && Math.abs(estCU.convertirAFahrenheit(20.0) - 68.0) < 1e-9,
                  "Obtenido para 20.0 C: " + estCU.convertirAFahrenheit(20.0));

        verificar("calcularPromedio(Escala.CELSIUS) devuelve 19.8",
                  Math.abs(estCU.calcularPromedio(Escala.CELSIUS) - 19.8) < 1e-9,
                  "Obtenido: " + estCU.calcularPromedio(Escala.CELSIUS));

        verificar("calcularPromedio(Escala.FAHRENHEIT) devuelve 67.64",
                  Math.abs(estCU.calcularPromedio(Escala.FAHRENHEIT) - 67.64) < 1e-9,
                  "Esperado: 67.64, Obtenido: " + estCU.calcularPromedio(Escala.FAHRENHEIT));

        // ---------------------------------------------------------------------
        // 4. Retos (Primera Helada y Mayor Cambio Consecutivo)
        // ---------------------------------------------------------------------
        System.out.println("\n>>> 4. PROBANDO RETOS (HELADA Y VARIACION CONSECUTIVA):");
        EstacionMeteorologica estHelada = new EstacionMeteorologica("Toluca", 5);
        estHelada.registrarTemperatura(0, 12.0);
        estHelada.registrarTemperatura(1, 4.5);
        estHelada.registrarTemperatura(2, 0.0);
        estHelada.registrarTemperatura(3, -2.5);
        estHelada.registrarTemperatura(4, 3.0);

        verificar("buscarPrimerDiaHelada en {12.0, 4.5, 0.0, -2.5, 3.0} devuelve indice 2",
                  estHelada.buscarPrimerDiaHelada() == 2,
                  "Esperado: 2, Obtenido: " + estHelada.buscarPrimerDiaHelada());

        verificar("buscarPrimerDiaHelada devuelve -1 si no hubo temperaturas <= 0.0",
                  estCU.buscarPrimerDiaHelada() == -1,
                  "Esperado: -1, Obtenido: " + estCU.buscarPrimerDiaHelada());

        EstacionMeteorologica estSaltos = new EstacionMeteorologica("Saltos", 5);
        estSaltos.registrarTemperatura(0, 18.0);
        estSaltos.registrarTemperatura(1, 20.0);
        estSaltos.registrarTemperatura(2, 11.5);
        estSaltos.registrarTemperatura(3, 15.0);
        estSaltos.registrarTemperatura(4, 22.0);
        verificar("mayorCambioConsecutivo en {18.0, 20.0, 11.5, 15.0, 22.0} es 8.5",
                  Math.abs(estSaltos.mayorCambioConsecutivo() - 8.5) < 1e-9,
                  "Esperado: 8.5, Obtenido: " + estSaltos.mayorCambioConsecutivo());

        EstacionMeteorologica estUltimo = new EstacionMeteorologica("Ultimo", 3);
        estUltimo.registrarTemperatura(0, 10.0);
        estUltimo.registrarTemperatura(1, 12.0);
        estUltimo.registrarTemperatura(2, 25.0);
        verificar("mayorCambioConsecutivo detecta el mayor cambio en el ultimo par (13.0)",
                  Math.abs(estUltimo.mayorCambioConsecutivo() - 13.0) < 1e-9,
                  "Esperado: 13.0, Obtenido: " + estUltimo.mayorCambioConsecutivo());

        EstacionMeteorologica estUnDia = new EstacionMeteorologica("UnDia", 1);
        estUnDia.registrarTemperatura(0, 24.0);
        verificar("mayorCambioConsecutivo con 1 solo dia devuelve 0.0 sin excepcion",
                  Math.abs(estUnDia.mayorCambioConsecutivo() - 0.0) < 1e-9,
                  "Obtenido: " + estUnDia.mayorCambioConsecutivo());

        // ---------------------------------------------------------------------
        // Resumen de pruebas automatizadas
        // ---------------------------------------------------------------------
        System.out.println("\n================================================================================");
        System.out.println("  RESUMEN DE PRUEBAS: " + pruebasExitosas + " / " + totalPruebas + " pruebas superadas con exito.");
        System.out.println("================================================================================\n");

        // ---------------------------------------------------------------------
        // 5. Prueba Personalizada (Parte 5.2)
        // ---------------------------------------------------------------------
        System.out.println(">>> 5. PRUEBA PERSONALIZADA:");
        EstacionMeteorologica estMunoz = new EstacionMeteorologica("Munoz", 5);
        estMunoz.registrarTemperatura(0, 18.0);
        estMunoz.registrarTemperatura(1, -2.5);
        estMunoz.registrarTemperatura(2, 21.0);
        estMunoz.registrarTemperatura(3, 16.5);
        estMunoz.registrarTemperatura(4, 23.0);

        System.out.println(estMunoz.toString());
        System.out.println("Promedio en Celsius: " + estMunoz.calcularPromedio(Escala.CELSIUS) + " C");
        System.out.println("Promedio en Fahrenheit: " + estMunoz.calcularPromedio(Escala.FAHRENHEIT) + " F");
    }

    private static void verificar(String descripcion, boolean condicion, String detalle) {
        totalPruebas++;
        if (condicion) {
            pruebasExitosas++;
            System.out.printf("  [OK] %s%n", descripcion);
        } else {
            System.out.printf("  [FALLO] %s (%s)%n", descripcion, detalle);
        }
    }
}
