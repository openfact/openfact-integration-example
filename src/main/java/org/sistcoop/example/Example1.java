package org.sistcoop.example;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.openfact.representations.idm.OrganizationRepresentation;
import org.sistcoop.admin.client.OpenfactClient;

import java.util.List;

public class Example1 {

    public static void main(String args[]) {
        // Openfact Server Url
        String serverUrl = "http://openfact-openfact.192.168.42.244.nip.io";

        // Openfact Authentication
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://keycloak-openfact.192.168.42.244.nip.io/auth")
                .realm("myRealm")
                .username("myUsername")
                .password("myPassword")
                .clientId("openfact-client")
                .clientSecret("myClientSecret")
                .build();

        // Openfact Client
        OpenfactClient openfact = OpenfactClient.buildTarget(serverUrl, keycloak);

        // Listing all allowed organizations
        List<OrganizationRepresentation> organizations = openfact.organizations().findAll();
        for (OrganizationRepresentation organization : organizations) {
            System.out.println(organization.getOrganization());
        }
    }

}