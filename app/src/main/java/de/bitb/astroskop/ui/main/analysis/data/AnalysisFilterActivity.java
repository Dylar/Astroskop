package de.bitb.astroskop.ui.main.analysis.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.enums.AnimationType;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.base.SimpleBaseActivity;

import static de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter.FILTER_ALTONA;
import static de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter.FILTER_BERGEDORF;
import static de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter.FILTER_EIMSBUETTEL;
import static de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter.FILTER_HAMBURG;
import static de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter.FILTER_HARBURG;
import static de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter.FILTER_MITTE;
import static de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter.FILTER_NORD;
import static de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter.FILTER_WANDSBEK;

public class AnalysisFilterActivity extends SimpleBaseActivity implements IBind {

    public static final String KEY_SELECTED_FILTER = "selectedFilter";

    public static final int REQUEST_CODE = 999;

    @BindView(R.id.activity_analysis_filter_nord_check)
    protected View nordCheck;
    @BindView(R.id.activity_analysis_filter_mitte_check)
    protected View mitteCheck;
    @BindView(R.id.activity_analysis_filter_Bergedorf_check)
    protected View bergedorfCheck;
    @BindView(R.id.activity_analysis_filter_altona_check)
    protected View altoneCheck;
    @BindView(R.id.activity_analysis_filter_eimsbuettel_check)
    protected View eimsbuettelCheck;
    @BindView(R.id.activity_analysis_filter_wandsbek_check)
    protected View wandsbekCheck;
    @BindView(R.id.activity_analysis_filter_harburg_check)
    protected View harburgCheck;
    @BindView(R.id.activity_analysis_filter_hamburg_check)
    protected View hamburgCheck;

    public static void startActivity(BaseActivity activity, String selectedFilter) {
        Intent intent = new Intent(activity, AnalysisFilterActivity.class);
        intent.putExtra(KEY_SELECTED_FILTER, selectedFilter);
        activity.startActivityForResult(intent, REQUEST_CODE);
        activity.getUiUtils().setAnimation(activity, AnimationType.SLIDE_BOTTOM_POP_BOTTOM, true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle(getString(R.string.activity_analysis_filter_title));
        selectFilter(getIntent().getStringExtra(KEY_SELECTED_FILTER));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTracker().trackScreenView(getString(R.string.tracker_analysis_data_filter));
    }

    private void selectFilter(String selectedFilter) {
        switch (selectedFilter) {
            case FILTER_ALTONA:
                altoneCheck.setVisibility(View.VISIBLE);
                break;
            case FILTER_EIMSBUETTEL:
                eimsbuettelCheck.setVisibility(View.VISIBLE);
                break;
            case FILTER_NORD:
                nordCheck.setVisibility(View.VISIBLE);
                break;
            case FILTER_MITTE:
                mitteCheck.setVisibility(View.VISIBLE);
                break;
            case FILTER_HARBURG:
                harburgCheck.setVisibility(View.VISIBLE);
                break;
            case FILTER_BERGEDORF:
                bergedorfCheck.setVisibility(View.VISIBLE);
                break;
            case FILTER_WANDSBEK:
                wandsbekCheck.setVisibility(View.VISIBLE);
                break;
            case FILTER_HAMBURG:
            default:
                hamburgCheck.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_analysis_filter;
    }

    @OnClick({R.id.activity_analysis_filter_wandsbek_container, R.id.activity_analysis_filter_altona_container,
            R.id.activity_analysis_filter_Bergedorf_container, R.id.activity_analysis_filter_eimsbuettel_container,
            R.id.activity_analysis_filter_hamburg_container, R.id.activity_analysis_filter_harburg_container,
            R.id.activity_analysis_filter_mitte_container, R.id.activity_analysis_filter_nord_container})
    public void onFilterClicked(View view) {
        String selectedFilter;
        switch (view.getId()) {
            case R.id.activity_analysis_filter_wandsbek_container:
                selectedFilter = FILTER_WANDSBEK;
                break;
            case R.id.activity_analysis_filter_altona_container:
                selectedFilter = FILTER_ALTONA;
                break;
            case R.id.activity_analysis_filter_Bergedorf_container:
                selectedFilter = FILTER_BERGEDORF;
                break;
            case R.id.activity_analysis_filter_eimsbuettel_container:
                selectedFilter = FILTER_EIMSBUETTEL;
                break;
            case R.id.activity_analysis_filter_harburg_container:
                selectedFilter = FILTER_HARBURG;
                break;
            case R.id.activity_analysis_filter_mitte_container:
                selectedFilter = FILTER_MITTE;
                break;
            case R.id.activity_analysis_filter_nord_container:
                selectedFilter = FILTER_NORD;
                break;
            case R.id.activity_analysis_filter_hamburg_container:
            default:
                selectedFilter = FILTER_HAMBURG;
                break;
        }

        Intent returnIntent = new Intent();
        returnIntent.putExtra(KEY_SELECTED_FILTER, selectedFilter);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}
