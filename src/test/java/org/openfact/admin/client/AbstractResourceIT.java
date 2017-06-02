package org.openfact.admin.client;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.core.Response;
import java.util.Iterator;

public class AbstractResourceIT {

    @Rule
    public OpenfactJAXRSClient jaxrsClient = OpenfactJAXRSClient.buildTarget();

    @Before
    public void before() {
        refreshData();
    }

    @After
    public void after() {
        refreshData();
    }

    private void refreshData() {
        Response response = jaxrsClient.organizations().findAll();
        JsonArray jsonArray = response.readEntity(JsonArray.class);

        Iterator<JsonValue> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JsonObject jsonObject = (JsonObject) iterator.next();

            String organization = jsonObject.getString("organization");
            if (!organization.equals("master")) {
                jaxrsClient.organizations().organization(organization).remove();
            }
        }
    }

}
