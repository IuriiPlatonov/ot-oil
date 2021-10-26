package com.otoil.ot_1_1_1.client.master.impl;


import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.i18n.ExampleTaskConstant;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterView;


public class ExampleTaskMasterViewImpl extends VerticalPanel
        implements ExampleTaskMasterView
{

    private final static ExampleTaskConstant CONSTANTS = ExampleTaskConstant.INSTANCE;
    private Label titleDocumentCardsTable = new Label();
    private FlexTable documentCardTable = new FlexTable();

    @Override
    public Widget asWidget()
    {
        return this;
    }

    public ExampleTaskMasterViewImpl()
    {
        onLoad();
    }

    public void onLoad()
    {
        documentCardTable.setText(0, 0, CONSTANTS.name());
        documentCardTable.setText(0, 1, CONSTANTS.orderedNumber());
        documentCardTable.setText(0, 2, CONSTANTS.changeDate());
        documentCardTable.setText(0, 3, CONSTANTS.image());

        titleDocumentCardsTable.addStyleName("title");
        documentCardTable.addStyleName("table");
        this.addStyleName("masterPanel");

        documentCardTable.getColumnFormatter().addStyleName(0, "firstColumn");
        documentCardTable.getColumnFormatter().addStyleName(1, "secondColumn");
        documentCardTable.getColumnFormatter().addStyleName(2, "thirdColumn");
        documentCardTable.getColumnFormatter().addStyleName(3, "fourthColumn");

        titleDocumentCardsTable.setText(CONSTANTS.docCardTable());

        this.add(titleDocumentCardsTable);
        this.add(documentCardTable);
    }

    public void addDataToDocCardTable(
        List<ResponseDocumentCardBean> documentDataList)
    {
        for (int i = 0; i < documentDataList.size(); i++)
        {

            int row = documentCardTable.getRowCount();

            Button saveButton = new Button(CONSTANTS.save());
            saveButton.setVisible(false);

            Button detailButton = new Button(CONSTANTS.detail());

            Label id = new Label(documentDataList.get(i).getDcmcrdId());
            id.setVisible(false);

            Image image = new Image(documentDataList.get(i).getBinaryData());

            documentCardTable.setWidget(row, 0,
                new Label(documentDataList.get(i).getName()));
            documentCardTable.setWidget(row, 1,
                new Label(documentDataList.get(i).getOrderNumber()));
            documentCardTable.setWidget(row, 2,
                new Label(documentDataList.get(i).getChangeDate().toString()));
            documentCardTable.setWidget(row, 3, image);
            documentCardTable.setWidget(row, 4, detailButton);
            documentCardTable.setWidget(row, 5, saveButton);
            documentCardTable.setWidget(row, 6, id);
        }

    }

    @Override
    public HasClickHandlers getTableClick()
    {
        return documentCardTable;
    }

}
