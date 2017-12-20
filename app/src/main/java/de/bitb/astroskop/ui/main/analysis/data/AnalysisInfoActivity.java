package de.bitb.astroskop.ui.main.analysis.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import de.bitb.astroskop.ui.base.BaseActivity;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.enums.AnimationType;
import de.bitb.astroskop.ui.base.SimpleBaseActivity;

public class AnalysisInfoActivity extends SimpleBaseActivity {

    public static void startActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, AnalysisInfoActivity.class);
        activity.startActivity(intent);
        activity.getUiUtils().setAnimation(activity, AnimationType.SLIDE_BOTTOM_POP_BOTTOM, true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(getString(R.string.activity_analysis_info_title));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTracker().trackScreenView(getString(R.string.tracker_analysis_data_info));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_analysis_info;
    }


}
