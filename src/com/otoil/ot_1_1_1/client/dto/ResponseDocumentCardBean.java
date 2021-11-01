package com.otoil.ot_1_1_1.client.dto;


import java.util.Date;

import com.otoil.ot_1_1_1.shared.DefaultTreeNamedBean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDocumentCardBean extends DefaultTreeNamedBean
{
    private String dcmcrdId;
    private String name;
    private String orderNumber;
    private Date changeDate;
    private String dcmcrdDcmcrdId;
    private String binaryData;
}
