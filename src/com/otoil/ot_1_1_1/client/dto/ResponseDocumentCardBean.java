package com.otoil.ot_1_1_1.client.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDocumentCardBean
{
    private String dcmcrdId;
    private String name;
    private String orderNumber;
    private Date changeDate;
    private String binaryData;

}
