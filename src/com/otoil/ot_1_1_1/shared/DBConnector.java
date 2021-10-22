package com.otoil.ot_1_1_1.shared;


import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.fusesource.restygwt.client.DirectRestService;

import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import javax.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public interface DBConnector extends DirectRestService
{

    @GET
    @Path("/documentCard/")
    List<ResponseDocumentCardBean> getDocumentCard();

    @PUT
    @Path("/documentCard/")
    Boolean saveDocumentCard(RequestDocumentCardBean request);

    @GET
    @Path("/attributeName/{id}")
    List<AttributeNameBean> getObjectAttribute(@PathParam("id") String id);
}
