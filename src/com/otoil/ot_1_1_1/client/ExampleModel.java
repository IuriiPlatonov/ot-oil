package com.otoil.ot_1_1_1.client;


import java.util.List;

import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;

import io.reactivex.Single;
import ru.ot.mvp.client.presenters.Model;


public interface ExampleModel extends Model
{
    Single<List<ResponseDocumentCardBean>> getDocumentsCard();

    Single<List<AttributeNameBean>> getObjectAttribute(String id);

    Single<Boolean> saveDocumentCard(RequestDocumentCardBean request);
    // public interface ExampleModel extends RestService
    // {
    //
    // @GET
    // @Path("/api/documentCard")
    // void getDocumentsCard(
    // MethodCallback<List<ResponseDocumentCardBean>> callback);
    //
    // @GET
    // @Path("/api/attributeName/{id}")
    // void getObjectAttribute(@PathParam("id") String id,
    // MethodCallback<List<AttributeNameBean>> callback);
    //
    // @PUT
    // @Path("/api/documentCard")
    // void saveDocumentCard(RequestDocumentCardBean request,
    // MethodCallback<Boolean> callback);

}
