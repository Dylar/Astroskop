package de.bitb.astroskop.ui.main.home;


import javax.inject.Inject;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.ui.base.BasePresenter;
import de.bitb.astroskop.utils.CommonUtils;

public class HomeViewPresenter extends BasePresenter<IHomeView> implements IInjection {

    @Inject
    protected CommonUtils commonUtils;

    public HomeViewPresenter(AstroApplication application, IHomeView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void jobOfferClicked() {
    }

}
