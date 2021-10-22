package com.otoil.ot_1_1_1.client.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RequestDocumentCardBean
{
    private String dcmcrdId;
    private String name;
    private String orderNumber;

    @JsonCreator
    public RequestDocumentCardBean(@JsonProperty("dcmcrdId") String dcmcrdId,
        @JsonProperty("name") String name,
        @JsonProperty("orderNumber") String orderNumber)
    {
        this.dcmcrdId = dcmcrdId;
        this.name = name;
        this.orderNumber = orderNumber;
    }
}
