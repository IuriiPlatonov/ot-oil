package com.otoil.ot_1_1_1.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.otoil.ot_1_1_1.client.impl.ExampleTaskClientFactoryImpl;


public class Entry implements EntryPoint
{
    private ExampleTaskMainPresenter presenter;

    @Override
    public void onModuleLoad()
    {
        EventBus eventBus = new SimpleEventBus();

        presenter = new ExampleTaskMainPresenter(new ExampleTaskClientFactoryImpl());
        presenter.start(null, eventBus);

    }
}
