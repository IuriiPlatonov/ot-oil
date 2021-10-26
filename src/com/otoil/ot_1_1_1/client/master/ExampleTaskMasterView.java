package com.otoil.ot_1_1_1.client.master;


import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.IsWidget;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;


public interface ExampleTaskMasterView extends IsWidget
{
    void addDataToDocCardTable(List<ResponseDocumentCardBean> documentDataList);
    HasClickHandlers getTableClick();
}
