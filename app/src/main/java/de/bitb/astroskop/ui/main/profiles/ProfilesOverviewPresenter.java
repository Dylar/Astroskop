package de.bitb.astroskop.ui.main.profiles;

import java.util.List;

import javax.inject.Inject;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.controller.ProfileController;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.ui.base.BasePresenter;
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import de.bitb.astroskop.ui.base.adapter.IAdapterView;

public class ProfilesOverviewPresenter extends BasePresenter<IProfilesOverviewView> implements IInjection, AdapterPresenter<Profile> {

    @Inject
    protected ProfileController profileController;

    private List<Profile> profiles;

    public ProfilesOverviewPresenter(AstroApplication application, IProfilesOverviewView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void onCreate() {
        refreshView();
    }

    private void refreshView() {
        profiles = profileController.getAllProfiles();
        getView().refreshView();
    }

    @Override
    public int getAdapterItemCount() {
        return profiles.size();
    }

    @Override
    public void onBindAtPosition(IAdapterView<Profile> holder, int position) {
        holder.bindValues(profiles.get(position));
    }

    @Override
    public void onItemClicked(Profile model) {
        getView().openProfile(model);
    }

    @Override
    public void onItemLongClicked(Profile model) {
        getView().openProfile(model);
    }

    public void createProfile(String name) {
        profileController.createProfile(name);
        refreshView();
    }

    public void deleteProfile(Profile profile) {
        profileController.delete(profile);
        refreshView();
    }
}
