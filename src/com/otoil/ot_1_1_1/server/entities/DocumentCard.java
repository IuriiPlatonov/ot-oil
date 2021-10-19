package com.otoil.ot_1_1_1.server.entities;


import ru.ep.sdo.Entity;
import ru.ep.sdo.annotations.Xml;
import ru.ot.gwt.sdo.server.beans.BeanConverter;

import java.awt.geom.Area;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;

import java.sql.Blob;


@Xml(name = "ROW")
public class DocumentCard extends Entity
{

    public static final String PROPERTYNAME_DCMCRD_ID = "dcmcrdId";
    public static final String PROPERTYNAME_NAME = "name";
    public static final String PROPERTYNAME_ORDER_NUMBER = "orderNumber";
    public static final String PROPERTYNAME_CHANGE_DATE = "changeDate";
    public static final String PROPERTYNAME_BINARY_DATA = "binaryData";

    @Xml(name = "DCMCRD_ID")
    private BigDecimal dcmcrdId;

    @Xml(name = "NAME")
    private String name;

    @Xml(name = "ORDER_NUMBER")
    private BigDecimal orderNumber;

    @Xml(name = "CHANGE_DATE")
    private Timestamp changeDate;

    @Xml(name = "BINARY_DATA")
    private Blob binaryData;

    public BigDecimal getDcmcrdId()
    {
        return dcmcrdId;
    }

    public void setDcmcrdId(BigDecimal dcmcrdId)
    {
        BigDecimal oldValue = this.dcmcrdId;
        this.dcmcrdId = dcmcrdId;
        firePropertyChange(PROPERTYNAME_DCMCRD_ID, oldValue, dcmcrdId);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        String oldValue = this.name;
        this.name = name;
        firePropertyChange(PROPERTYNAME_NAME, oldValue, name);
    }

    public BigDecimal getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(BigDecimal orderNumber)
    {
        BigDecimal oldValue = this.orderNumber;
        this.orderNumber = orderNumber;
        firePropertyChange(PROPERTYNAME_ORDER_NUMBER, oldValue, orderNumber);
    }

    public Timestamp getChangeDate()
    {
        return changeDate;
    }

    public void setChangeDate(Timestamp changeDate)
    {
        Timestamp oldValue = this.changeDate;
        this.changeDate = changeDate;
        firePropertyChange(PROPERTYNAME_CHANGE_DATE, oldValue, changeDate);
    }

    public Blob getBinaryData()
    {
        return binaryData;
    }

    public void setBinaryData(Blob binaryData)
    {
        Blob oldValue = this.binaryData;
        this.binaryData = binaryData;
        firePropertyChange(PROPERTYNAME_BINARY_DATA, oldValue, binaryData);
    }

    public static BeanConverter<DocumentCard, ResponseDocumentCardBean> converter()
    {
        return new BeanConverter<>(DocumentCard.class, ResponseDocumentCardBean.class);
    }

}
