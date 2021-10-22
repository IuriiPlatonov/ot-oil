package com.otoil.ot_1_1_1.client.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AttributeNameBean
{
    private String name;
    private String valueString;

    @JsonCreator
    public AttributeNameBean(@JsonProperty("name") String name,@JsonProperty("valueString") String valueString)
    {
        this.name = name;
        this.valueString = valueString;
    }
}
