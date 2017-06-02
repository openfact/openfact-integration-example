package org.openfact.admin.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface StorageFilesResource {

    @GET
    @Path("{idFile}")
    Response getFile(@PathParam("idFile") String idFile);

}
