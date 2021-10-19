package com.otoil.ot_1_1_1.shared;


import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import org.fusesource.restygwt.client.DirectRestService;

import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;


public interface DBConnector extends DirectRestService
{

    @GET
    @Produces("application/json")
    List<ResponseDocumentCardBean> getDocumentCard();

    @PUT
    @Produces("application/json")
    Integer saveDocumentCard(RequestDocumentCardBean request);
}
