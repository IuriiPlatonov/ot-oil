package com.otoil.ot_1_1_1.server.entities.objectattribute;


import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.server.entities.documentcard.DocumentCard;

import ru.ep.sdo.Entity;
import ru.ep.sdo.annotations.Xml;
import ru.ot.gwt.sdo.server.beans.BeanConverter;


@Xml(name = "ROW")
public class ObjectAttribute extends Entity
{

    public static final String PROPERTYNAME_NAME = "name";

    @Xml(name = "NAME")
    private String name;

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

    public static BeanConverter<ObjectAttribute, String> converter()
    {
        return new BeanConverter<>(ObjectAttribute.class, String.class);
    }

}
