package com.otoil.ot_1_1_1.client;


import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;


public interface ExampleView extends IsWidget
{
    void createViews();

    void addDataToTable(List<ResponseDocumentCardBean> documentDataList);

    FlexTable getTable();
}
