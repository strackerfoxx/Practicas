
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
RESUMEN DE PRUEBAS: 21 / 21 pruebas superadas con exito.
¡FELICIDADES! Todos los metodos de la clase Punto funcionan correctamente.
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
