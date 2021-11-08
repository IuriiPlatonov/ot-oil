
package com.otoil.ot_1_1_1.client.master;


import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.otoil.ot_1_1_1.client.ExampleTaskClientFactory;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.event.GetDetailIdEvent;

import lombok.Setter;
import ru.ot.mvp.client.presenters.AbstractPresenter;
import ru.ot.ot_132_5_0030.client.rest.helpers.tree.FetchedTreeProviderAdapter;
import ru.ot.wevelns.client.tree.DefaultTreeNode;


@Setter
public class ExampleTaskMasterPresenter
        extends AbstractPresenter<ExampleTaskMasterModel, ExampleTaskMasterView>
        implements SelectionChangeEvent.Handler
{

    private FetchedTreeProviderAdapter<ResponseDocumentCardBean> provider = new FetchedTreeProviderAdapter<>();
    private NoSelectionModel<DefaultTreeNode<ResponseDocumentCardBean>> selectionModel = new NoSelectionModel<>();

    public ExampleTaskMasterPresenter(ExampleTaskClientFactory factory)
    {
        super(factory.getMasterModel(), factory.getMasterView());
        provider.addDataDisplay(view.getTree());
        view.getTree().setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(this);
    }

    @Override
    public void bind()
    {
        Defaults.setServiceRoot(
            com.google.gwt.core.client.GWT.getHostPageBaseURL());

        model.loadTree().subscribe((treeData) -> {
            provider.setTree(treeData);
            provider.refresh();
        });

        view.getTreeSaveSubject().subscribe(this::saveDocument);
    }

    private void saveDocument(RequestDocumentCardBean cardBean)
    {
        model.saveDocumentCard(cardBean).subscribe();
    }

    private void createGetDetailIdEvent(String id)
    {
        eventBus.fireEvent(new GetDetailIdEvent(id));
    }

    @Override
    public void onSelectionChange(SelectionChangeEvent event)
    {
        createGetDetailIdEvent(
            selectionModel.getLastSelectedObject().getValue().getId());
    }

}
