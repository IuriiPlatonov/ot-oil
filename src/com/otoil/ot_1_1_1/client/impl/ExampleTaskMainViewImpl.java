package com.otoil.ot_1_1_1.client.impl;


import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.ExampleTaskMainView;


public class ExampleTaskMainViewImpl implements ExampleTaskMainView
{

    private SimplePanel masterPanel = new SimplePanel();
    private SimplePanel detailPanel = new SimplePanel();
    private HorizontalPanel horizontalPanel = new HorizontalPanel();

    public ExampleTaskMainViewImpl()
    {
        init();
    }

    private void init()
    {
        horizontalPanel.addStyleName("horizontalPanel");
        horizontalPanel.add(masterPanel);
        horizontalPanel.add(detailPanel);

        RootPanel.get("resultTable").add(horizontalPanel);
    }

    @Override
    public AcceptsOneWidget getMasterPanel()
    {
        return masterPanel;
    }

    @Override
    public Widget asWidget()
    {
        return null;
    }

    @Override
    public AcceptsOneWidget getDetailPanel()
    {
        return detailPanel;
    }

}
