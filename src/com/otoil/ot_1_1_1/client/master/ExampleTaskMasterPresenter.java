package com.otoil.ot_1_1_1.client.master;


import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.event.GetDetailIdEvent;

import lombok.Setter;
import ru.ot.mvp.client.presenters.AbstractPresenter;


@Setter
public class ExampleTaskMasterPresenter
        extends AbstractPresenter<ExampleTaskMasterModel, ExampleTaskMasterView>
{

    private static final int SAVE_BUTTON_INDEX = 5;
    private static final int ID_INDEX = 6;
    private static final int NAME_LABEL_INDEX = 0;
    private static final int ORDER_LABEL_INDEX = 1;
    private static final int DETAIL_BUTTON_INDEX = 4;

    private FlexTable table;

    public ExampleTaskMasterPresenter(ExampleTaskMasterModel model,
        ExampleTaskMasterView view)
    {
        super(model, view);

    }

    @Override
    public void bind()
    {
        Defaults.setServiceRoot(
            com.google.gwt.core.client.GWT.getHostPageBaseURL());

        model.getDocumentsCard()
            .subscribe(list -> view.addDataToDocCardTable(list));

        view.getTableClick().addClickHandler(event -> {
            FlexTable table = (FlexTable) event.getSource();

            this.table = table;

            switch (getColumnIndex(event))
            {
                case NAME_LABEL_INDEX:
                    doEdit(event);
                    break;
                case ORDER_LABEL_INDEX:
                    doEdit(event);
                    break;
                case DETAIL_BUTTON_INDEX:
                    eventBus.fireEvent(new GetDetailIdEvent(getId(event)));;
                    break;
                case SAVE_BUTTON_INDEX:
                    doSave(event);
                    break;
            }
        });
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus)
    {
        super.start(panel, eventBus);
    }

    private void doEdit(ClickEvent event)
    {
        Widget button = (Button) getWidget(getRowIndex(event),
            SAVE_BUTTON_INDEX);
        button.setVisible(true);

        swapLabelToTextBox(getRowIndex(event), getColumnIndex(event),
            getWidget(event));
    }

    private void doSave(ClickEvent event)
    {
        Widget button = (Button) getWidget(getRowIndex(event),
            SAVE_BUTTON_INDEX);

        String firstElementValue = getElementValue(table, getRowIndex(event),
            NAME_LABEL_INDEX);
        String secondElementValue = getElementValue(table, getRowIndex(event),
            ORDER_LABEL_INDEX);

        if (secondElementValue.trim().matches("\\d+"))
        {
            swapTextBoxToLabel(getRowIndex(event), table, firstElementValue,
                secondElementValue);

            saveDocument(getId(event), firstElementValue, secondElementValue);

            button.setVisible(false);
        }
        else
        {
            Window.alert("Invalid value. Please enter an integer");
        }
    }

    private String getId(ClickEvent event)
    {
        Label idLabel = (Label) getWidget(getRowIndex(event), ID_INDEX);
        return idLabel.getText();
    }

    private int getRowIndex(ClickEvent event)
    {
        return table.getCellForEvent(event).getRowIndex();
    }

    private int getColumnIndex(ClickEvent event)
    {
        return table.getCellForEvent(event).getCellIndex();
    }

    private Widget getWidget(ClickEvent event)
    {
        return table.getWidget(getRowIndex(event), getColumnIndex(event));
    }

    private Widget getWidget(int rowIndex, int columnIndex)
    {
        return table.getWidget(rowIndex, columnIndex);
    }

    private void swapLabelToTextBox(int rowIndex, int columnIndex,
        Widget widget)
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
