package de.bornholdtlee.snh.ui.main.home;


import javax.inject.Inject;

import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.ui.base.BasePresenter;
import de.bornholdtlee.snh.utils.CommonUtils;

public class HomeViewPresenter extends BasePresenter<IHomeView> implements IInjection {

    @Inject
    protected CommonUtils commonUtils;

    public HomeViewPresenter(SNHApplication application, IHomeView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void jobOfferClicked() {
        commonUtils.openExternUrl(getContext(), getContext().getString(R.string.url_job_offer));
    }

}
