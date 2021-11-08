package com.otoil.ot_1_1_1.client.master.impl;


import java.util.Date;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.i18n.ExampleTaskConstant;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterView;

import io.reactivex.subjects.PublishSubject;
import ru.ot.gwt.utils.client.BeanProperty;
import ru.ot.wevelns.client.NSBlock;
import ru.ot.wevelns.client.NSTreeNodeDataGrid;
import ru.ot.wevelns.client.cell.datebox.DateboxCell;
import ru.ot.wevelns.client.cellview.columns.AbstractDateColumn;
import ru.ot.wevelns.client.cellview.columns.StringColumn;
import ru.ot.wevelns.client.tree.DefaultTreeNode;
import ru.ot.wevelns.client.tree.TreeDataDisplay;


public class ExampleTaskMasterViewImpl extends VerticalPanel
        implements ExampleTaskMasterView, SelectionChangeEvent.Handler
{

    private static ExampleTaskMasterViewImpl INSTANCE;

    public static final BeanProperty<ResponseDocumentCardBean, String> name = BeanProperty
        .create(ResponseDocumentCardBean::getDocName,
            ResponseDocumentCardBean::setDocName);

    public static final BeanProperty<ResponseDocumentCardBean, String> orderedNumber = BeanProperty
        .create(ResponseDocumentCardBean::getOrderNumber,
            ResponseDocumentCardBean::setOrderNumber);

    public static final BeanProperty<ResponseDocumentCardBean, Date> changeDate = BeanProperty
        .create(ResponseDocumentCardBean::getChangeDate,
            ResponseDocumentCardBean::setChangeDate);

    public static final BeanProperty<ResponseDocumentCardBean, String> image = BeanProperty
        .create(ResponseDocumentCardBean::getBinaryData,
            ResponseDocumentCardBean::setBinaryData);

    public static final BeanProperty<ResponseDocumentCardBean, String> dcmcrdId = BeanProperty
        .create(ResponseDocumentCardBean::getDcmcrdId,
            ResponseDocumentCardBean::setDcmcrdId);

    private final static ExampleTaskConstant CONSTANTS = ExampleTaskConstant.INSTANCE;

    private NSTreeNodeDataGrid<ResponseDocumentCardBean> treeTable = new NSTreeNodeDataGrid<ResponseDocumentCardBean>();;

    private PublishSubject<String> treeDetailSubject = PublishSubject.create();
    private PublishSubject<RequestDocumentCardBean> treeSaveSubject = PublishSubject
        .create();

    private NoSelectionModel<DefaultTreeNode<ResponseDocumentCardBean>> selectionModel = new NoSelectionModel<>();

    private int columnNumber = 0;

    private ExampleTaskMasterViewImpl()
    {
        init();
    }

    public static ExampleTaskMasterView getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new ExampleTaskMasterViewImpl();
        }
        return INSTANCE;
    }

    public void init()
    {
        initTreeNodeDataGrid();

        NSBlock block = new NSBlock(CONSTANTS.docCardTable());
        block.setSize("600px", "1000px");
        block.setWidget(treeTable);

        this.add(block);

    }

    private void initTreeNodeDataGrid()
    {

        treeTable.setEditing(true);
        treeTable.setSize("100%", "100%");
        treeTable.setFocus(true);
        
        
        addStringColumn(name, CONSTANTS.name(), 200, true);
        addStringColumn(orderedNumber, CONSTANTS.orderedNumber(), 60, true);
        addDateColumn(changeDate, CONSTANTS.changeDate(), 100, true);
        addImageColumn(image, CONSTANTS.image(), 45, true);
        addStringButtonColumn(CONSTANTS.detail(), CONSTANTS.detail(), 90, true);

        treeTable.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(this);
    }

    private void addStringColumn(
        BeanProperty<ResponseDocumentCardBean, String> property, String title,
        int width, boolean visible)
    {
        Column<DefaultTreeNode<ResponseDocumentCardBean>, String> column = new StringColumn<DefaultTreeNode<ResponseDocumentCardBean>>()
        {
            @Override
            public String getValue(
                DefaultTreeNode<ResponseDocumentCardBean> object)
            {
                return property.get(object.getValue());
            }
        };
        column.setSortable(true);
        column.setFieldUpdater((index, node, value) -> {
            property.set(node.getValue(), value);
            treeSaveSubject.onNext(new RequestDocumentCardBean(
                node.getValue().getDcmcrdId(), node.getValue().getDocName(),
                node.getValue().getOrderNumber()));
        });

        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width + "px");
        treeTable.setColumnVisible(column, visible);

    }

    private void addImageColumn(
        BeanProperty<ResponseDocumentCardBean, String> property, String title,
        int width, boolean visible)
    {

        Column<DefaultTreeNode<ResponseDocumentCardBean>, String> column = new Column<DefaultTreeNode<ResponseDocumentCardBean>, String>(
            new ImageCell())
        {

            @Override
            public void render(Context context,
                DefaultTreeNode<ResponseDocumentCardBean> object,
                SafeHtmlBuilder sb)
            {
                Image img = new Image(property.get(object.getValue()));

                SafeHtml imageHtml = SafeHtmlUtils
                    .fromTrustedString(img.toString());
                sb.append(imageHtml);

                sb.toSafeHtml();
            }

            @Override
            public String getValue(
                DefaultTreeNode<ResponseDocumentCardBean> object)
            {
                if (object == null)
                    return "";
                else
                    return property.get(object.getValue());
            }
        };
        column.setSortable(true);
        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width, Unit.PX);
        treeTable.setColumnVisible(column, visible);

    }

    private void addDateColumn(
        BeanProperty<ResponseDocumentCardBean, Date> property, String title,
        int width, boolean visible)
    {
        AbstractDateColumn<DefaultTreeNode<ResponseDocumentCardBean>, DateboxCell> column = new AbstractDateColumn<DefaultTreeNode<ResponseDocumentCardBean>, DateboxCell>(
            new DateboxCell())
        {
            @Override
            public Date getValue(
                DefaultTreeNode<ResponseDocumentCardBean> object)
            {
                return property.get(object.getValue());
            }
        };
        column.setSortable(true);
        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width + "px");
        treeTable.setColumnVisible(column, visible);
    }

    private void addStringButtonColumn(String buttonName, String title,
        int width, boolean visible)
    {
        Column<DefaultTreeNode<ResponseDocumentCardBean>, String> column = new StringColumn<DefaultTreeNode<ResponseDocumentCardBean>>()
        {
            @Override
            public String getValue(
                DefaultTreeNode<ResponseDocumentCardBean> object)
            {
                return buttonName;
            }
        };
        column.setSortable(true);
        treeTable.addColumn(column, title);
        treeTable.setColumnWidth(column, width, Unit.PX);
        treeTable.setColumnVisible(column, visible);
    }

    @Override
    public TreeDataDisplay<DefaultTreeNode<ResponseDocumentCardBean>> getTree()
    {
        return treeTable;
    }

    @Override
    public PublishSubject<String> getTreeDetailSubject()
    {
        return treeDetailSubject;
    }

    @Override
    public Widget asWidget()
    {
        return this;
    }

    @Override
    public void onSelectionChange(SelectionChangeEvent event)
    {
        treeTable.addCellPreviewHandler(e -> columnNumber = e.getColumn());

        if (columnNumber == 4)
        {
            treeDetailSubject.onNext(selectionModel.getLastSelectedObject()
                .getValue().getDcmcrdId());
        }
    }

    @Override
    public PublishSubject<RequestDocumentCardBean> getTreeSaveSubject()
    {
        return treeSaveSubject;
    }

}
