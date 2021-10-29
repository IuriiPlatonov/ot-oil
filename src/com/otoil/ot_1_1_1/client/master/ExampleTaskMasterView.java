package com.otoil.ot_1_1_1.client.master;


import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;


import ru.ot.wevelns.client.tree.TreeDataDisplay;
import ru.ot.wevelns.client.tree.DefaultTreeNode;

import io.reactivex.subjects.PublishSubject;


public interface ExampleTaskMasterView extends IsWidget
{
    void addDataToDocCardTable(List<ResponseDocumentCardBean> documentDataList);
    PublishSubject<RequestDocumentCardBean> getSaveSubject(); 
    PublishSubject<String> getDetailId(); 
    TreeDataDisplay<DefaultTreeNode<ResponseDocumentCardBean>> getTree();

}
