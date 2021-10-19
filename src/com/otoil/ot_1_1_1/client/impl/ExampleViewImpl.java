package com.otoil.ot_1_1_1.client.impl;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.ExampleView;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.i18n.LocalizedFields;


public class ExampleViewImpl implements ExampleView
{

    private Label title = new Label();
    private VerticalPanel mainPanel = new VerticalPanel();
    private FlexTable resultTable = new FlexTable();
    private LocalizedFields localizedFields = GWT.create(LocalizedFields.class);

    public void createViews()
    {
        resultTable.setText(0, 0, localizedFields.name());
        resultTable.setText(0, 1, localizedFields.orderedNumber());
        resultTable.setText(0, 2, localizedFields.changeDate());
        resultTable.setText(0, 3, localizedFields.image());

        mainPanel.addStyleName("mainPanel");
        title.addStyleName("title");
        resultTable.addStyleName("table");
        resultTable.getColumnFormatter().addStyleName(0, "firstColumn");
        resultTable.getColumnFormatter().addStyleName(1, "secondColumn");
        resultTable.getColumnFormatter().addStyleName(2, "thirdColumn");
        resultTable.getColumnFormatter().addStyleName(3, "fourthColumn");

        title.setText("Test table");

        mainPanel.add(title);
        mainPanel.add(resultTable);

        RootPanel.get("resultTable").add(mainPanel);

    }

    public void addDataToTable(List<ResponseDocumentCardBean> documentDataList)
    {
        for (int i = 0; i < documentDataList.size(); i++)
        {

            int row = resultTable.getRowCount();

            Button saveButton = new Button("save");
            saveButton.setVisible(false);

            Label id = new Label(documentDataList.get(i).getId());
            id.setVisible(false);

            resultTable.setWidget(row, 0,
                new Label(documentDataList.get(i).getName()));
            resultTable.setWidget(row, 1, new Label(
                Integer.toString(documentDataList.get(i).getOrderedNumber())));
            resultTable.setWidget(row, 2,
                new Label(documentDataList.get(i).getChangeDate().toString()));
            resultTable.setWidget(row, 3,
                new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
            resultTable.setWidget(row, 4, saveButton);
            resultTable.setWidget(row, 5, id);
        }

    }

    @Override
    public FlexTable getTable()
    {
        // TODO Auto-generated method stub
        return resultTable;
    }

    @Override
    public Widget asWidget()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
