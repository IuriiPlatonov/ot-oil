package com.otoil.ot_1_1_1.client;


import org.fusesource.restygwt.client.Defaults;
import com.otoil.ot_1_1_1.client.impl.ExampleViewImpl;


public class Presenter
{
    private ExampleViewImpl view;
    private DetailPresenter detail;
    private MasterPresenter master;

    public Presenter(ExampleModel model, ExampleViewImpl view)
    {
        this.view = view;
        detail = new DetailPresenter(model, view);
        master = new MasterPresenter(model, view);
    }

    public void init()
    {

        Defaults.setServiceRoot(
            com.google.gwt.core.client.GWT.getHostPageBaseURL());

        view.createViews();

        master.setDocumentToMasterTable();

        view.getTable().addClickHandler(event -> {

            detail.setDetails(master.getId(event));

            switch (master.getColumnIndex(event))
            {
                case MasterPresenter.NAME_LABEL_INDEX:
                    master.doEdit(event);
                    break;
                case MasterPresenter.ORDER_LABEL_INDEX:
                    master.doEdit(event);
                    break;
                case MasterPresenter.BUTTON_INDEX:
                    master.doSave(event);
                    break;
            }

        });
    }
}
