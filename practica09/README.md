# Practica 9: Agregacion, Clonacion de Objetos y Guias de Envio
**Introduccion a las Ciencias de la Computacion (Semestre 2027-1)**  
**Facultad de Ciencias, UNAM**

## Descripcion y decisiones de diseno

La clase `Paquete` contiene los datos fisicos y comerciales de la mercancia. Su constructor por copia recibe otro paquete y copia sus atributos; cuando recibe `null`, asigna los valores predeterminados solicitados. Su metodo `toString()` muestra el codigo, peso fisico, peso volumetrico, peso cobrable, dimensiones, valor declarado y modalidad express.

La clase `Persona` utiliza tres atributos privados: `identificador`, `nombreCompleto` y `ubicacion`. Tiene constructor por omision y constructor con parametros. Las cadenas nulas o vacias se sustituyen por valores predeterminados. `mismaUbicacionQue` compara la ubicacion sin distinguir mayusculas y minusculas, mientras que `esLaMismaPersona` compara los identificadores.

La clase `Envio` agrega referencias a un remitente, un destinatario y un paquete. El contador `totalGuiasEmitidas` es `static`, por lo que pertenece a la clase y se comparte entre todas las guias. Cada constructor genera un folio nuevo. El constructor de clonacion conserva las referencias de las personas de la guia base y asocia un paquete nuevo. La guia de retorno invierte las personas y crea una copia independiente del paquete con el prefijo `RET-`.

## Comandos para compilar, ejecutar y documentar

Ejecuta desde la raiz de `practica09/`:

### 1. Compilacion
```bash
javac -d bin src/*.java
```

### 2. Pruebas automatizadas
```bash
java -cp bin PruebaEnvio
```

### 3. Menu interactivo
```bash
java -cp bin MenuPostal
```

### 4. Javadoc
```bash
javadoc -d doc -author -version src/*.java
```

## Evidencia de ejecucion

### 1. Salida de `PruebaEnvio`
Ejecuta el comando indicado y pega aqui la salida real de tu terminal. No uses una salida estimada: el resultado puede variar en los caracteres aleatorios de los folios.

