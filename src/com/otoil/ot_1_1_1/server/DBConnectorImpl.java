package com.otoil.ot_1_1_1.server;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Path;

import com.google.gwt.user.client.Window;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.server.entities.DocumentCard;
import com.otoil.ot_1_1_1.shared.DBConnector;

import cern.colt.Arrays;
import ru.ep.sdo.Session;
import ru.ep.sdo.SessionFactory;

import ru.ep.sdo.list.XMLListModel;
import ru.ot.gwt.sdo.server.beans.BeanConverter;


@Path("documentCard")
public class DBConnectorImpl implements DBConnector
{

    private final String url = "jdbc:oracle:thin:@10.100.22.9:1521:HPDOILDV";
    private final String user = "ADMIN";
    private final String password = "admin";

    @Override
    public List<ResponseDocumentCardBean> getDocumentCard()
    {
        Session session = SessionFactory.getInstance()
            .createSessionFromFile("session.session", null);

        XMLListModel listModel = session
            .getListModel("ExampleTask.DocumentCard");

        return BeanConverter.load(listModel, DocumentCard.converter()).join();
    }

    @Override
    public Integer saveDocumentCard(RequestDocumentCardBean docCard)
    {

        int result = 0;

        Session session = SessionFactory.getInstance()
            .createSessionFromFile("session.session", null);

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

}
