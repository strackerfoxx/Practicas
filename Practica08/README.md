
# Practica 8 (Mini-Lab): La Clase Punto

**Introduccion a las Ciencias de la Computacion (Semestre 2027-1)**  
**Facultad de Ciencias, UNAM**

---

## Descripcion de la practica

En esta practica se implemento la clase `Punto`, que representa un punto en el plano cartesiano bidimensional mediante dos coordenadas de tipo double: x e y.

Se implementaron dos constructores, metodos de consulta y asignacion, y operaciones geometricas como desplazamiento, calculo de distancia, punto medio, determinacion de cuadrantes y comprobacion de colinealidad.

Tambien se sobrescribieron los metodos `toString()` y `equals()` para representar y comparar objetos de tipo Punto.

---

## Comandos para compilar, ejecutar y documentar

Ejecutar los siguientes comandos desde la raiz de esta carpeta.

### 1. Compilacion

```bash
javac -d bin src/*.java
```

### 2. Ejecucion de la verificacion

```bash
java -cp bin PruebaPunto
```

### 3. Generacion de documentacion Javadoc

```bash
javadoc -d doc -author -version src/Punto.java
```

---

## Evidencia de ejecucion

Pegar aqui la salida final de la terminal al ejecutar `PruebaPunto`.

```text
================================================================================
  ICC 2027-1 | VERIFICACION AUTOMATIZADA: CLASE PUNTO (MINI-LAB 8)
================================================================================

>>> 1. PROBANDO CONSTRUCTORES Y GETTERS:
  [OK] Constructor por omision inicializa en (0.0, 0.0)
  [OK] Constructor con parametros inicializa en (3.0, 4.0)

>>> 2. PROBANDO SETTERS:
  [OK] Setters modifican correctamente las coordenadas

>>> 3. PROBANDO METODO DESPLAZAR:
  [OK] desplazar(1.5, -1.0) actualiza a (3.5, 2.0)

>>> 4. PROBANDO DISTANCIA EUCLIDIANA:
  [OK] Distancia de (0.0, 0.0) a (3.0, 4.0) es 5.0
  [OK] Distancia de (1.0, 2.0) a (4.0, 6.0) es 5.0
  [OK] Distancia con referencia null regresa -1.0

>>> 5. PROBANDO PUNTO MEDIO (OBJETO RETORNADO):
  [OK] Punto medio entre (1.0, 2.0) y (4.0, 6.0) es (2.5, 4.0)
  [OK] Punto medio con null regresa null

>>> 6. PROBANDO DETERMINACION DE CUADRANTES:
  [OK] Cuadrante de (5.0, 8.0) es I
  [OK] Cuadrante de (-3.0, 4.0) es II
  [OK] Cuadrante de (-2.0, -7.0) es III
  [OK] Cuadrante de (6.0, -1.0) es IV
  [OK] Cuadrante de (0.0, 5.0) en eje es 0
  [OK] Cuadrante del origen (0.0, 0.0) es 0

>>> 7. PROBANDO METODO toString():
  [OK] toString() de (3.0, 4.0) devuelve "(3.0, 4.0)"

>>> 8. PROBANDO METODO EQUALS (IDENTIDAD VS CONTENIDO):
  [OK] p1.equals(new Punto(3.0, 4.0)) retorna true
  [OK] p1.equals(new Punto(3.0, 4.1)) retorna false
  [OK] p1.equals(null) retorna false de forma segura

>>> 9. PROBANDO COLINEALIDAD (RETO: TRES PUNTOS EN UNA RECTA):
  [OK] (1,1), (2,2) y (5,5) estan alineados
  [OK] (1,1), (2,2) y (5,6) NO estan alineados

================================================================================
  RESUMEN DE PRUEBAS: 21 / 21 pruebas superadas con exito.
  ¡FELICIDADES! Todos los metodos de la clase Punto funcionan correctamente.
================================================================================

```

---

## Preguntas de reflexion

### 1. ¿Por que el metodo `puntoMedio` crea y regresa un nuevo objeto `Punto`, mientras que `desplazar` modifica al mismo objeto que recibe el mensaje?

Porque los dos metodos tienen objetivos diferentes. `puntoMedio` calcula un resultado nuevo a partir de dos puntos, por lo que crea una nueva instancia con las coordenadas calculadas y no modifica los puntos originales. En cambio, `desplazar` realiza un cambio directamente sobre las coordenadas del objeto que recibe el mensaje.

### 2. Al ejecutar `p1.distancia(p2)`, ¿a que objeto en memoria hace referencia `this` y como accede el metodo a las coordenadas de `p2`?

`this` hace referencia al objeto `p1`, porque es el objeto que recibe el mensaje y ejecuta el metodo. Para acceder a las coordenadas de `p2`, el metodo utiliza el parametro `otroPunto` y sus getters `getX()` y `getY()`.

### 3. ¿Por que los atributos `pruebasExitosas`, `totalPruebas` y el metodo `verificar` estan declarados como `static`? ¿Que ocurriria si les quitaramos la palabra `static` y los llamaramos desde `main`? Menciona ademas dos metodos estaticos de las bibliotecas estandar de Java que utilizaste en `Punto.java`.

Estan declarados como `static` porque pertenecen a la clase y no a una instancia particular. De esta manera, el metodo `main`, que tambien es static, puede utilizarlos directamente sin crear un objeto de tipo `PruebaPunto`.

Si se quitara `static`, seria necesario crear una instancia de `PruebaPunto` para acceder a esos atributos y al metodo desde `main`.

Dos metodos estaticos utilizados en `Punto.java` son `Math.sqrt()`, que calcula la raiz cuadrada, y `Math.abs()`, que obtiene el valor absoluto de un numero.
