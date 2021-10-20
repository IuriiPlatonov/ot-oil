package com.otoil.ot_1_1_1.shared;


import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.fusesource.restygwt.client.DirectRestService;

import com.otoil.ot_1_1_1.client.dto.AttributeName;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import javax.ws.rs.core.MediaType;

@Produces("application/json")
public interface DBConnector
{

    @GET
    @Path("/documentCard/")
    List<ResponseDocumentCardBean> getDocumentCard();

    @PUT
    @Path("/documentCard/")
    Integer saveDocumentCard(RequestDocumentCardBean request);
    
    @GET
    @Path("/attributeName/")
    List<AttributeName> getObjectAttribute();
}
