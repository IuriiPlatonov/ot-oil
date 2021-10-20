package com.otoil.ot_1_1_1.client.impl;


import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.ExampleView;
import com.otoil.ot_1_1_1.client.dto.AttributeName;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.i18n.LocalizedFields;


public class ExampleViewImpl implements ExampleView
{

    private Label titleDocumentCardsTable = new Label();
    private Label titleDetailsTable = new Label();
    private VerticalPanel verticalPanelForDoc = new VerticalPanel();
    private VerticalPanel verticalPanelForDetail = new VerticalPanel();
    private HorizontalPanel horizontalPanel = new HorizontalPanel();
    private FlexTable documentCardTable = new FlexTable();
    private LocalizedFields localizedFields = GWT.create(LocalizedFields.class);

    private FlexTable detailTable = new FlexTable(); 


    public void createViews()
    {
        documentCardTable.setText(0, 0, localizedFields.name());
        documentCardTable.setText(0, 1, localizedFields.orderedNumber());
        documentCardTable.setText(0, 2, localizedFields.changeDate());
        documentCardTable.setText(0, 3, localizedFields.image());

        detailTable.setText(0, 0, "Name                                                             /");

        
        verticalPanelForDoc.addStyleName("verticalPanel");
        verticalPanelForDetail.addStyleName("verticalPanel");
        horizontalPanel.addStyleName("horizontalPanel");
        
        titleDocumentCardsTable.addStyleName("title");
        titleDetailsTable.addStyleName("title");
        
        detailTable.addStyleName("table");
        documentCardTable.addStyleName("table");
        
        documentCardTable.getColumnFormatter().addStyleName(0, "firstColumn");
        documentCardTable.getColumnFormatter().addStyleName(1, "secondColumn");
        documentCardTable.getColumnFormatter().addStyleName(2, "thirdColumn");
        documentCardTable.getColumnFormatter().addStyleName(3, "fourthColumn");

        detailTable.getColumnFormatter().addStyleName(0, "firstDetailColumn");
        
        titleDocumentCardsTable.setText("Document cards table");
        titleDetailsTable.setText("Detail");
        verticalPanelForDoc.add(titleDocumentCardsTable);
        verticalPanelForDoc.add(documentCardTable);
        verticalPanelForDetail.add(titleDetailsTable);
        verticalPanelForDetail.add(detailTable);
        horizontalPanel.add(verticalPanelForDoc);

        horizontalPanel.add(verticalPanelForDetail);
        RootPanel.get("resultTable").add(horizontalPanel);

    }

    public void addDataToDocCardTable(List<ResponseDocumentCardBean> documentDataList)
    {
        for (int i = 0; i < documentDataList.size(); i++)
        {

            int row = documentCardTable.getRowCount();

            Button saveButton = new Button("save");
            saveButton.setVisible(false);

            Label id = new Label(documentDataList.get(i).getId());
            id.setVisible(false);

            documentCardTable.setWidget(row, 0,
                new Label(documentDataList.get(i).getName()));
            documentCardTable.setWidget(row, 1, new Label(
                Integer.toString(documentDataList.get(i).getOrderedNumber())));
            documentCardTable.setWidget(row, 2,
                new Label(documentDataList.get(i).getChangeDate().toString()));
            documentCardTable.setWidget(row, 3,
                new Image("http://www.tutorialspoint.com/images/gwt-mini.png"));
            documentCardTable.setWidget(row, 4, saveButton);
            documentCardTable.setWidget(row, 5, id);
        }

    }
    
    @Override
    public void addDataToDetailTable(List<AttributeName> detailList)
    {
        // TODO Auto-generated method stub
        for (int i = 0; i < detailList.size(); i++){
            detailTable.setWidget(i, 0, new Label(detailList.get(i).getName()));
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
