package org.openfact.admin.client.resource;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public interface ComponentsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<JsonObject> query();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<JsonObject> query(@QueryParam("parent") String parent);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<JsonObject> query(@QueryParam("parent") String parent,
                           @QueryParam("type") String type);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<JsonObject> query(@QueryParam("parent") String parent,
                           @QueryParam("type") String type,
                           @QueryParam("name") String name);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response add(JsonObject rep);

    @Path("{id}")
    ComponentResource component(@PathParam("id") String id);

}
