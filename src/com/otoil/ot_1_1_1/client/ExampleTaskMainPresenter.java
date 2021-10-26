package com.otoil.ot_1_1_1.client;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailModel;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailPresenter;
import com.otoil.ot_1_1_1.client.detail.ExampleTaskDetailView;
import com.otoil.ot_1_1_1.client.detail.impl.ExampleTaskDetailModelImpl;
import com.otoil.ot_1_1_1.client.detail.impl.ExampleTaskDetailViewImpl;
import com.otoil.ot_1_1_1.client.event.GetDetailIdEvent;
import com.otoil.ot_1_1_1.client.event.SendDetailIdEvent;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterModel;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterView;
import com.otoil.ot_1_1_1.client.master.ExampleTaskMasterPresenter;
import com.otoil.ot_1_1_1.client.master.impl.ExampleTaskMasterModelImpl;
import com.otoil.ot_1_1_1.client.master.impl.ExampleTaskMasterViewImpl;

import io.reactivex.disposables.CompositeDisposable;
import ru.ot.gwt.utils.client.rx.RxGwtEvent;
import ru.ot.mvp.client.presenters.AbstractPresenter;


public class ExampleTaskMainPresenter
        extends AbstractPresenter<ExampleTaskMainModel, ExampleTaskMainView>
{

    private ExampleTaskMasterPresenter masterPresenter;
    private ExampleTaskDetailPresenter detailPresenter;
    private ExampleTaskMasterView masterView;
    private ExampleTaskMasterModel masterModel;
    private ExampleTaskDetailModel detailModel;
    private ExampleTaskDetailView detailView;

    private final CompositeDisposable subscriptions = new CompositeDisposable();

    public ExampleTaskMainPresenter(ExampleTaskMainModel model,
        ExampleTaskMainView view)
    {
        super(model, view);

        masterModel = new ExampleTaskMasterModelImpl();
        masterView = new ExampleTaskMasterViewImpl();
        masterPresenter = new ExampleTaskMasterPresenter(masterModel,
            masterView);
        detailModel = new ExampleTaskDetailModelImpl();
        detailView = new ExampleTaskDetailViewImpl();
        detailPresenter = new ExampleTaskDetailPresenter(detailModel,
            detailView);
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus)
    {
        super.start(panel, eventBus);

        detailPresenter.start(view.getDetailPanel(), eventBus);
        masterPresenter.start(view.getMasterPanel(), eventBus);

    }

    @Override
    public void bind()
    {
        subscriptions.add(
            RxGwtEvent.observeOn(eventBus, GetDetailIdEvent.TYPE).subscribe(
                x -> eventBus.fireEvent(new SendDetailIdEvent(x.getId()))));
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
