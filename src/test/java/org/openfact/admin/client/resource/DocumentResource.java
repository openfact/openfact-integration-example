package org.openfact.admin.client.resource;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DocumentResource {

    @GET
    public JsonObject toRepresentation();

    @PUT
    public void update(JsonObject documentRepresentation);

    @DELETE
    public void remove();

    @GET
    @Path("representation/json")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject jsonRepresentation();

    @GET
    @Path("representation/xml")
    @Produces("application/xml")
    public Response downloadXml();

    @Path("report")
    @GET
    public Response report(@QueryParam("theme") String theme,
                           @QueryParam("format") String format);

    @POST
    @Path("send-to-customer")
    public void sendToCustomer();

    @POST
    @Path("send-to-third-party")
    public void sendToThirdParty();

    @POST
    @Path("send-to-third-party-by-email")
    public void sendToCustomEmail();

    @GET
    @Path("send-events")
    public List<JsonObject> sendedEvents();

}
