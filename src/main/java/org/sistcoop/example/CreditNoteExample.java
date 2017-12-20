package org.sistcoop.example;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.openfact.models.ModelException;
import org.openfact.pe.representations.idm.DocumentRepresentation;
import org.openfact.pe.representations.idm.LineRepresentation;
import org.openfact.services.ModelErrorResponseException;
import org.sistcoop.admin.client.OpenfactClient;
import org.sistcoop.admin.client.resource.OrganizationSunatResource;
import org.sistcoop.admin.client.resource.SunatAdapter;

import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreditNoteExample {

    public static void main(String args[]) throws Exception {
        OpenfactClient client = getClient();
        OrganizationSunatResource organization = SunatAdapter.from(client).organization("xiomany");

        try {
            Response result = organization.createCreditNote(generateCreditNote());
            if (result.getStatus() != 201) {
                throw new Exception("Error al enviar documento a la sunat, los servicios de la sunat parecen no estar disponibles");
            }
            org.openfact.representations.idm.DocumentRepresentation document = result.readEntity(org.openfact.representations.idm.DocumentRepresentation.class);


            System.out.println("Felicitaciones, creaste una nota de credito");
        } catch (ModelErrorResponseException e) {
            System.out.println("Error al enviar documentos a la sunat");
        } catch (ModelException e) {
            System.out.println("Error al crear el documento, verifique que el documento no haya sido creado previamente");
        }
    }

    public static OpenfactClient getClient() {
        // Openfact Server Url
        String serverUrl = "http://openfact.com";

        // Openfact Authentication
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://keycloak/auth")
                .realm("myRealm")
                .username("admin")
                .password("admin")
                .clientId("openfact-client")
                .clientSecret("9df24011-daef-4c8f-8282-f82d101c90ff")
                .build();

        // Openfact Client
        return OpenfactClient.buildTarget(serverUrl, keycloak);
    }

    public static DocumentRepresentation generateCreditNote() {
        DocumentRepresentation notaCredito = new DocumentRepresentation();

        notaCredito.setDocumentoQueSeModifica("F001-00000056");
        notaCredito.setTipoDeNotaDeCredito("01");

        notaCredito.setEntidadDenominacion("Juan Perez"); // Nombre del cliente
        notaCredito.setEntidadNumeroDeDocumento("10254125878"); // Numero de documento del cliente (RUC/DNI)
        notaCredito.setEntidadTipoDeDocumento("6"); // Tipo de Documento del cliente (RUC/DNI)

        notaCredito.setEnviarAutomaticamenteASunat(false);
        notaCredito.setEnviarAutomaticamenteAlCliente(false);

        notaCredito.setIgv(new BigDecimal(18)); // IGV Aplicado a la operacion expresada en porcentaje 18%
        notaCredito.setMoneda("PEN"); // Moneda nacional
        notaCredito.setObservaciones("Mis observaciones");

        notaCredito.setOperacionGratuita(false); // Si la operacion es gratuita
        notaCredito.setTotalGratuita(BigDecimal.ZERO);

        notaCredito.setTotalGravada(new BigDecimal(120_100));
        notaCredito.setTotalExonerada(BigDecimal.ZERO);
        notaCredito.setTotalInafecta(BigDecimal.ZERO);

        notaCredito.setTotal(new BigDecimal(141_718));
        notaCredito.setTotalIgv(new BigDecimal(21_618));
        notaCredito.setTotalOtrosCargos(BigDecimal.ZERO); // Otros cargos aplicados
        notaCredito.setDescuentoGlobal(BigDecimal.ZERO); // Descuentos aplicados

        // Detalle de la factura
        List<LineRepresentation> lines = new ArrayList<>();
        LineRepresentation line1 = new LineRepresentation();
        LineRepresentation line2 = new LineRepresentation();
        lines.add(line1);
        lines.add(line2);

        line1.setCantidad(new BigDecimal(2));
        line1.setDescripcion("Carro Toyota ultimo modelo");
        line1.setTipoDeIgv("10"); // Codigo de operacion (Gravado - Operacion Onerosa)
        line1.setValorUnitario(new BigDecimal(60_000)); // Precio unitario sin igv
        line1.setIgv(new BigDecimal(21_600)); // Igv aplicado al item
        line1.setPrecioUnitario(new BigDecimal(70_800)); // Precio unitario con igv
        line1.setSubtotal(new BigDecimal(120_000)); // Subtotal sin igv
        line1.setTotal(new BigDecimal(141_600)); // Subtotal con igv

        line2.setCantidad(new BigDecimal(1));
        line2.setDescripcion("Llanta doble filo original");
        line2.setTipoDeIgv("10"); // Codigo de operacion (Gravado - Operacion Onerosa)
        line2.setValorUnitario(new BigDecimal(100)); // Precio unitario sin igv
        line2.setIgv(new BigDecimal(18)); // Igv aplicado al item
        line2.setPrecioUnitario(new BigDecimal(118)); // Precio unitario con igv
        line2.setSubtotal(new BigDecimal(100)); // Subtotal sin igv
        line2.setTotal(new BigDecimal(118)); // Subtotal con igv

        // Return result
        notaCredito.setDetalle(lines);
        return notaCredito;
    }

}
