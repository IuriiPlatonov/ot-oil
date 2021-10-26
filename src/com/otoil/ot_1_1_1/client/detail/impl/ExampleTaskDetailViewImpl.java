package com.otoil.ot_1_1_1.client.detail.impl;


import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailView;
import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.i18n.ExampleTaskConstant;


public class ExampleTaskDetailViewImpl extends VerticalPanel
        implements ExampleTaskDetailView
{

    private Label titleDetailsTable = new Label();
    private VerticalPanel verticalPanelForDetail = new VerticalPanel();
    private final static ExampleTaskConstant CONSTANTS = ExampleTaskConstant.INSTANCE;
    private FlexTable detailTable = new FlexTable();

    @Override
    public Widget asWidget()
    {
        return this;
    }

    public ExampleTaskDetailViewImpl()
    {
        onLoad();
    }

    public void onLoad()
    {
        verticalPanelForDetail.addStyleName("detailsTable");

        titleDetailsTable.addStyleName("title");

        detailTable.addStyleName("detailsTable");

        detailTable.getColumnFormatter().addStyleName(0, "firstDetailColumn");
        detailTable.getColumnFormatter().addStyleName(1, "secondDetailColumn");

        titleDetailsTable.setText(CONSTANTS.detail());

        this.add(titleDetailsTable);
        this.add(detailTable);
    }

    @Override
    public void addDataToDetailTable(List<AttributeNameBean> list)
    {

        detailTable.removeAllRows();
        detailTable.setText(0, 0, CONSTANTS.attributesName());
        detailTable.setText(0, 1, CONSTANTS.values());

        for (int i = 0; i < list.size(); i++)
        {

            int row = detailTable.getRowCount();

            detailTable.setWidget(row, 0,
                new Label(list.get(i).getName()));
            detailTable.setWidget(row, 1,
                new Label(list.get(i).getValueString()));
        }

    }

}
