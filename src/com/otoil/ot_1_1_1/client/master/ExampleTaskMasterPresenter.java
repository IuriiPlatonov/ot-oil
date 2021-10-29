
package com.otoil.ot_1_1_1.client.master;


import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.event.shared.EventBus;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.SingleSelectionModel;
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
{

    private FetchedTreeProviderAdapter<ResponseDocumentCardBean> provider = new FetchedTreeProviderAdapter<>();
    private final SingleSelectionModel<DefaultTreeNode<ResponseDocumentCardBean>> selectionModel = new SingleSelectionModel<>();
    
    public ExampleTaskMasterPresenter(ExampleTaskClientFactory factory)
    {
        super(factory.getMasterModel(), factory.getMasterView());
    }

    @Override
    public void bind()
    {
        Defaults.setServiceRoot(
            com.google.gwt.core.client.GWT.getHostPageBaseURL());

        model.load().subscribe((treeData) -> {
            provider.setTree(treeData);
            provider.refresh();
        });
        model.getDocumentsCard().subscribe(view::addDataToDocCardTable);
        view.getSaveSubject().subscribe(this::saveDocument);
        view.getDetailId().subscribe(this::createGetDetailIdEvent);

    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus)
    {
        super.start(panel, eventBus);
        provider.addDataDisplay(view.getTree());
        view.getTree().setSelectionModel(selectionModel);
    }
   
    private void saveDocument(RequestDocumentCardBean cardBean)
    {
        model.saveDocumentCard(cardBean).subscribe();
    }

    private void createGetDetailIdEvent(String id)
    {
        eventBus.fireEvent(new GetDetailIdEvent(id));
    }

}
