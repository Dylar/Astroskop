package de.bitb.astroskop.ui.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.base.IToolbarView;
import de.bitb.astroskop.ui.report.form.ReportFormFourFragment;
import de.bitb.astroskop.ui.report.form.ReportFormOneFragment;
import de.bitb.astroskop.ui.report.form.ReportFormSentFragment;
import de.bitb.astroskop.ui.report.form.ReportFormThreeFragment;
import de.bitb.astroskop.ui.report.form.ReportFormTwoFragment;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.enums.AnimationType;
import de.bitb.astroskop.ui.report.form.ReportFormErrorFragment;
import lombok.Getter;

public class ReportActivity extends BaseActivity implements IBind, IToolbarView, IReportView {

    @Getter
    private ReportPresenter presenter;

    public static void startActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, ReportActivity.class);
        activity.startActivity(intent);
        activity.getUiUtils().setAnimation(activity, AnimationType.SLIDE_BOTTOM_POP_BOTTOM, true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new ReportPresenter((SNHApplication) getApplication(), this);
        setToolbarWidthWorkaround(false, 1);
        showFragment(ReportStartFragment.createInstance(), false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle(getString(R.string.report_disturbance));
        getCommonUtils().closeKeyboard(getContext(), getBaseView().getWindowToken());
    }

    @Override
    public AnimationType getAnimationType() {
        return AnimationType.SLIDE_BOTTOM_POP_BOTTOM;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_navbar_back;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setToolbarWidthWorkaround(getCurrentContent() instanceof ReportTabBaseFragment, 1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToReportFormTab1Screen() {
        showFragmentClearTop(ReportFormOneFragment.createInstance(), true);
        setToolbarWidthWorkaround(true, 1);
    }

    @Override
    public void navigateToReportFormTab2Screen() {
        showFragmentClearTop(ReportFormTwoFragment.createInstance(), true);
        setToolbarWidthWorkaround(true, 1);
    }

    @Override
    public void navigateToReportFormTab3Screen() {
        showFragmentClearTop(ReportFormThreeFragment.createInstance(), true);
        setToolbarWidthWorkaround(true, 1);
    }

    @Override
    public void navigateToReportFormTab4Screen() {
        showFragmentClearTop(ReportFormFourFragment.createInstance(), true);
        setToolbarWidthWorkaround(true, 1);
    }

    @Override
    public void navigateToReportAlternativeScreen() {
        showFragment(ReportAlternativeFragment.createInstance(), true);
        setToolbarWidthWorkaround(true, 1);
    }

    @Override
    public void showNoNetworkError() {
        showFragment(ReportFormErrorFragment.createInstance(), true);
        setToolbarWidthWorkaround(false, 1);
    }

    @Override
    public void navigateToReportSentScreen() {
        showFragment(ReportFormSentFragment.createInstance(), true);
        setToolbarWidthWorkaround(false, 1);
    }

}