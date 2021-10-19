package com.otoil.ot_1_1_1.client.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class RequestDocumentCardBean
{
    private final String id;
    private final String name;
    private final Integer orderedNumber;

    @JsonCreator
    public RequestDocumentCardBean(@JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("orderedNumber") Integer orderedNumber)
    {
        this.id = id;
        this.name = name;
        this.orderedNumber = orderedNumber;

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

}
