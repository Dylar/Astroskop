package de.bornholdtlee.snh.ui.menu;

import de.bornholdtlee.snh.ui.base.BaseFragment;

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
