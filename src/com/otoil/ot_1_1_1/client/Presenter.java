package com.otoil.ot_1_1_1.client;


import java.util.List;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.dto.AttributeNameBean;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.impl.ExampleViewImpl;


public class Presenter
{

    private ExampleModel model;
    private ExampleViewImpl view;

    public Presenter(ExampleModel model, ExampleViewImpl view)
    {
        this.model = model;
        this.view = view;
    }

    public void init()
    {

        Defaults.setServiceRoot(
            com.google.gwt.core.client.GWT.getHostPageBaseURL());

        view.createViews();

        model.getDocumentsCard(
            new MethodCallback<List<ResponseDocumentCardBean>>()
            {

                @Override
                public void onSuccess(Method method,
                    List<ResponseDocumentCardBean> response)
                {
                    view.addDataToDocCardTable(response);

                }

                @Override
                public void onFailure(Method method, Throwable exception)
                {
                    Window.alert(exception.toString());
                }
            });
        


        view.getTable().addClickHandler(event -> {

            FlexTable table = view.getTable();
            
            int columnIndex = table.getCellForEvent(event).getCellIndex();
            int rowIndex = table.getCellForEvent(event).getRowIndex();

            Widget widget = table.getWidget(rowIndex, columnIndex);

            Label idLabel = (Label) table.getWidget(rowIndex, 5);
            String id = idLabel.getText();
            
            setDetails(id);
            
            boolean isWidgetAllowedToSwap = widget.getClass() == Label.class
                && columnIndex != 2;

            if (isWidgetAllowedToSwap)
            {

                swapLabelToTextBox(rowIndex, columnIndex, widget, table);

                Widget button = (Button) table.getWidget(rowIndex, 4);
                button.setVisible(true);
            }

            boolean isWidgetButton = columnIndex == 4;

            if (isWidgetButton)
            {
                Widget button = (Button) table.getWidget(rowIndex, 4);

                String firstElementValue = getElementValue(table, rowIndex, 0);
                String secondElementValue = getElementValue(table, rowIndex, 1);

                if (secondElementValue.trim().matches("\\d+"))
                {
                    swapTextBoxToLabel(rowIndex, table, firstElementValue,
                        secondElementValue);

                    saveDocument(id, firstElementValue, secondElementValue);

                    button.setVisible(false);
                }
                else
                {
                    Window.alert("Invalid value. Please enter an integer");
                }
            }

        });

    }

    private void setDetails(String id) {
        model.getObjectAttribute(id, new MethodCallback<List<AttributeNameBean>>()
        {

            @Override
            public void onFailure(Method method, Throwable exception)
            {
                Window.alert("Call details failed to id: "+ id + " . Exception: " + exception.getMessage());

            }

            @Override
            public void onSuccess(Method method,
                List<AttributeNameBean> response)
            {
                view.addDataToDetailTable(response);
            }

        });
    }
    
    private void swapLabelToTextBox(int rowIndex, int columnIndex,
        Widget widget, FlexTable table)
    {

        Label label = (Label) widget;
        String text = label.getText();
        TextBox firstColTextBox = new TextBox();
        firstColTextBox.setText(text);
        table.setWidget(rowIndex, columnIndex, firstColTextBox);
    }

    private void swapTextBoxToLabel(int rowIndex, FlexTable table,
        String firstElementValue, String secondElementValue)
    {

        table.setWidget(rowIndex, 0, new Label(firstElementValue));
        table.setWidget(rowIndex, 1, new Label(secondElementValue.trim()));
    }

    private String getElementValue(FlexTable table, int rowIndex,
        int columnIndex)
    {
        TextBox textBoxElement;
        Label labelElement;

        if (table.getWidget(rowIndex, columnIndex).getClass() == Label.class)
        {
            labelElement = (Label) (table.getWidget(rowIndex, columnIndex));
            return labelElement.getText();
        }
        else
        {
            textBoxElement = (TextBox) (table.getWidget(rowIndex, columnIndex));
            return textBoxElement.getText();
        }
    }

    private void saveDocument(String id, String firstElementValue,
        String secondElementValue)
    {
        model.saveDocumentCard(new RequestDocumentCardBean(id,
            firstElementValue, secondElementValue.trim()),
            new MethodCallback<Boolean>()
            {
                @Override
                public void onSuccess(Method method, Boolean response)
                {
                    Window.alert("Save doc");
                }

                @Override
                public void onFailure(Method method, Throwable exception)
                {
                    Window.alert(exception.toString());
                }
            });
    }
}
