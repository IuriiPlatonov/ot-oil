package com.otoil.ot_1_1_1.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.otoil.ot_1_1_1.client.impl.ExampleViewImpl;


public class Main implements EntryPoint
{

    @Override
    public void onModuleLoad()
    {
        Presenter presenter = new Presenter(GWT.create(ExampleModel.class),
            new ExampleViewImpl());
        presenter.init();

    }
}
