package org.openfact.admin.client.resource;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin/organizations")
@Consumes(MediaType.APPLICATION_JSON)
public interface OrganizationsResource {

    @Path("/{organization}")
    OrganizationResource organization(@PathParam("organization") String organization);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(JsonObject organizationRepresentation);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response findAll();

}
