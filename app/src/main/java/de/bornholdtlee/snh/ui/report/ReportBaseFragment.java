package de.bornholdtlee.snh.ui.report;


import de.bornholdtlee.snh.enums.AnimationType;
import de.bornholdtlee.snh.ui.base.BaseFragment;

public abstract class ReportBaseFragment extends BaseFragment {

    protected boolean showInfoActionBarButton() {
        return false;
    }

    protected boolean showRefreshActionBarButton() {
        return false;
    }

    protected boolean showFinishActionBarButton() {
        return true;
    }

    @Override
    public AnimationType getAnimationType() {
        return AnimationType.SLIDE_RIGHT_POP_LEFT;
    }
}
