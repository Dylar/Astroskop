package de.bornholdtlee.snh.ui.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import butterknife.BindView;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.enums.AnimationType;
import de.bornholdtlee.snh.injection.IBind;

public class PrivacyFragment extends MenuBaseFragment implements IBind {

    @BindView(R.id.fragment_privacy_webview)
    protected WebView privacy;

    public static PrivacyFragment createInstance() {
        return new PrivacyFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_privacy;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarTitle(getString(R.string.fragment_privacy_title));
        privacy.setBackgroundColor(Color.TRANSPARENT);
        privacy.loadUrl("file:///android_asset/html/privacy.html");
    }

    @Override
    public void onResume() {
        super.onResume();
        getTracker().trackScreenView(getString(R.string.tracker_privacy));
    }

    @Override
    public AnimationType getAnimationType() {
        return AnimationType.SLIDE_RIGHT_POP_LEFT;
    }
}