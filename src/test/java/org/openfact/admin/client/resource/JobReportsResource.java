package org.openfact.admin.client.resource;

import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface JobReportsResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<JsonObject> getReports(
            @QueryParam("jobName") List<String> jobNames,
            @QueryParam("dateFrom") String dateFrom,
            @QueryParam("dateTo") String dateTo,
            @QueryParam("first") Integer firstResult,
            @QueryParam("max") Integer maxResults);

    @GET
    @Path("providers")
    @Produces(MediaType.APPLICATION_JSON)
    List<String> getFile();

}
