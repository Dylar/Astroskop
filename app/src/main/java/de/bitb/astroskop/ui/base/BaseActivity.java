package de.bitb.astroskop.ui.base;

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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.R;
import de.bitb.astroskop.enums.AnimationType;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.ui.main.home.HomeFragment;
import de.bitb.astroskop.utils.CommonUtils;
import de.bitb.astroskop.utils.UiUtils;
import de.bitb.astroskop.viewbuilder.ToastBuilder;
import lombok.Getter;

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
    protected ToastBuilder toastUtils;

    @Getter
    private View baseView;

    private ActionbarHandler actionbarHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.actionbarHandler = new ActionbarHandler(getActionbarCallback());

        if (this instanceof IInjection) {
            ((IInjection) this).inject(getAppComponent());
        } else {
            getAppComponent().inject(this);
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        View activityView = inflater.inflate(getLayoutId(), null);

        baseView = inflater.inflate(R.layout.activity_base, null);

        ViewGroup container = baseView.findViewById(R.id.activity_base_content);
        container.removeAllViews();
        container.addView(activityView);

        initToolbar(baseView);

        setContentView(baseView);

        if (!(this instanceof NavigationBaseActivity)) {
            baseView.findViewById(R.id.activity_navigation).setVisibility(View.GONE);
        }

        if (this instanceof IBind) {
            ButterKnife.bind(this);
        }

    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    public int getLayoutId() {
        return R.layout.activity_container;
    }

    public int getContentContainerId() {
        return R.id.activity_container;
    }

    public AppComponent getAppComponent() {
        return ((AstroApplication) getApplication()).getAppComponent();
    }

    public Context getContext() {
        return this;
    }

    public Bundle getArguments() {
        return getIntent().getExtras();
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.activity_base_toolbar);
        if (this instanceof IToolbarView) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    public ActionbarHandler.ActionbarCallback getActionbarCallback() {
        return new ActionbarHandler.ActionbarCallback();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return actionbarHandler.onPrepareOptionsMenu(menu) || super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionbarHandler.onOptionsItemSelected(item.getItemId()) || super.onOptionsItemSelected(item);
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
            actionBar.setTitle(title);
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

    public void showFragmentClearTop(BaseFragment fragment) {
        showFragmentClearTop(fragment, getContentContainerId(), false);
    }

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

    public void showFragment(BaseFragment fragment) {
        showFragment(fragment, getContentContainerId(), false);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
