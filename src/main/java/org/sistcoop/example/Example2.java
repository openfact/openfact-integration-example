package org.sistcoop.example;

import org.openfact.representations.idm.OrganizationRepresentation;
import org.sistcoop.admin.client.OpenfactClient;

import java.util.List;

public class Example2 {

    public static void main(String args[]) {
        // This will search for keycloak.properties and openfact.properties
        // on META-INF/keycloak.properties and META-INF/openfact.properties
        OpenfactClient openfact = OpenfactClient.buildTarget();

        // Listing all organizations
        List<OrganizationRepresentation> organizations = openfact.organizations().findAll();
        for (OrganizationRepresentation organization : organizations) {
            System.out.println(organization.getOrganization());
        }
    }

}