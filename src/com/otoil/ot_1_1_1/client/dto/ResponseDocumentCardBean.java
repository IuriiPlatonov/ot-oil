package com.otoil.ot_1_1_1.client.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.otoil.ot_1_1_1.shared.DefaultTreeNamedBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDocumentCardBean extends DefaultTreeNamedBean
{
    private String dcmcrdId;
    private String docName;
    private String orderNumber;
    private Date changeDate;
    private String dcmcrdDcmcrdId;
    private String binaryData;
}
