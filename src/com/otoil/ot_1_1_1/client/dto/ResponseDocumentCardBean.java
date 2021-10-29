package com.otoil.ot_1_1_1.client.dto;


import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.otoil.ot_040_5_0010.client.beans.tree.DefaultTreeNamedBean;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDocumentCardBean extends DefaultTreeNamedBean
{
    private String dcmcrdId;
    private String name;
    private String orderNumber;
    private Date changeDate;
    private String binaryData;
}
