package de.bornholdtlee.snh.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.etracker.tracking.Tracker;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.enums.AnimationType;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.ui.main.home.HomeFragment;
import de.bornholdtlee.snh.utils.CommonUtils;
import de.bornholdtlee.snh.utils.UiUtils;
import lombok.Getter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

    @Getter
    private static boolean appInForeground;

    @Inject
    @Getter
    protected UiUtils uiUtils;

    @Inject
    @Getter
    protected CommonUtils commonUtils;

    @Inject
    @Getter
    protected Tracker tracker;

    @Getter
    private View baseView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this instanceof IInjection) {
            ((IInjection) this).inject(getAppComponent());
        } else {
            getAppComponent().inject(this);
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        View activityView = inflater.inflate(getLayoutId(), null);

        baseView = inflater.inflate(R.layout.activity_base, null);

        ViewGroup container = baseView.findViewById(R.id.activity_base_container);
        container.removeAllViews();
        container.addView(activityView);

        initToolbar(baseView);

        setContentView(baseView);

        if (this instanceof IBind) {
            ButterKnife.bind(this);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public int getLayoutId() {
        return R.layout.activity_container;
    }

    public int getContentContainerId() {
        return R.id.activity_container;
    }

    public AppComponent getAppComponent() {
        return ((SNHApplication) getApplication()).getAppComponent();
    }

    public Context getContext() {
        return this;
    }

    public Bundle getArguments() {
        return getIntent().getExtras();
    }

    public boolean willNotCrash() {
        return !isFinishing();
    }

    private void initToolbar(View view) {
        if (this instanceof IToolbarView) {
            IToolbarView toolbarView = (IToolbarView) this;
            Toolbar toolbar = view.findViewById(R.id.activity_base_toolbar);

            toolbar.setVisibility(View.VISIBLE);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(getLayoutInflater().inflate(R.layout.base_toolbar, null));
//            actionBar.setCustomView(getLayoutInflater().inflate(R.layout.base_toolbar, null),
//                    new ActionBar.LayoutParams(
//                            ActionBar.LayoutParams.MATCH_PARENT,
//                            ActionBar.LayoutParams.MATCH_PARENT,
//                            Gravity.CENTER
//                    )
//            );
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(toolbarView.getIcon());
        }
    }

    protected void setToolbarWidthWorkaround(boolean showHome, int btnCount) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHome);
        View toolbar = getSupportActionBar().getCustomView();
//        toolbar.findViewById(R.id.toolbar_placeholder_back).setVisibility(showHome ? View.GONE : View.VISIBLE);
        toolbar.findViewById(R.id.toolbar_placeholder_button_2).setVisibility(btnCount > 1 ? View.GONE : View.VISIBLE);
        toolbar.findViewById(R.id.toolbar_placeholder_button_1).setVisibility(btnCount > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appInForeground = true;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appInForeground = false;
    }

    @Override
    public void onBackPressed() {
        BaseFragment fragment = getCurrentContent();

        if ((fragment == null || fragment.allowBackPressed()) && allowBackPressed()) {
            if (fragment instanceof HomeFragment) {
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    protected boolean allowBackPressed() {
        return true;
    }

    public void setToolbarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
            toolbarTitle.setText(title);
            toolbarTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setToolbarIcon(int icon) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(icon);
        }
    }

    public BaseFragment getCurrentContent() {
        return (BaseFragment) getSupportFragmentManager().findFragmentById(getContentContainerId());
    }

//    protected void showScreenClearBackStack(BaseFragment fragment, boolean shouldAddToBackStack) {
//        showScreenClearBackStack(fragment, getContentContainerId(), shouldAddToBackStack);
//    }
//
//    private void showScreenClearBackStack(BaseFragment fragment, int containerViewResId, boolean shouldAddToBackStack) {
//        clearBackstack();
//        showFragment(fragment, containerViewResId, shouldAddToBackStack);
//    }
//
//    private void clearBackstack() {
//        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//    }

    public void showFragmentClearTop(BaseFragment fragment, boolean shouldAddToBackStack) {
        showFragmentClearTop(fragment, getContentContainerId(), shouldAddToBackStack);
    }

    public void showFragmentClearTop(BaseFragment fragment, int containerViewResId, boolean shouldAddToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String fragmentName = fragment.getClass().getName();

        boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragmentName, 0);

        if (!fragmentPopped) { //fragment not in back stack, create it.
            showFragment(fragment, containerViewResId, shouldAddToBackStack);
        }
    }

    public void showFragment(BaseFragment fragment, boolean shouldAddToBackStack) {
        showFragment(fragment, getContentContainerId(), shouldAddToBackStack);
    }

    public void showFragment(BaseFragment fragment, int containerViewResId, boolean shouldAddToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String fragmentName = fragment.getClass().getName();
        BaseFragment fragmentByTag = (BaseFragment) fragmentManager.findFragmentByTag(fragmentName);

        fragmentByTag = (null == fragmentByTag) ? fragment : fragmentByTag;

        if (fragmentByTag.isAdded()) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        uiUtils.setAnimation(fragmentByTag, fragmentTransaction);

        fragmentTransaction.replace(containerViewResId, fragmentByTag, fragmentName);

        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void finish() {
        super.finish();
        getCommonUtils().closeKeyboard(this, getBaseView().getWindowToken());
        if (!AnimationType.NONE.equals(getAnimationType())) {
            getUiUtils().setAnimation(this, false);
        }
    }

    public AnimationType getAnimationType() {
        return AnimationType.NONE;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        BaseFragment frag = getCurrentContent();
        frag.onActivityResult(requestCode, resultCode, data);
    }
}
