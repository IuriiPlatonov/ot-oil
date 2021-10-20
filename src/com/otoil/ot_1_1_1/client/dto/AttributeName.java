package com.otoil.ot_1_1_1.client.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeName
{
    private final String name;

    @JsonCreator
    public AttributeName(@JsonProperty("name") String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    
}
