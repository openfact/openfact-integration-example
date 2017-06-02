package org.openfact.admin.client.resource;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public interface DocumentsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response search(@QueryParam("documentType") String documentType,
                    @QueryParam("documentId") String documentId,
                    @QueryParam("first") Integer firstResult,
                    @QueryParam("max") Integer maxResults);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response search(@QueryParam("filterText") String filterText,
                            @QueryParam("first") Integer firstResult,
                            @QueryParam("max") Integer maxResults);

    @Path("search")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    Response search(JsonObject criteria);

    @Path("{documentId}")
    DocumentResource get(@PathParam("documentId") String documentId);

}
