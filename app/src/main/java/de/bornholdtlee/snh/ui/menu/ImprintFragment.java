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

public class ImprintFragment extends MenuBaseFragment implements IBind {

    @BindView(R.id.fragment_imprint_webview)
    protected WebView imprint;

    public static ImprintFragment createInstance() {
        return new ImprintFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_imprint;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setToolbarTitle(getString(R.string.fragment_imprint_title));
        imprint.setBackgroundColor(Color.TRANSPARENT);
        imprint.loadUrl("file:///android_asset/html/imprint.html");
    }

    @Override
    public void onResume() {
        super.onResume();
        getTracker().trackScreenView(getString(R.string.tracker_imprint));
    }

    @Override
    public AnimationType getAnimationType() {
        return AnimationType.SLIDE_RIGHT_POP_LEFT;
    }
}