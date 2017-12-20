package de.bitb.astroskop.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.etracker.tracking.Tracker;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.injection.IInjection;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.enums.AnimationType;
import de.bitb.astroskop.viewbuilder.ToastBuilder;
import lombok.Getter;

public abstract class BaseFragment extends Fragment implements IBaseView {

    @Getter
    @Inject
    protected Tracker tracker;

    @Inject
    protected ToastBuilder toastUtils;

    private IToolbarView toolbarView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (this instanceof IInjection) {
            ((IInjection) this).inject(((BaseActivity) getActivity()).getAppComponent());
        } else {
            ((BaseActivity) getActivity()).getAppComponent().inject(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(getLayoutId(), null, false);
        if (this instanceof IBind) {
            ButterKnife.bind(this, root);
        }

        setHasOptionsMenu(true);
        return root;
    }

    public abstract int getLayoutId();

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setVisible(showRefreshActionBarButton());
        menu.getItem(1).setVisible(showInfoActionBarButton());
        menu.getItem(2).setVisible(showFinishActionBarButton());
        super.onPrepareOptionsMenu(menu);
    }

    protected boolean showInfoActionBarButton() {
        return true;
    }

    protected boolean showRefreshActionBarButton() {
        return true;
    }

    protected boolean showFinishActionBarButton() {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionbar_refresh: {
                onRefreshClicked();
                return true;
            }
            case R.id.actionbar_info: {
                onInfoClicked();
                return true;
            }
            case R.id.actionbar_cancel: {
                getActivity().finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void onInfoClicked() {
        //show info
    }

    public void onRefreshClicked() {
        //refresh view
    }

    @Override
    public boolean willNotCrash() {
        return getContext() != null && getActivity() != null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IToolbarView) {
            toolbarView = (IToolbarView) context;
        }
    }

    protected void runOnUiThread(Runnable runnable) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(runnable);
        }
    }

    public void setToolbarTitle(String title) {
        if (toolbarView != null) {
            toolbarView.setToolbarTitle(title);
        }
    }

    public boolean allowBackPressed() {
        return true;
    }

    public AnimationType getAnimationType() {
        return AnimationType.NONE;
    }

    public int getIcon() {
        return R.drawable.ic_navbar_burger;
    }
}
