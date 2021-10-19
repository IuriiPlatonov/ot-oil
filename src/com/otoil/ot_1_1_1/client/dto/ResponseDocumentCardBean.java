package com.otoil.ot_1_1_1.client.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ResponseDocumentCardBean
{

    private final String id;
    private final String name;
    private final Integer orderedNumber;
    private final Date changeDate;
    private final String image;

    @JsonCreator
    public ResponseDocumentCardBean(@JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("orderedNumber") Integer orderedNumber,
        @JsonProperty("changeDate") Date changeDate,
        @JsonProperty("image") String image)
    {
        this.id = id;
        this.name = name;
        this.orderedNumber = orderedNumber;
        this.changeDate = changeDate;
        this.image = image;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Integer getOrderedNumber()
    {
        return orderedNumber;
    }

    public Date getChangeDate()
    {
        return changeDate;
    }

    public String getImage()
    {
        return image;
    }

}
