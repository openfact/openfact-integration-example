package org.openfact.admin.client;

import org.junit.Test;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ServerInfoAdminResourceIT extends AbstractResourceIT {

    @Test
    public void testServerInfo() {
        Response response = jaxrsClient.serverInfo().getInfo();
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        JsonObject serverInfo = response.readEntity(JsonObject.class);
        assertNotNull(serverInfo);
    }

}
