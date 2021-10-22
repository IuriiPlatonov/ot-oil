package com.otoil.ot_1_1_1.client;


import com.google.gwt.core.client.EntryPoint;
import com.otoil.ot_1_1_1.client.impl.ExampleModelImpl;
import com.otoil.ot_1_1_1.client.impl.ExampleViewImpl;


public class Main implements EntryPoint
{

    @Override
    public void onModuleLoad()
    {
        Presenter presenter = new Presenter(new ExampleModelImpl(),
            new ExampleViewImpl());
        presenter.init();

    }
}
