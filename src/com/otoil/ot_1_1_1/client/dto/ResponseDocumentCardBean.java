package com.otoil.ot_1_1_1.client.dto;


import java.sql.Blob;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseDocumentCardBean
{
    private String dcmcrdId;
    private String name;
    private String orderNumber;
    private Date changeDate;
    private String binaryData;

    @JsonCreator
    public ResponseDocumentCardBean(@JsonProperty("dcmcrdId") String dcmcrdId,
        @JsonProperty("name") String name,
        @JsonProperty("orderNumber") String orderNumber,
        @JsonProperty("changeDate") Date changeDate,
        @JsonProperty("binaryData") String binaryData)
    {
        this.dcmcrdId = dcmcrdId;
        this.name = name;
        this.orderNumber = orderNumber;
        this.changeDate = changeDate;
        this.binaryData = binaryData;
    }
}
