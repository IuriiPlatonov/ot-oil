package com.otoil.ot_1_1_1.client;


import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;


public interface ExampleView extends IsWidget
{
    void createViews();

    void addDataToDocCardTable(List<ResponseDocumentCardBean> documentDataList);
    
    void addDataToDetailTable(List<AttributeNameBean> detailList);

    FlexTable getTable();
}
