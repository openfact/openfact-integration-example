package org.openfact.admin.client.resource;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

public interface ComponentResource {

    @GET
    JsonObject toRepresentation();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(JsonObject rep);

    @DELETE
    void remove();

}
