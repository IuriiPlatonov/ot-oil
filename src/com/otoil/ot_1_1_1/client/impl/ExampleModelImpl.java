package com.otoil.ot_1_1_1.client.impl;


import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.otoil.ot_1_1_1.client.ExampleModel;
import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.shared.ExampleTaskService;

import io.reactivex.Single;
import ru.ot.gwt.utils.client.rest.RestServiceAdapter;


public class ExampleModelImpl implements ExampleModel
{

    private RestServiceAdapter<ExampleTaskService> service = RestServiceAdapter
        .get(GWT.create(ExampleTaskService.class));

    @Override
    public Single<List<ResponseDocumentCardBean>> getDocumentsCard()
    {
        return service.toSingle(rs -> rs.getDocumentCard());
    }

    @Override
    public Single<List<AttributeNameBean>> getObjectAttribute(String id)
    {
        return service.toSingle(rs -> rs.getObjectAttribute(id));
    }

    @Override
    public Single<Boolean> saveDocumentCard(RequestDocumentCardBean request)
    {
        return service.toSingle(rs -> rs.saveDocumentCard(request));
    }

}
