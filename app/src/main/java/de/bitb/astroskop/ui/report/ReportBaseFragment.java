package de.bitb.astroskop.ui.report;


import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.enums.AnimationType;

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
