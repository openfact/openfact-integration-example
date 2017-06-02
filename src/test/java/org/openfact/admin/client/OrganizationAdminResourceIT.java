package org.openfact.admin.client;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OrganizationAdminResourceIT extends AbstractTestOrganizationResourceIT {

    @Test
    public void toRepresentation() {
        Response response = organizationResource.toRepresentation();
        assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));

        JsonObject jsonObject = response.readEntity(JsonObject.class);
        assertNotNull(jsonObject);
    }

    @Test
    public void update() {
        JsonObject updatedInfo = Json
                .createObjectBuilder()
                .add("description", "my description")
                .build();

        Response response = organizationResource.update(updatedInfo);
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));

        JsonObject result = organizationResource.toRepresentation().readEntity(JsonObject.class);
        assertNotNull(result);
        assertEquals(result.getString("description"), "my description");
    }

    @Test
    public void remove() {
        Response response = organizationResource.remove();
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));

        assertThat(organizationResource.toRepresentation().getStatus(), is(Response.Status.NOT_FOUND.getStatusCode()));
    }

}