```text
================================================================================
  ICC 2027-1 | PRACTICA 9: AGREGACION, CLONACION Y GUIAS DE ENVIO
================================================================================

>>> PARTE 1: REPRESENTACION TEXTUAL Y CLONACION DE PAQUETE
  [OK]    Paquete.toString() incluye el codigo de rastreo
  [OK]    Constructor por copia crea una instancia independiente
  [OK]    Modificar el clon no altera el paquete original
  [OK]    Constructor por copia con null asigna valores predeterminados

>>> PARTE 2: VERIFICACION DE LA CLASE PERSONA
  [OK]    Dos personas de la misma ubicacion
  [OK]    Personas de ubicaciones distintas
  [OK]    La ubicacion rechaza una referencia null
  [OK]    El mismo identificador representa a la misma persona
  [OK]    Identificadores distintos representan personas distintas
  [OK]    La identidad rechaza una referencia null
  [OK]    Persona.toString() contiene nombre, ID y ubicacion

>>> PARTE 3: VERIFICACION DE ENVIO Y FOLIOS AUTOMATICOS
  [OK]    Al iniciar no se han emitido guias
  [OK]    Los folios se generan y son distintos
  [OK]    Los folios tienen consecutivos 1 y 2
  [OK]    El contador global registra dos guias
  [OK]    La primera guia es local
  [OK]    La segunda guia es foranea
  [OK]    El autoenvio se identifica por el ID
  [OK]    El costo local no incluye cargo foraneo
  [OK]    El costo foraneo incluye el cargo adicional
  [OK]    Cambiar destinatario rechaza null
  [OK]    Cambiar destinatario acepta una persona

>>> PARTE 4: SIMULACION DE ENVIOS, CLONACION DE GUIA Y RETORNO
a) Persona X envia el kit promocional a Persona Y:
========== GUIA DE ENVIO ==========
Folio: GUIA-9EA-4
Tipo de ruta: LOCAL
Autoenvio: No

REMITENTE
Persona: Persona X [PER-01]
  Ubicacion: Ciudad de Mexico

DESTINATARIO
Persona: Persona Y [PER-02]
  Ubicacion: Ciudad de Mexico

MERCANCIA
Paquete: KIT-101
  Peso fisico: 4.0 kg
  Peso volumetrico: 1.6 kg
  Peso cobrable: 4.0 kg
  Dimensiones (alto x ancho x largo): 20.0 x 20.0 x 20.0 cm
  Valor declarado: $500.0 MXN
  Envio express: No

Costo total: $120.0 MXN
===================================

  [OK]    Clonar guia conserva remitente y destinatario
  [OK]    Clonar guia asocia el nuevo paquete
  [OK]    Clonar guia genera un folio diferente
b) Segundo paquete enviado mediante clonacion de guia:
========== GUIA DE ENVIO ==========
Folio: GUIA-F46-5
Tipo de ruta: LOCAL
Autoenvio: No

REMITENTE
Persona: Persona X [PER-01]
  Ubicacion: Ciudad de Mexico

DESTINATARIO
Persona: Persona Y [PER-02]
  Ubicacion: Ciudad de Mexico

MERCANCIA
Paquete: PKG-202
  Peso fisico: 12.0 kg
  Peso volumetrico: 7.2 kg
  Peso cobrable: 12.0 kg
  Dimensiones (alto x ancho x largo): 40.0 x 30.0 x 30.0 cm
  Valor declarado: $1500.0 MXN
  Envio express: No

Costo total: $246.0 MXN
===================================

  [OK]    El tercer envio usa una copia del kit y llega a otra ubicacion
c) Persona X envia una copia del kit a Persona Z:
========== GUIA DE ENVIO ==========
Folio: GUIA-068-6
Tipo de ruta: FORANEO
Autoenvio: No

REMITENTE
Persona: Persona X [PER-01]
  Ubicacion: Ciudad de Mexico

DESTINATARIO
Persona: Persona Z [PER-03]
  Ubicacion: Puebla

MERCANCIA
Paquete: KIT-Z-103
  Peso fisico: 4.0 kg
  Peso volumetrico: 1.6 kg
  Peso cobrable: 4.0 kg
  Dimensiones (alto x ancho x largo): 20.0 x 20.0 x 20.0 cm
  Valor declarado: $500.0 MXN
  Envio express: No

Costo total: $200.0 MXN
===================================

  [OK]    El retorno invierte remitente y destinatario
  [OK]    El paquete de retorno es independiente
  [OK]    El codigo del paquete de retorno tiene prefijo RET-
  [OK]    La guia de retorno tiene un folio nuevo
d) Guia de retorno solicitada por Persona Y:
========== GUIA DE ENVIO ==========
Folio: GUIA-3E8-7
Tipo de ruta: LOCAL
Autoenvio: No

REMITENTE
Persona: Persona Y [PER-02]
  Ubicacion: Ciudad de Mexico

DESTINATARIO
Persona: Persona X [PER-01]
  Ubicacion: Ciudad de Mexico

MERCANCIA
Paquete: RET-KIT-101
  Peso fisico: 4.0 kg
  Peso volumetrico: 1.6 kg
  Peso cobrable: 4.0 kg
  Dimensiones (alto x ancho x largo): 20.0 x 20.0 x 20.0 cm
  Valor declarado: $500.0 MXN
  Envio express: No

Costo total: $120.0 MXN
===================================

Total acumulado de guias emitidas: 7

================================================================================
  RESUMEN DE PRUEBAS: 30 / 30 pruebas superadas con exito
================================================================================
```

### 2. Ejemplo de `MenuPostal`
Ejecuta `MenuPostal`, emite una guia, consulta su detalle y sal del programa. Pega aqui la interaccion real de tu terminal.

