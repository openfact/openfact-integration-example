package org.openfact.admin.client.resource;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public interface OrganizationResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response toRepresentation();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(JsonObject organizationRepresentation);

    @DELETE
    Response remove();

    @Path("documents")
    DocumentsResource documents();

    @Path("invoices")
    InvoicesResource invoices();

    @Path("credit-notes")
    CreditNotesResource creditNotes();

    @Path("debit-notes")
    DebitNotesResource debitNotes();

    @Path("job-reports")
    JobReportsResource jobReports();

    @Path("storage-files")
    StorageFilesResource storageFiles();

    @DELETE
    @Path("events")
    void clearEvents();

    @GET
    @Path("events")
    @Produces(MediaType.APPLICATION_JSON)
    List<JsonObject> getEvents();

    @Path("events")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<JsonObject> getEvents(@QueryParam("type") List<String> types,
                               @QueryParam("user") String user,
                               @QueryParam("dateFrom") String dateFrom,
                               @QueryParam("dateTo") String dateTo,
                               @QueryParam("ipAddress") String ipAddress,
                               @QueryParam("first") Integer firstResult,
                               @QueryParam("max") Integer maxResults);

    @DELETE
    @Path("admin-events")
    void clearAdminEvents();

    @GET
    @Path("admin-events")
    @Produces(MediaType.APPLICATION_JSON)
    List<JsonObject> getAdminEvents();

    @GET
    @Path("admin-events")
    @Produces(MediaType.APPLICATION_JSON)
    List<JsonObject> getAdminEvents(@QueryParam("operationTypes") List<String> operationTypes,
                                    @QueryParam("authOrganization") String authOrganization,
                                    @QueryParam("authUser") String authUser,
                                    @QueryParam("authIpAddress") String authIpAddress,
                                    @QueryParam("resourcePath") String resourcePath,
                                    @QueryParam("dateFrom") String dateFrom,
                                    @QueryParam("dateTo") String dateTo,
                                    @QueryParam("first") Integer firstResult,
                                    @QueryParam("max") Integer maxResults,
                                    @QueryParam("resourceTypes") List<String> resourceTypes);

    @GET
    @Path("events/config")
    @Produces(MediaType.APPLICATION_JSON)
    JsonObject getOrganizationEventsConfig();

    @PUT
    @Path("events/config")
    @Consumes(MediaType.APPLICATION_JSON)
    void updateOrganizationEventsConfig(JsonObject rep);

    @Path("components")
    ComponentsResource components();

    @Path("keys")
    KeyResource keys();

}
