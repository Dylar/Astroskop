package de.bitb.astroskop.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.bitb.astroskop.Constants;
import de.bitb.astroskop.R;
import de.bitb.astroskop.enums.AnimationType;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.viewbuilder.DialogBuilder;
import de.bitb.astroskop.viewbuilder.ToastBuilder;

import static de.bitb.astroskop.Constants.NULL_INTEGER;

public abstract class BaseFragment extends Fragment implements IBaseView {

    @Inject
    protected ToastBuilder toastUtils;

    @Inject
    protected DialogBuilder dialogBuilder;

    private IToolbarView toolbarView;
    private ActionbarHandler actionbarHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.actionbarHandler = new ActionbarHandler(getActionbarCallback());
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

    public ActionbarHandler.ActionbarCallback getActionbarCallback() {
        return new ActionbarHandler.ActionbarCallback();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        actionbarHandler.onPrepareOptionsMenu(menu);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionbarHandler.onOptionsItemSelected(item.getItemId()) || super.onOptionsItemSelected(item);
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

}
