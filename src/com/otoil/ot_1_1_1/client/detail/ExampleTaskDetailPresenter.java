package com.otoil.ot_1_1_1.client.detail;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.otoil.ot_1_1_1.client.event.SendDetailIdEvent;

import io.reactivex.disposables.CompositeDisposable;
import ru.ot.gwt.utils.client.rx.RxGwtEvent;
import ru.ot.mvp.client.presenters.AbstractPresenter;


public class ExampleTaskDetailPresenter
        extends AbstractPresenter<ExampleTaskDetailModel, ExampleTaskDetailView>
{

    private ExampleTaskDetailModel model;
    private ExampleTaskDetailView view;
    private EventBus eventBus;

    private final CompositeDisposable subscriptions = new CompositeDisposable();

    public ExampleTaskDetailPresenter(ExampleTaskDetailModel model,
        ExampleTaskDetailView view)
    {
        super(model, view);

    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus)
    {
        super.start(panel, eventBus);
        this.eventBus = eventBus;
        bind();
    }

    @Override
    public void bind()
    {
        subscriptions.add(RxGwtEvent.observeOn(eventBus, SendDetailIdEvent.TYPE)
            .subscribe(x -> model.getObjectAttribute(x.getId())
                .subscribe(list -> view.addDataToDetailTable(list))));

    }

    @Override
    public void onCancel()
    {
        subscriptions.clear();
        super.onCancel();
    }

    @Override
    public void onStop()
    {
        subscriptions.clear();
        super.onStop();
    }
}
