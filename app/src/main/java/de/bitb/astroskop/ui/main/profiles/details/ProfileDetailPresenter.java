package de.bitb.astroskop.ui.main.profiles.details;

import javax.inject.Inject;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.controller.ProfileController;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.ui.base.BasePresenter;

public class ProfileDetailPresenter extends BasePresenter<IProfileDetailView> implements IInjection {

    @Inject
    protected ProfileController profileController;

    private Profile profile;

    public ProfileDetailPresenter(AstroApplication application, IProfileDetailView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void onCreate(String uuid) {
        profile = profileController.getByUUID(uuid);
        getView().setToolbarTitle(profile.getName());
        getView().initProfile(profile);
        refreshView();
    }

    private void refreshView() {
        getView().refreshView();
    }

}
