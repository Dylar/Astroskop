package de.bitb.astroskop.ui.menu;

import de.bitb.astroskop.ui.base.BaseFragment;

public abstract class MenuBaseFragment extends BaseFragment {

    protected boolean showInfoActionBarButton() {
        return false;
    }

    protected boolean showRefreshActionBarButton() {
        return false;
    }

    protected boolean showFinishActionBarButton() {
        return true;
    }
}
