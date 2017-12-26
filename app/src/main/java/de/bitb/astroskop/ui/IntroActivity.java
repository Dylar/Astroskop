package de.bitb.astroskop.ui;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.main.MainActivity;
import de.bitb.astroskop.utils.SharedPreferencesUtils;
import de.bitb.astroskop.viewbuilder.DialogBuilder;

public class IntroActivity extends BaseActivity implements IInjection, IBind {

    private static final int SPLASHSCREEN_MIN_DELAY = 5000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.background_gray));

        long updateStarted = System.currentTimeMillis();
        startRemainingTimer(updateStarted);
    }

    private void startRemainingTimer(long updateStarted) {
        long usedTime = System.currentTimeMillis() - updateStarted;
        int remainingDelay = (int) (SPLASHSCREEN_MIN_DELAY - (usedTime / 1000));
        new Handler().postDelayed(() -> {
                navigateToMainScreen();
        }, remainingDelay < 0 ? 1 : remainingDelay);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_intro;
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    private void navigateToMainScreen() {
        MainActivity.startActivity(this);
        finish();
    }

}
