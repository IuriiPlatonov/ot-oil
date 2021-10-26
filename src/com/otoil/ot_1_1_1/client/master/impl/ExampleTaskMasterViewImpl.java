package com.otoil.ot_1_1_1.client.master.impl;


import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.i18n.ExampleTaskConstant;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterView;

import io.reactivex.subjects.PublishSubject;


public class ExampleTaskMasterViewImpl extends VerticalPanel
        implements ExampleTaskMasterView
{

    private static final int SAVE_BUTTON_INDEX = 5;
    private static final int ID_INDEX = 6;
    private static final int NAME_LABEL_INDEX = 0;
    private static final int ORDER_LABEL_INDEX = 1;
    private static final int DETAIL_BUTTON_INDEX = 4;

    private final static ExampleTaskConstant CONSTANTS = ExampleTaskConstant.INSTANCE;
    private Label titleDocumentCardsTable = new Label();
    private FlexTable documentCardTable = new FlexTable();

    private PublishSubject<RequestDocumentCardBean> requestDocumentCardBeanSubject = PublishSubject
        .create();
    private PublishSubject<String> detailIdSubject = PublishSubject.create();

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

        documentCardTable.addClickHandler(new MasterTableClickHandler());

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
    public PublishSubject<String> getDetailId()
    {
        return detailIdSubject;
    }

    @Override
    public PublishSubject<RequestDocumentCardBean> getSaveSubject()
    {
        return requestDocumentCardBeanSubject;
    }

    @Override
    public Widget asWidget()
    {
        return this;
    }
    
    public class MasterTableClickHandler implements ClickHandler
    {
        @Override
        public void onClick(ClickEvent event)
        {
            switch (getColumnIndex(event))
            {
                case NAME_LABEL_INDEX:
                    doEdit(event);
                    break;
                case ORDER_LABEL_INDEX:
                    doEdit(event);
                    break;
                case DETAIL_BUTTON_INDEX:
                    detailIdSubject.onNext(getId(event));
                    break;
                case SAVE_BUTTON_INDEX:
                    doSave(event);
                    break;
            }
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

            String firstElementValue = getElementValue(documentCardTable,
                getRowIndex(event), NAME_LABEL_INDEX);
            String secondElementValue = getElementValue(documentCardTable,
                getRowIndex(event), ORDER_LABEL_INDEX);

            if (secondElementValue.trim().matches("\\d+"))
            {
                swapTextBoxToLabel(getRowIndex(event), documentCardTable,
                    firstElementValue, secondElementValue);
                requestDocumentCardBeanSubject
                    .onNext(new RequestDocumentCardBean(getId(event),
                        firstElementValue, secondElementValue));

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
            return documentCardTable.getCellForEvent(event).getRowIndex();
        }

        private int getColumnIndex(ClickEvent event)
        {
            return documentCardTable.getCellForEvent(event).getCellIndex();
        }

        private Widget getWidget(ClickEvent event)
        {
            return documentCardTable.getWidget(getRowIndex(event),
                getColumnIndex(event));
        }

        private Widget getWidget(int rowIndex, int columnIndex)
        {
            return documentCardTable.getWidget(rowIndex, columnIndex);
        }

        private void swapLabelToTextBox(int rowIndex, int columnIndex,
            Widget widget)
        {

            Label label = (Label) widget;
            String text = label.getText();
            TextBox firstColTextBox = new TextBox();
            firstColTextBox.setText(text);
            documentCardTable.setWidget(rowIndex, columnIndex, firstColTextBox);
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

            if (table.getWidget(rowIndex, columnIndex)
                .getClass() == Label.class)
            {
                labelElement = (Label) (table.getWidget(rowIndex, columnIndex));
                return labelElement.getText();
            }
            else
            {
                textBoxElement = (TextBox) (table.getWidget(rowIndex,
                    columnIndex));
                return textBoxElement.getText();
            }
        }

    }

}
