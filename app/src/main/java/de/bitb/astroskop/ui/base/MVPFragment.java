package de.bitb.astroskop.ui.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import de.bitb.astroskop.AstroApplication;
import lombok.Getter;

public abstract class MVPFragment<T extends IBaseView, P extends BasePresenter<T>> extends BaseFragment {

    @Getter
    private P presenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter((AstroApplication) getActivity().getApplication());
    }

    protected abstract P createPresenter(AstroApplication application);
}
