package com.otoil.ot_1_1_1.client.master;


import org.fusesource.restygwt.client.Defaults;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.otoil.ot_1_1_1.client.dto.RequestDocumentCardBean;
import com.otoil.ot_1_1_1.client.event.GetDetailIdEvent;

import lombok.Setter;
import ru.ot.mvp.client.presenters.AbstractPresenter;


@Setter
public class ExampleTaskMasterPresenter
        extends AbstractPresenter<ExampleTaskMasterModel, ExampleTaskMasterView>
{

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

        model.getDocumentsCard().subscribe(view::addDataToDocCardTable);
        view.getSaveSubject().subscribe(this::saveDocument);
        view.getDetailId().subscribe(this::createGetDetailIdEvent);

    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus)
    {
        super.start(panel, eventBus);
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
