# Práctica 7: Introducción a la Programación Orientada a Objetos
**Introducción a las Ciencias de la Computación (Semestre 2027-1)**  
**Facultad de Ciencias, UNAM**

---

## Descripción del proyecto
[Escribe aquí con tus propias palabras una breve descripción del problema y de cómo organizaste la solución mediante las clases solicitadas].

    El proyecto consiste en desarrollar un sistema básico de paquetería que permite representar paquetes, calcular su peso volumétrico, determinar su peso cobrable y cotizar el costo de envío de acuerdo con diferentes tarifas.

    La solución se organizó mediante tres clases principales. La clase Paquete representa cada envío y contiene sus atributos, constructores, métodos de acceso y operaciones para calcular sus pesos. La clase CotizadorEnvio contiene métodos estáticos para validar paquetes, calcular el costo del envío y determinar si requieren seguro. Finalmente, la clase PruebaPaqueteria permite comprobar el funcionamiento de las clases mediante diferentes casos de prueba.

    Como propuesta personal se agregó la modalidad de envío express, que aplica un recargo del 30% sobre el costo normal del envío.

---

## Comandos para compilar, ejecutar y documentar

Escribe en las siguientes secciones los comandos que utilizaste desde la terminal (ubicado en la raíz de tu carpeta de trabajo):

### 1. Compilación
```bash
# Escribe aquí el comando para compilar todas las clases hacia la carpeta bin/
    javac -d bin src/*.java
```

### 2. Ejecución
```bash
# Escribe aquí el comando para ejecutar el programa de prueba
    java -cp bin PruebaPaqueteria
```

### 3. Generación de documentación Javadoc
```bash
# Escribe aquí el comando para generar la documentación en la carpeta doc/
    javadoc -d doc src/*.java
```

