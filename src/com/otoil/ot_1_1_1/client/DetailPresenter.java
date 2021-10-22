package com.otoil.ot_1_1_1.client;


import lombok.AllArgsConstructor;


@AllArgsConstructor
public class DetailPresenter
{
    private ExampleModel model;
    private ExampleView view;

    public void setDetails(String id)
    {
        model.getObjectAttribute(id)
            .subscribe(list -> view.addDataToDetailTable(list));
    }
}
