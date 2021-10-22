package com.otoil.ot_1_1_1.client.impl;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.ExampleView;
import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.i18n.ExampleTaskConstant;


public class ExampleViewImpl implements ExampleView
{

    private Label titleDocumentCardsTable = new Label();
    private Label titleDetailsTable = new Label();
    private VerticalPanel verticalPanelForDoc = new VerticalPanel();
    private VerticalPanel verticalPanelForDetail = new VerticalPanel();
    private HorizontalPanel horizontalPanel = new HorizontalPanel();
    private FlexTable documentCardTable = new FlexTable();
    private ExampleTaskConstant constant = GWT
        .create(ExampleTaskConstant.class);

    private FlexTable detailTable = new FlexTable();

    public void createViews()
    {
        documentCardTable.setText(0, 0, constant.name());
        documentCardTable.setText(0, 1, constant.orderedNumber());
        documentCardTable.setText(0, 2, constant.changeDate());
        documentCardTable.setText(0, 3, constant.image());

        verticalPanelForDoc.addStyleName("verticalPanel");
        verticalPanelForDetail.addStyleName("detailsTable");
        horizontalPanel.addStyleName("horizontalPanel");

        titleDocumentCardsTable.addStyleName("title");
        titleDetailsTable.addStyleName("title");

        detailTable.addStyleName("detailsTable");
        documentCardTable.addStyleName("table");

        documentCardTable.getColumnFormatter().addStyleName(0, "firstColumn");
        documentCardTable.getColumnFormatter().addStyleName(1, "secondColumn");
        documentCardTable.getColumnFormatter().addStyleName(2, "thirdColumn");
        documentCardTable.getColumnFormatter().addStyleName(3, "fourthColumn");

        detailTable.getColumnFormatter().addStyleName(0, "firstDetailColumn");
        detailTable.getColumnFormatter().addStyleName(1, "secondDetailColumn");

        titleDocumentCardsTable.setText(constant.docCardTable());
        titleDetailsTable.setText(constant.detail());

        verticalPanelForDoc.add(titleDocumentCardsTable);
        verticalPanelForDoc.add(documentCardTable);
        verticalPanelForDetail.add(titleDetailsTable);
        verticalPanelForDetail.add(detailTable);

        horizontalPanel.add(verticalPanelForDoc);
        horizontalPanel.add(verticalPanelForDetail);

        RootPanel.get("resultTable").add(horizontalPanel);

    }

    public void addDataToDocCardTable(
        List<ResponseDocumentCardBean> documentDataList)
    {
        for (int i = 0; i < documentDataList.size(); i++)
        {

            int row = documentCardTable.getRowCount();

            Button saveButton = new Button(constant.save());
            saveButton.setVisible(false);

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
            documentCardTable.setWidget(row, 4, saveButton);
            documentCardTable.setWidget(row, 5, id);
        }

    }

    @Override
    public void addDataToDetailTable(List<AttributeNameBean> detailList)
    {

        detailTable.removeAllRows();
        detailTable.setText(0, 0, constant.attributesName());
        detailTable.setText(0, 1, constant.values());

        for (int i = 0; i < detailList.size(); i++)
        {

            int row = detailTable.getRowCount();

            detailTable.setWidget(row, 0,
                new Label(detailList.get(i).getName()));
            detailTable.setWidget(row, 1,
                new Label(detailList.get(i).getValueString()));
        }

    }

    @Override
    public FlexTable getTable()
    {
        // TODO Auto-generated method stub
        return documentCardTable;
    }

    @Override
    public Widget asWidget()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
