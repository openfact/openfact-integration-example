package org.sistcoop.example;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.openfact.representations.idm.OrganizationRepresentation;

import java.util.List;

public class Example {

    public static void main(String args[]) {
        String serverUrl = "http://openfact-openfact.192.168.42.121.nip.io";

        Keycloak keycloak = KeycloakBuilder
                .builder()
                .serverUrl("http://keycloak-openfact.192.168.42.121.nip.io/auth")
                .realm("ahren")
                .username("carlos")
                .password("carlos")
                .clientId("openfact-web-console")
                .build();

        OpenfactClient openfact = OpenfactClient.buildTarget(serverUrl, keycloak);

        List<OrganizationRepresentation> organizations = openfact.organizations().findAll();
        for (OrganizationRepresentation organization : organizations) {
            System.out.println(organization);
        }
    }

}