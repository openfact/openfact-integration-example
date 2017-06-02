package org.openfact.admin.client;

import org.junit.Before;
import org.openfact.admin.client.resource.OrganizationResource;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class AbstractTestOrganizationResourceIT extends AbstractResourceIT {

    private static final String organizationName = "test";

    protected JsonObject organization;
    protected OrganizationResource organizationResource;

    @Before
    public void before() {
        super.before();

        JsonObject organization = Json
                .createObjectBuilder()
                .add("organization", organizationName)
                .build();

        Response response = jaxrsClient
                .organizations()
                .create(organization);

        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            throw new IllegalStateException("Could not create test organization");
        }

        String uri = response.getHeaderString("Location");
        this.organization = jaxrsClient.target(uri).request().get(JsonObject.class);
        this.organizationResource = jaxrsClient.organizations().organization(organizationName);
    }

}
