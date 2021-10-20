package com.otoil.ot_1_1_1.server;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import com.otoil.ot_1_1_1.client.dto.AttributeName;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.server.entities.documentcard.DocumentCard;
import com.otoil.ot_1_1_1.shared.DBConnector;

import ru.ep.sdo.Session;
import ru.ep.sdo.SessionFactory;

import ru.ep.sdo.list.XMLListModel;
import ru.ot.gwt.sdo.server.beans.BeanConverter;

public class DBConnectorImpl implements DBConnector
{

    Session session;

    public DBConnectorImpl()
    {
        session = SessionFactory.getInstance()
            .createSessionFromFile("session.session", null);
    }
    

   
    @Override
    public List<ResponseDocumentCardBean> getDocumentCard()
    {
        XMLListModel listModel = session
            .getListModel("ExampleTask.DocumentCard");

        return BeanConverter.load(listModel, DocumentCard.converter()).join();
    }


    @Override
    public Integer saveDocumentCard(RequestDocumentCardBean docCard)
    {

        int result = 0;

        XMLListModel listModel = session
            .getListModel("ExampleTask.DocumentCard");

        listModel.setWhereClauseParam(DocumentCard.PROPERTYNAME_DCMCRD_ID,
            docCard.getId());
        listModel.fetchAll();
        DocumentCard bo = (DocumentCard) listModel.get(0);
        bo.setName(docCard.getName());
        bo.setOrderNumber(BigDecimal.valueOf(docCard.getOrderedNumber()));
        session.commit();

        return result;
    }

    
    @Override
    public List<AttributeName> getObjectAttribute()
    {
//        XMLListModel listModel = session
//            .getListModel("ExampleTask.ObjectAttribute");
//
//        Iterator<String> iterator = listModel.iterator();

        List<AttributeName> list = new ArrayList<>();
        list.add(new AttributeName("awd"));
//        while (iterator.hasNext())
//        {
//            String name = iterator.next();
//            list.add(name);
//        }

        return list;
//        return BeanConverter.load(listModel, ObjectAttribute.converter())
//            .join();
    }


}