```text
================================================================
  SISTEMA INTERACTIVO DE GUIAS POSTALES | ICC 2027-1
================================================================

--- MENU PRINCIPAL ---
1. Emitir nueva guia de envio
2. Clonar guia activa con un nuevo paquete
3. Clonar paquete de la guia activa para otro destinatario
4. Generar guia de retorno
5. Mostrar detalle y costo de la guia activa
0. Salir
Seleccione una opcion: 
--- Captura del REMITENTE ---
Identificador (ej. PER-01): Nombre completo ficticio: Ciudad o codigo postal: 
--- Captura del DESTINATARIO ---
Identificador (ej. PER-01): Nombre completo ficticio: Ciudad o codigo postal: 
--- Captura de datos del Paquete ---
Codigo de rastreo: Peso fisico en kg: Alto en cm: Ancho en cm: Largo en cm: Valor declarado en MXN: Es envio express? (s/n): 
Guia emitida con exito:
========== GUIA DE ENVIO ==========
Folio: GUIA-EBF-1
Tipo de ruta: FORANEO
Autoenvio: No

REMITENTE
Persona: Persona Remitente [PER-10]
  Ubicacion: Ciudad de Mexico

DESTINATARIO
Persona: Persona Destinataria [PER-11]
  Ubicacion: Puebla

MERCANCIA
Paquete: PKG-900
  Peso fisico: 2.0 kg
  Peso volumetrico: 0.2 kg
  Peso cobrable: 2.0 kg
  Dimensiones (alto x ancho x largo): 10.0 x 10.0 x 10.0 cm
  Valor declarado: $500.0 MXN
  Envio express: No

Costo total: $200.0 MXN
===================================


--- MENU PRINCIPAL ---
1. Emitir nueva guia de envio
2. Clonar guia activa con un nuevo paquete
3. Clonar paquete de la guia activa para otro destinatario
4. Generar guia de retorno
5. Mostrar detalle y costo de la guia activa
0. Salir
Seleccione una opcion: ========== GUIA DE ENVIO ==========
Folio: GUIA-EBF-1
Tipo de ruta: FORANEO
Autoenvio: No

REMITENTE
Persona: Persona Remitente [PER-10]
  Ubicacion: Ciudad de Mexico

DESTINATARIO
Persona: Persona Destinataria [PER-11]
  Ubicacion: Puebla

MERCANCIA
Paquete: PKG-900
  Peso fisico: 2.0 kg
  Peso volumetrico: 0.2 kg
  Peso cobrable: 2.0 kg
  Dimensiones (alto x ancho x largo): 10.0 x 10.0 x 10.0 cm
  Valor declarado: $500.0 MXN
  Envio express: No

Costo total: $200.0 MXN
===================================

Costo calculado: $200.0 MXN

--- MENU PRINCIPAL ---
1. Emitir nueva guia de envio
2. Clonar guia activa con un nuevo paquete
3. Clonar paquete de la guia activa para otro destinatario
4. Generar guia de retorno
5. Mostrar detalle y costo de la guia activa
0. Salir
Seleccione una opcion: Saliendo del sistema postal...
```

## Preguntas de reflexion

1. **En que se diferencia clonar un `Paquete` (`new Paquete(kitBase)`) de clonar una guia de `Envio` (`new Envio(guiaBase, nuevoPaquete)`) respecto a que objetos se comparten y cuales se duplican en memoria?**

   Al clonar un `Paquete` se crea un objeto nuevo con los mismos valores de los atributos, por lo que modificar el clon no modifica el original. Al clonar una guia, se crea un nuevo objeto `Envio`, pero se conservan las mismas referencias al remitente y destinatario de la guia base. El paquete asociado es el que se recibe como `nuevoPaquete`; no se copia automaticamente dentro de ese constructor.

2. **Para generar el `folioGuia` con un numero consecutivo global y consultar cuantas guias se han emitido en total, que modificador tuviste que utilizar en ese atributo y en su metodo de consulta? Por que no funcionaria si el contador fuera un atributo normal de instancia?**

   Se utiliza `static` tanto en el contador como en el metodo de consulta. Asi existe un solo contador compartido por todos los objetos `Envio`. Si fuera un atributo de instancia, cada guia tendria su propio contador y no se podria mantener una numeracion global.

3. **Si una guia `envio1` se descarta (`envio1 = null;`), que ocurre en memoria con los objetos `Persona` (remitente/destinatario) y `Paquete` que estaban asociados a ella?**

   Asignar `null` a `envio1` elimina esa referencia a la guia, pero no destruye inmediatamente los objetos asociados. Las personas pueden seguir referenciadas por otras guias o variables. El paquete tambien puede seguir existiendo si otra variable lo referencia; si un objeto ya no tiene ninguna referencia alcanzable, queda disponible para que el recolector de basura lo libere.

## Antes de entregar

- Sustituye los marcadores de evidencia por la salida real de tus ejecuciones.
- Actualiza tus datos de entrega en la plataforma; no publiques informacion personal en un repositorio publico.
- Genera Javadoc y conserva la carpeta `doc/` si forma parte de tu entrega.
- Elimina los archivos `.class` de `bin/` antes de comprimir el proyecto.
- Nombra el ZIP segun el formato solicitado por tu profesor: `Practica09_NumCuenta_PrimerApellido_Nombre.zip`.
