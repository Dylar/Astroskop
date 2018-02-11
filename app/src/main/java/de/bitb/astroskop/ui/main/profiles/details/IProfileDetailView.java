package de.bitb.astroskop.ui.main.profiles.details;

import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.ui.base.IBaseView;

interface IProfileDetailView extends IBaseView {

    void setToolbarTitle(String title);

    void initProfile(Profile profile);

    void refreshView();
}
