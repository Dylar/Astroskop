package de.bitb.astroskop.ui.main.home;


import javax.inject.Inject;

import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.utils.CommonUtils;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.ui.base.BasePresenter;

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
