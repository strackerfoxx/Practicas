# Practica 10 - Estacion Meteorologica

## Datos


## De que trata

En esta practica hice una estacion meteorologica que guarda las temperaturas de varios dias usando un arreglo `double[]`

La clase permite registrar y consultar temperaturas, sacar el promedio, encontrar la maxima y contar los dias calurosos
Tambien agregue la conversion a Fahrenheit y los dos retos de la practica: encontrar la primera helada y calcular el mayor cambio entre dias seguidos

## Como ejecutar

Desde la carpeta principal del proyecto use estos comandos

```bash
javac -d bin src/*.java
java -cp bin PruebaEstacionMeteorologica
javadoc -d doc -author -version src/*.java
```

## Pruebas

Ejecute las pruebas y todas pasaron correctamente

```text
================================================================================
  ICC 2027-1 | VERIFICACION AUTOMATIZADA: ESTACION METEOROLOGICA (MINI-LAB 10)
================================================================================

>>> 1. PROBANDO CONSTRUCTORES, INICIALIZACION Y ACCESO SEGURO:
  [OK] Constructor por omision asigna "Estacion UNAM" y 7 dias
  [OK] Constructor por omision inicializa las casillas en -300.0 (sin lectura)
  [OK] Constructor con parametros valida nombre null y numDias <= 0 (usa 7)
  [OK] registrarTemperatura guarda valor valido en [0] y mantiene sin captura [5] en -300.0
  [OK] registrarTemperatura rechaza indices fuera de rango (-1 y 10)
  [OK] registrarTemperatura rechaza lecturas fuera de [-90.0, 60.0]
  [OK] obtenerTemperatura devuelve -300.0 en indices fuera de rango (-1 y 10)

>>> 2. PROBANDO ESTADISTICAS BASICAS Y toString():
  [OK] obtenerTemperatura(3) coincide con el ejemplo de la tabla (23.0)
  [OK] calcularPromedio de {18.5, 21.0, 19.5, 23.0, 17.0} es 19.8
  [OK] obtenerMaxima de {18.5, 21.0, 19.5, 23.0, 17.0} es 23.0
  [OK] obtenerMaxima funciona cuando TODAS las lecturas son negativas (-3.2)
  [OK] contarDiasCalurosos(> 20.0) en CU-UNAM devuelve 2
  [OK] contarDiasCalurosos(> 30.0) devuelve 0 cuando ninguno supera el umbral
  [OK] toString() devuelve el resumen estadistico exacto

>>> 3. PROBANDO CONVERSION A FAHRENHEIT Y ESCALAS:
  [OK] convertirAFahrenheit(0.0) devuelve 32.0 y convertirAFahrenheit(20.0) devuelve 68.0
  [OK] calcularPromedio(Escala.CELSIUS) devuelve 19.8
  [OK] calcularPromedio(Escala.FAHRENHEIT) devuelve 67.64

>>> 4. PROBANDO RETOS (HELADA Y VARIACION CONSECUTIVA):
  [OK] buscarPrimerDiaHelada en {12.0, 4.5, 0.0, -2.5, 3.0} devuelve indice 2
  [OK] buscarPrimerDiaHelada devuelve -1 si no hubo temperaturas <= 0.0
  [OK] mayorCambioConsecutivo en {18.0, 20.0, 11.5, 15.0, 22.0} es 8.5
  [OK] mayorCambioConsecutivo detecta el mayor cambio en el ultimo par (13.0)
  [OK] mayorCambioConsecutivo con 1 solo dia devuelve 0.0 sin excepcion

================================================================================
  RESUMEN DE PRUEBAS: 22 / 22 pruebas superadas con exito.
================================================================================

>>> 5. PRUEBA PERSONALIZADA:
Estacion: Munoz | Dias: 5 | Promedio: 15.2 C | Maxima: 23.0 C
Promedio en Celsius: 15.2 C
Promedio en Fahrenheit: 59.36 F
```

## Preguntas de reflexion

**1. El metodo `convertirAFahrenheit(double celsius)` es de instancia o static? Por que?**

Lo hice como metodo de instancia para mantener la estructura de la clase que viene en la plantilla
Aunque no necesita los datos de una estacion tambien podria ser `static`, porque solo recibe una temperatura y hace una conversion

**2. Por que no conviene iniciar la variable del maximo en `0.0`?**

Porque puede haber estaciones donde todas las temperaturas sean negativas
Si empiezo en cero el programa devolveria cero aunque esa temperatura no exista en el arreglo
Por eso inicie la variable con la primera temperatura y despues compare las demas

**3. Como resolviste uno de los retos o que pasa si calculas el promedio con dias sin lectura?**

En `buscarPrimerDiaHelada()` recorro el arreglo desde el primer dia y cuando encuentro una temperatura menor o igual a cero regreso ese indice
Si termino de recorrerlo y no encuentro ninguna helada regreso `-1`

Los dias que todavia no tienen lectura guardan `-300.0`, asi que si se calcula el promedio antes de registrar todos los dias ese valor afecta el resultado y lo vuelve incorrecto
Una mejora seria contar solo los dias que ya tienen una temperatura registrada

## Comentario final

Lo que mas tuve que cuidar fueron los indices del arreglo para no salir de sus limites
Tambien me quedo mas claro por que conviene iniciar la temperatura maxima con un valor que ya exista en el arreglo y no con cero
