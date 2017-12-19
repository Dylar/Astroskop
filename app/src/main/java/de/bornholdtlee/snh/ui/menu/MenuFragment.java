package de.bornholdtlee.snh.ui.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.bornholdtlee.snh.BuildConfig;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.utils.CommonUtils;

public class MenuFragment extends MenuBaseFragment implements IBind, IInjection {

    @Inject
    protected CommonUtils commonUtils;

    @BindView(R.id.fragment_menu_buildversion)
    protected TextView buildVersion;


    private IMenuView menuView;

    public static MenuFragment createInstance() {
        return new MenuFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        menuView = (IMenuView) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle(getString(R.string.fragment_menu_title));
        buildVersion.setVisibility(BuildConfig.SHOW_VERSION ? View.VISIBLE : View.GONE);
        buildVersion.setText(getString(R.string.version, String.valueOf(BuildConfig.VERSION_CODE), String.valueOf(BuildConfig.VERSION_NAME)));
        getTracker().trackScreenView(getString(R.string.tracker_menu));
    }

    @OnClick(R.id.fragment_menu_button_job_offer_container)
    public void onJobOfferClick() {
        commonUtils.openExternUrl(getContext(), getContext().getString(R.string.url_job_offer));
    }

    @OnClick(R.id.fragment_menu_button_rank_app_container)
    public void onRankAppClick() {
        commonUtils.rankApp(getContext());
    }

    @OnClick(R.id.fragment_menu_button_imprint_container)
    public void navigateToImprint() {
        menuView.navigateToImprint();
    }

    @OnClick(R.id.fragment_menu_button_privacy_container)
    public void navigateToPrivacy() {
        menuView.navigateToPrivacy();
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }
}