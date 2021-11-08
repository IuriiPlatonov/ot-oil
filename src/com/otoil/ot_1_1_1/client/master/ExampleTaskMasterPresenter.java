
package com.otoil.ot_1_1_1.client.master;


import org.fusesource.restygwt.client.Defaults;

import com.otoil.ot_1_1_1.client.ExampleTaskClientFactory;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.dto.ResponseDocumentCardBean;
import com.otoil.ot_1_1_1.client.event.GetDetailIdEvent;

import lombok.Setter;
import ru.ot.mvp.client.presenters.AbstractPresenter;
import ru.ot.ot_132_5_0030.client.rest.helpers.tree.FetchedTreeProviderAdapter;


@Setter
public class ExampleTaskMasterPresenter
        extends AbstractPresenter<ExampleTaskMasterModel, ExampleTaskMasterView>
{

    private FetchedTreeProviderAdapter<ResponseDocumentCardBean> provider = new FetchedTreeProviderAdapter<>();
  
    public ExampleTaskMasterPresenter(ExampleTaskClientFactory factory)
    {
        super(factory.getMasterModel(), factory.getMasterView());
    }

    @Override
    public void bind()
    {
        provider.addDataDisplay(view.getTree());


        Defaults.setServiceRoot(
            com.google.gwt.core.client.GWT.getHostPageBaseURL());

     
        model.loadTree().subscribe((treeData) -> {
            provider.setTree(treeData);
            provider.refresh();
        });
        view.getTreeDetailSubject().subscribe(this::createGetDetailIdEvent);
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

}
