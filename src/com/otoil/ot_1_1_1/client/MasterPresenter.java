package com.otoil.ot_1_1_1.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;

import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@RequiredArgsConstructor
public class MasterPresenter
{
    private final ExampleModel model;
    private final ExampleView view;
   // private ClickEvent event;

    public static final int BUTTON_INDEX = 4;
    public static final int ID_INDEX = 5;
    public static final int NAME_LABEL_INDEX = 0;
    public static final int ORDER_LABEL_INDEX = 1;

    public void setDocumentToMasterTable()
    {
        model.getDocumentsCard()
            .subscribe(list -> view.addDataToDocCardTable(list));
    }

    public int getColumnIndex(ClickEvent event)
    {
        return view.getTable().getCellForEvent(event).getCellIndex();
    }

    public String getId(ClickEvent event)
    {
        Label idLabel = (Label) getWidget(getRowIndex(event), ID_INDEX);
        return idLabel.getText();
    }

    public void doEdit(ClickEvent event)
    {
        Widget button = (Button) getWidget(getRowIndex(event), BUTTON_INDEX);
        button.setVisible(true);

        swapLabelToTextBox(getRowIndex(event), getColumnIndex(event), getWidget(event),
            view.getTable());
    }

    public void doSave(ClickEvent event)
    {
        Widget button = (Button) getWidget(getRowIndex(event), BUTTON_INDEX);

        String firstElementValue = getElementValue(view.getTable(),
            getRowIndex(event), NAME_LABEL_INDEX);
        String secondElementValue = getElementValue(view.getTable(),
            getRowIndex(event), ORDER_LABEL_INDEX);

        if (secondElementValue.trim().matches("\\d+"))
        {
            swapTextBoxToLabel(getRowIndex(event), view.getTable(),
                firstElementValue, secondElementValue);

            saveDocument(getId(event), firstElementValue, secondElementValue);

            button.setVisible(false);
        }
        else
        {
            Window.alert("Invalid value. Please enter an integer");
        }
    }

    private int getRowIndex(ClickEvent event)
    {
        return view.getTable().getCellForEvent(event).getRowIndex();
    }

    private Widget getWidget(ClickEvent event)
    {
        return view.getTable().getWidget(getRowIndex(event), getColumnIndex(event));
    }

    private Widget getWidget(int rowIndex, int columnIndex)
    {
        return view.getTable().getWidget(rowIndex, columnIndex);
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

        table.setWidget(rowIndex, NAME_LABEL_INDEX,
            new Label(firstElementValue));
        table.setWidget(rowIndex, ORDER_LABEL_INDEX,
            new Label(secondElementValue.trim()));
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
            firstElementValue, secondElementValue.trim())).subscribe();
    }
}
