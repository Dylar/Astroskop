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

    private static final String KEY_FIRST_STARTED = "firstStarted";
    private static final String KEY_PRIVACY_READ = "acceptPrivacy";

    private static final int SPLASHSCREEN_MIN_DELAY = 2000;

    @BindView(R.id.activity_intro_splashscreen)
    protected View splashScreen;

    @Inject
    DialogBuilder dialogBuilder;

    @Inject
    SharedPreferencesUtils sharedPreferencesUtils;

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
            if (isFirstStarted()) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                splashScreen.setVisibility(View.GONE);
            } else {
                navigateToMainScreen();
            }
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

    @OnClick(R.id.activity_intro_button)
    public void onGoButton() {
        if (hasReadPrivacy()) {
            firstStarted();
            navigateToMainScreen();
        } else {
            dialogBuilder.showPrivacyDialog(getContext(), (dialogInterface, i) -> {
                acceptPrivacy();
                onGoButton();
            }, (dialogInterface, i) -> openPrivacy());
        }
    }

    private void openPrivacy() {
    }

    private boolean hasReadPrivacy() {
        return sharedPreferencesUtils.getBoolean(KEY_PRIVACY_READ, false);
    }

    private void navigateToMainScreen() {
        MainActivity.startActivity(this);
        finish();
    }

    private boolean isFirstStarted() {
        return sharedPreferencesUtils.getBoolean(KEY_FIRST_STARTED, true);
    }

    private void firstStarted() {
        sharedPreferencesUtils.putBoolean(KEY_FIRST_STARTED, false);
    }

    private void acceptPrivacy() {
        sharedPreferencesUtils.putBoolean(KEY_PRIVACY_READ, true);
    }

}
