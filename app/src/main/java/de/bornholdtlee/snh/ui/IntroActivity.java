package de.bornholdtlee.snh.ui;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.api.ControllerCallback;
import de.bornholdtlee.snh.controller.ConstructionsController;
import de.bornholdtlee.snh.controller.DisturbanceController;
import de.bornholdtlee.snh.controller.PowerController;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.model.Construction;
import de.bornholdtlee.snh.model.DistrictPower;
import de.bornholdtlee.snh.model.Disturbance;
import de.bornholdtlee.snh.model.StreetSearchResult;
import de.bornholdtlee.snh.ui.base.BaseActivity;
import de.bornholdtlee.snh.ui.main.MainActivity;
import de.bornholdtlee.snh.utils.SharedPreferencesUtils;
import de.bornholdtlee.snh.viewbuilder.DialogBuilder;

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

    @Inject
    ConstructionsController constructionsController;

    @Inject
    DisturbanceController disturbanceController;

    @Inject
    PowerController powerController;

    private boolean constructionsLoaded;
    private boolean disturbancesLoaded;
    private boolean graphLoaded;
    private boolean powerRangerSummoned;
    private boolean streetsLoaded;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.background_gray));

        getTracker().trackScreenView(getString(R.string.tracker_splash));
        long updateStarted = System.currentTimeMillis();
        loadConstructions(updateStarted);
        loadDisturbance(updateStarted);
        loadStreets(updateStarted);
        loadGraph(updateStarted);
        summonPowerRanger(updateStarted);
    }

    private void loadStreets(long updateStarted) {
        disturbanceController.loadAllStreetSearchResults(new ControllerCallback<StreetSearchResult>() {
            @Override
            public void onSuccess(List<StreetSearchResult> response) {
                loadMore();
            }

            @Override
            public void onSuccess(StreetSearchResult response) {
                loadMore();
            }

            @Override
            public void onFailure() {
                loadMore();
            }

            private void loadMore() {
                streetsLoaded = true;
                startRemainingTimer(updateStarted);
            }
        });
    }

    private void summonPowerRanger(long updateStarted) {
        powerController.loadDistrictPower(new ControllerCallback<DistrictPower>() {
            @Override
            public void onSuccess(List<DistrictPower> response) {
                onSuccess(response.get(0));
            }

            @Override
            public void onSuccess(DistrictPower response) {
                powerRangerSummoned = true;
                startRemainingTimer(updateStarted);
            }

            @Override
            public void onFailure() {
                powerRangerSummoned = true;
                startRemainingTimer(updateStarted);
            }
        });
    }

    private void loadGraph(long updateStarted) {
        powerController.loadGraph(new ControllerCallback<Integer>() {
            @Override
            public void onSuccess(List<Integer> response) {
                onSuccess(response.get(0));
            }

            @Override
            public void onSuccess(Integer response) {
                graphLoaded = true;
                startRemainingTimer(updateStarted);
            }

            @Override
            public void onFailure() {
                graphLoaded = true;
                startRemainingTimer(updateStarted);
            }
        });
    }

    private void loadConstructions(long updateStarted) {
        constructionsController.loadAllConstructions(new ControllerCallback<Construction>() {
            @Override
            public void onSuccess(List<Construction> response) {
                constructionsLoaded = true;
                startRemainingTimer(updateStarted);
            }

            @Override
            public void onSuccess(Construction response) {
                //nothing
            }

            @Override
            public void onFailure() {
                constructionsLoaded = true;
                startRemainingTimer(updateStarted);
            }
        });
    }

    private void loadDisturbance(long updateStarted) {
        disturbanceController.loadAllDisturbance(new ControllerCallback<Disturbance>() {
            @Override
            public void onSuccess(List<Disturbance> response) {
                disturbancesLoaded = true;
                startRemainingTimer(updateStarted);
            }

            @Override
            public void onSuccess(Disturbance response) {
                //nothing
            }

            @Override
            public void onFailure() {
                disturbancesLoaded = true;
                startRemainingTimer(updateStarted);
            }
        });
    }

    private void startRemainingTimer(long updateStarted) {
        if (constructionsLoaded && disturbancesLoaded && graphLoaded && powerRangerSummoned && streetsLoaded) {
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
        PrivacyActivity.startActivity(this);
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
