package com.otoil.ot_1_1_1.client;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;


public interface ExampleModel extends RestService
{

    @GET
    @Path("/api/documentCard")
    void getDocumentsCard(
        MethodCallback<List<ResponseDocumentCardBean>> callback);

    @GET
    @Path("/api/attributeName/{id}")
    void getObjectAttribute(@PathParam("id") String id, MethodCallback<List<AttributeNameBean>> callback);

    @PUT
    @Path("/api/documentCard")
    void saveDocumentCard(RequestDocumentCardBean request,
        MethodCallback<Boolean> callback);

}
