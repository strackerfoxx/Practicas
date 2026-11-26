/**
 * Ejecuta pruebas automatizadas y una simulacion del sistema postal.
 *
 * @author Alexis Eduardo Munoz Trujillo
 * @version 1.1
 */
public class PruebaEnvio {
    private static int pruebasExitosas = 0;
    private static int totalPruebas = 0;

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("  ICC 2027-1 | PRACTICA 9: AGREGACION, CLONACION Y GUIAS DE ENVIO");
        System.out.println("================================================================================\n");

        System.out.println(">>> PARTE 1: REPRESENTACION TEXTUAL Y CLONACION DE PAQUETE");
        Paquete kitBase = new Paquete("KIT-101", 4.0, 20.0, 20.0, 20.0, 500.0, false);
        Paquete otroPaquete = new Paquete("PKG-202", 12.0, 40.0, 30.0, 30.0, 1500.0, false);
        String txtKit = kitBase.toString();
        verificar("Paquete.toString() incluye el codigo de rastreo", txtKit.contains("KIT-101") && !txtKit.contains("Paquete@"));
        Paquete kitClon = new Paquete(kitBase);
        double pesoOriginal = kitBase.getPeso();
        kitBase.setPeso(99.0);
        verificar("Constructor por copia crea una instancia independiente", kitClon != kitBase && Math.abs(kitClon.getPeso() - pesoOriginal) < 1e-6);
        kitBase.setPeso(pesoOriginal);
        kitClon.setCodigoRastreo("KIT-102");
        verificar("Modificar el clon no altera el paquete original", "KIT-101".equals(kitBase.getCodigoRastreo()) && "KIT-102".equals(kitClon.getCodigoRastreo()));
        Paquete clonNulo = new Paquete(null);
        verificar("Constructor por copia con null asigna valores predeterminados", "ND-000".equals(clonNulo.getCodigoRastreo()) && clonNulo.getPeso() == 1.0);
        System.out.println();

        System.out.println(">>> PARTE 2: VERIFICACION DE LA CLASE PERSONA");
        Persona personaX = new Persona("PER-01", "Persona X", "Ciudad de Mexico");
        Persona personaY = new Persona("PER-02", "Persona Y", "Ciudad de Mexico");
        Persona personaZ = new Persona("PER-03", "Persona Z", "Puebla");
        Persona copiaX = new Persona("PER-01", "Otra representacion de X", "Guadalajara");
        verificar("Dos personas de la misma ubicacion", personaX.mismaUbicacionQue(personaY));
        verificar("Personas de ubicaciones distintas", !personaX.mismaUbicacionQue(personaZ));
        verificar("La ubicacion rechaza una referencia null", !personaX.mismaUbicacionQue(null));
        verificar("El mismo identificador representa a la misma persona", personaX.esLaMismaPersona(copiaX));
        verificar("Identificadores distintos representan personas distintas", !personaX.esLaMismaPersona(personaY));
        verificar("La identidad rechaza una referencia null", !personaX.esLaMismaPersona(null));
        verificar("Persona.toString() contiene nombre, ID y ubicacion", personaX.toString().contains("Persona X") && personaX.toString().contains("PER-01") && personaX.toString().contains("Ciudad de Mexico"));
        System.out.println();

