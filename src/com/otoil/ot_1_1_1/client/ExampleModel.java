package com.otoil.ot_1_1_1.client;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;


@Path("api/documentCard")
public interface ExampleModel extends RestService
{

    @GET
    public void getDocumentsCard(
        MethodCallback<List<ResponseDocumentCardBean>> callback);

    @PUT
    public void saveDocumentCard(RequestDocumentCardBean request,
        MethodCallback<Integer> callback);
}
