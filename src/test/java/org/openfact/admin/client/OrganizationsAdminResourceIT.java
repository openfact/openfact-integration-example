package org.openfact.admin.client;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OrganizationsAdminResourceIT extends AbstractResourceIT {

    @Test
    public void findNotExisting() {
        String key = UUID.randomUUID().toString();
        Response response = jaxrsClient.organizations().organization(key).toRepresentation();
        assertThat(response.getStatus(), is(Response.Status.NOT_FOUND.getStatusCode()));
    }

    @Test
    public void findAll() {
        Response response = jaxrsClient.organizations().findAll();
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        JsonArray organizations = response.readEntity(JsonArray.class);
        assertTrue(organizations.size() > 0);
    }

    @Test
    public void create() {
        final String organizationName = "test";

        JsonObject organization = Json.createObjectBuilder().
                add("organization", organizationName).
                build();

        Response response = jaxrsClient.organizations().create(organization);
        assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());

        String uri = response.getHeaderString("Location");
        assertNotNull(uri);

        JsonObject result = jaxrsClient.target(uri).request().get(JsonObject.class);
        assertNotNull(result);
        assertThat(result.getString("organization"), is(organizationName));
    }

    @Test
    public void createDuplicate() {
        final String organization1Name = "test1";
        final String organization2Name = "test2";

        JsonObject organization1 = Json.createObjectBuilder().
                add("organization", organization1Name).
                build();
        JsonObject organization2 = Json.createObjectBuilder().
                add("organization", organization2Name).
                build();

        Response response1 = jaxrsClient.organizations().create(organization1);
        assertEquals(response1.getStatus(), Response.Status.CREATED.getStatusCode());

        Response response2 = jaxrsClient.organizations().create(organization1);
        assertEquals(response2.getStatus(), Response.Status.CONFLICT.getStatusCode());
    }

}