        System.out.println(">>> PARTE 3: VERIFICACION DE ENVIO Y FOLIOS AUTOMATICOS");
        verificar("Al iniciar no se han emitido guias", Envio.getTotalGuiasEmitidas() == 0);
        Envio guiaLocal = new Envio(personaX, personaY, kitBase);
        Envio guiaForanea = new Envio(personaX, personaZ, kitClon);
        verificar("Los folios se generan y son distintos", guiaLocal.getFolioGuia() != null && guiaForanea.getFolioGuia() != null && !guiaLocal.getFolioGuia().equals(guiaForanea.getFolioGuia()));
        verificar("Los folios tienen consecutivos 1 y 2", guiaLocal.getFolioGuia().endsWith("-1") && guiaForanea.getFolioGuia().endsWith("-2"));
        verificar("El contador global registra dos guias", Envio.getTotalGuiasEmitidas() == 2);
        verificar("La primera guia es local", !guiaLocal.esEnvioForaneo());
        verificar("La segunda guia es foranea", guiaForanea.esEnvioForaneo());
        verificar("El autoenvio se identifica por el ID", new Envio(personaX, copiaX, kitBase).esAutoEnvio());
        verificar("El costo local no incluye cargo foraneo", Math.abs(guiaLocal.calcularCostoTotal() - CotizadorEnvio.cotizarEnvio(kitBase)) < 1e-6);
        verificar("El costo foraneo incluye el cargo adicional", Math.abs(guiaForanea.calcularCostoTotal() - CotizadorEnvio.cotizarEnvio(kitClon) - Envio.CARGO_FORANEO) < 1e-6);
        verificar("Cambiar destinatario rechaza null", !guiaLocal.cambiarDestinatario(null));
        verificar("Cambiar destinatario acepta una persona", guiaLocal.cambiarDestinatario(personaZ) && guiaLocal.getDestinatario() == personaZ);
        guiaLocal.cambiarDestinatario(personaY);
        System.out.println();

        System.out.println(">>> PARTE 4: SIMULACION DE ENVIOS, CLONACION DE GUIA Y RETORNO");
        Envio envio1 = new Envio(personaX, personaY, kitBase);
        System.out.println("a) Persona X envia el kit promocional a Persona Y:\n" + envio1);
        Envio envio2 = new Envio(envio1, otroPaquete);
        verificar("Clonar guia conserva remitente y destinatario", envio2.getRemitente() == envio1.getRemitente() && envio2.getDestinatario() == envio1.getDestinatario());
        verificar("Clonar guia asocia el nuevo paquete", envio2.getPaquete() == otroPaquete && envio2.getPaquete() != envio1.getPaquete());
        verificar("Clonar guia genera un folio diferente", !envio2.getFolioGuia().equals(envio1.getFolioGuia()));
        System.out.println("b) Segundo paquete enviado mediante clonacion de guia:\n" + envio2);
        Paquete kitParaZ = new Paquete(kitBase);
        kitParaZ.setCodigoRastreo("KIT-Z-103");
        Envio envio3 = new Envio(personaX, personaZ, kitParaZ);
        verificar("El tercer envio usa una copia del kit y llega a otra ubicacion", envio3.getPaquete() != kitBase && envio3.esEnvioForaneo());
        System.out.println("c) Persona X envia una copia del kit a Persona Z:\n" + envio3);
        Envio retorno = envio1.generarGuiaRetorno();
        verificar("El retorno invierte remitente y destinatario", retorno.getRemitente() == personaY && retorno.getDestinatario() == personaX);
        verificar("El paquete de retorno es independiente", retorno.getPaquete() != envio1.getPaquete());
        verificar("El codigo del paquete de retorno tiene prefijo RET-", retorno.getPaquete().getCodigoRastreo().startsWith("RET-"));
        verificar("La guia de retorno tiene un folio nuevo", !retorno.getFolioGuia().equals(envio1.getFolioGuia()));
        System.out.println("d) Guia de retorno solicitada por Persona Y:\n" + retorno);
        System.out.println("Total acumulado de guias emitidas: " + Envio.getTotalGuiasEmitidas());

        System.out.println("\n================================================================================");
        System.out.println("  RESUMEN DE PRUEBAS: " + pruebasExitosas + " / " + totalPruebas + " pruebas superadas con exito");
        System.out.println("================================================================================");
    }

    private static void verificar(String nombrePrueba, boolean condicion) {
        totalPruebas++;
        if (condicion) {
            pruebasExitosas++;
            System.out.println("  [OK]    " + nombrePrueba);
        } else {
            System.out.println("  [FALLO] " + nombrePrueba);
        }
    }
}
