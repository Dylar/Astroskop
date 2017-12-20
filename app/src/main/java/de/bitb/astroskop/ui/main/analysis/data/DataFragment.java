package de.bitb.astroskop.ui.main.analysis.data;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.main.analysis.IAnalysisView;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.utils.DateUtils;

import static android.app.Activity.RESULT_OK;

public class DataFragment extends BaseFragment implements IDataView, IAnalysisView, IBind, IInjection {

    public static final double CO2_MODIFIER = 0.575;
    public static final String DIGIT_FORMAT = "%04d";

    @BindView(R.id.fragment_data_filter_button_text)
    protected TextView filterTv;

    @BindView(R.id.fragment_data_power_digit_1)
    protected TextView digitPower1;

    @BindView(R.id.fragment_data_power_digit_2)
    protected TextView digitPower2;

    @BindView(R.id.fragment_data_power_digit_3)
    protected TextView digitPower3;

    @BindView(R.id.fragment_data_power_digit_4)
    protected TextView digitPower4;

    @BindView(R.id.fragment_data_co2_digit_1)
    protected TextView digitCo21;

    @BindView(R.id.fragment_data_co2_digit_2)
    protected TextView digitCo22;

    @BindView(R.id.fragment_data_co2_digit_3)
    protected TextView digitCo23;

    @BindView(R.id.fragment_data_co2_digit_4)
    protected TextView digitCo24;

    @BindView(R.id.fragment_data_last_sync)
    protected TextView syncTv;

    @BindView(R.id.fragment_data_chart)
    protected com.github.mikephil.charting.charts.LineChart chart;

    @BindView(R.id.fragment_data_chart_container)
    protected View chartContainer;

    @Inject
    protected DateUtils dateUtils;

    protected DataViewPresenter presenter;

    public static BaseFragment createInstance() {
        return new DataFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initChart();
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public DataViewPresenter getPresenter() {
        if (presenter == null) {
            presenter = new DataViewPresenter((SNHApplication) getActivity().getApplication(), this);
        }
        return presenter;
    }

    private void initChart() {
        Description description = new Description();
        description.setText("");
        description.setTextAlign(Paint.Align.CENTER);
        chart.setDescription(description);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setLabelCount(7);
        xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.text_gray));
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private long baseTime = System.currentTimeMillis() - DateUtils.ONE_DAY_IN_MILLI;

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                DateTime date = new DateTime((long) (baseTime + (value * DateUtils.FIFTEEN_MIN_IN_MILLI)), DateTimeZone.forTimeZone(TimeZone.getDefault()));
                return String.valueOf(date.getHourOfDay());
            }
        });

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setEnabled(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.text_gray));

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        chart.setScaleEnabled(false);

        getPresenter().refreshView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data;
    }

    @Override
    public void onRefreshClicked() {
        getPresenter().onRefreshClicked();
    }

    @Override
    public void updateChart(List<Entry> entries) {
        if (entries == null) {
            chartContainer.setVisibility(View.GONE);
        } else {
            chartContainer.setVisibility(View.VISIBLE);
            LineDataSet dataSet = new LineDataSet(entries, "");
            LineData lineData = new LineData(dataSet);
            dataSet.setDrawValues(false);
            dataSet.setHighlightEnabled(false);
            dataSet.setDrawCircles(false);
            dataSet.setLineWidth(1f);
            dataSet.setColors(new int[]{R.color.blue}, getContext());
            chart.setData(lineData);
            chart.invalidate();
        }
    }

    @Override
    public void updateCurrentPower(int powerHamburg, String filter) {
        filterTv.setText(filter);

        String power = String.format(Locale.getDefault(), DIGIT_FORMAT, powerHamburg);
        String co2 = String.format(Locale.getDefault(), DIGIT_FORMAT, (int) (powerHamburg * CO2_MODIFIER));

        digitPower1.setText(String.valueOf(power.charAt(0)));
        digitPower2.setText(String.valueOf(power.charAt(1)));
        digitPower3.setText(String.valueOf(power.charAt(2)));
        digitPower4.setText(String.valueOf(power.charAt(3)));

        digitCo21.setText(String.valueOf(co2.charAt(0)));
        digitCo22.setText(String.valueOf(co2.charAt(1)));
        digitCo23.setText(String.valueOf(co2.charAt(2)));
        digitCo24.setText(String.valueOf(co2.charAt(3)));
    }

    @Override
    public void showNoNetworkError() {
        IAnalysisView view = (IAnalysisView) getParentFragment();
        view.showNoNetworkError();
    }

    @Override
    public void hideNoNetworkError() {
        IAnalysisView view = (IAnalysisView) getParentFragment();
        view.hideNoNetworkError();
    }

    @Override
    public void openFilter(String selectedFilter) {
        AnalysisFilterActivity.startActivity((BaseActivity) getActivity(), selectedFilter);
    }

    @Override
    public void updateLastSync(long updateTimestamp) {
        syncTv.setText(getString(R.string.fragment_data_last_sync, dateUtils.formatMillisToReadable(updateTimestamp)));
    }

    @Override
    public void onInfoClicked() {
        AnalysisInfoActivity.startActivity((BaseActivity) getActivity());
    }

    @OnClick(R.id.fragment_data_filter_button)
    public void onFilterClicked() {
        getPresenter().onFilterClicked();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AnalysisFilterActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            getPresenter().filterSelected(data.getStringExtra(AnalysisFilterActivity.KEY_SELECTED_FILTER));
        }
    }

    @Override
    public void onVisible() {
        getPresenter().onVisible();
    }

}