package de.bornholdtlee.snh.ui.main.analysis.map;

import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import butterknife.BindView;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.ui.base.BaseFragment;
import de.bornholdtlee.snh.ui.main.analysis.IAnalysisView;

public class MapFragment extends BaseFragment implements IMapView, IBind {

    public static final int POWER_100 = 100;
    public static final int POWER_150 = 150;
    public static final int POWER_200 = 200;
    public static final int POWER_250 = 250;
    public static final int POWER_300 = 300;
    public static final int POWER_350 = 350;
    public static final int POWER_400 = 400;
    public static final int POWER_450 = 450;
    public static final int POWER_500 = 500;

    @BindView(R.id.fragment_map_altona)
    protected ImageView altonaImg;

    @BindView(R.id.fragment_map_eimsbuettel)
    protected ImageView eimsbuettelImg;

    @BindView(R.id.fragment_map_nord)
    protected ImageView nordImg;

    @BindView(R.id.fragment_map_wandsbek)
    protected ImageView wandsbekImg;

    @BindView(R.id.fragment_map_bergedorf)
    protected ImageView bergedorfImg;

    @BindView(R.id.fragment_map_harburg)
    protected ImageView harburgImg;

    @BindView(R.id.fragment_map_mitte)
    protected ImageView mitteImg;

    protected MapViewPresenter presenter;

    public static BaseFragment createInstance() {
        return new MapFragment();
    }

    public MapViewPresenter getPresenter() {
        if (presenter == null) {
            presenter = new MapViewPresenter((SNHApplication) getActivity().getApplication(), this);
        }
        return presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onRefreshClicked() {
        getPresenter().refreshView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_map;
    }

    private int getColor(int consumption) {
        int color = R.color.consumption_below_100;
        if (consumption >= POWER_100 && consumption < POWER_150) {
            color = R.color.consumption_100_150;
        } else if (consumption >= POWER_150 && consumption < POWER_200) {
            color = R.color.consumption_150_200;
        } else if (consumption >= POWER_200 && consumption < POWER_250) {
            color = R.color.consumption_200_250;
        } else if (consumption >= POWER_250 && consumption < POWER_300) {
            color = R.color.consumption_250_300;
        } else if (consumption >= POWER_300 && consumption < POWER_350) {
            color = R.color.consumption_300_350;
        } else if (consumption >= POWER_350 && consumption < POWER_400) {
            color = R.color.consumption_350_400;
        } else if (consumption >= POWER_400 && consumption < POWER_450) {
            color = R.color.consumption_400_450;
        } else if (consumption >= POWER_450 && consumption < POWER_500) {
            color = R.color.consumption_450_500;
        } else if (consumption >= POWER_500) {
            color = R.color.consumption_over_500;
        }
        return ContextCompat.getColor(getContext(), color);
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
    public void tintAltona(int consumption) {
        altonaImg.setColorFilter(getColor(consumption));
    }

    @Override
    public void tintEimsbuettel(int consumption) {
        eimsbuettelImg.setColorFilter(getColor(consumption));
    }

    @Override
    public void tintNord(int consumption) {
        nordImg.setColorFilter(getColor(consumption));
    }

    @Override
    public void tintWandsbek(int consumption) {
        wandsbekImg.setColorFilter(getColor(consumption));
    }

    @Override
    public void tintBergedorf(int consumption) {
        bergedorfImg.setColorFilter(getColor(consumption));
    }

    @Override
    public void tintHarburg(int consumption) {
        harburgImg.setColorFilter(getColor(consumption));
    }

    @Override
    public void tintMitte(int consumption) {
        mitteImg.setColorFilter(getColor(consumption));
    }

    @Override
    public void onVisible() {
        getPresenter().onVisible();
    }
}
