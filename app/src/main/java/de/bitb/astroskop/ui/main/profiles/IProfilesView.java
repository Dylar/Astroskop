package de.bitb.astroskop.ui.main.profiles;

import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.ui.base.IBaseView;

interface IProfilesView extends IBaseView{
    void openProfile(Profile profile);

    void openDeleteDialog(Profile profile);

    void refreshView();
}
