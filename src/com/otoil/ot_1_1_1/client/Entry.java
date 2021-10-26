package com.otoil.ot_1_1_1.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.otoil.ot_1_1_1.client.impl.ExampleTaskMainModelImpl;
import com.otoil.ot_1_1_1.client.impl.ExampleTaskMainViewImpl;


public class Entry implements EntryPoint
{
    private ExampleTaskMainPresenter presenter ;
    private ExampleTaskMainModel model;
    private ExampleTaskMainView view;

    @Override
    public void onModuleLoad()
    {
        EventBus eventBus = new SimpleEventBus();
        
        model = new ExampleTaskMainModelImpl();
        view = new ExampleTaskMainViewImpl();
        
        presenter = new ExampleTaskMainPresenter(model, view);
        presenter.start(null , eventBus);

    }
}
