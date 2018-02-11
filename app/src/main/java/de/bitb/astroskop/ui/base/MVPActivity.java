package de.bitb.astroskop.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import de.bitb.astroskop.AstroApplication;
import lombok.Getter;

public abstract class MVPActivity<T extends IBaseView, P extends BasePresenter<T>> extends BaseActivity {

    @Getter
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter((AstroApplication) getApplication());
    }

    protected abstract P createPresenter(AstroApplication application);
}
