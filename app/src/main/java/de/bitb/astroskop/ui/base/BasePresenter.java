package de.bitb.astroskop.ui.base;

import android.content.Context;

import java.lang.ref.WeakReference;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.injection.IInjection;

public class BasePresenter<T extends IBaseView> {

    private WeakReference<T> view;

    public BasePresenter(AstroApplication application, T view) {
        this.view = new WeakReference<>(view);
        if (this instanceof IInjection) {
            ((IInjection) this).inject(application.getAppComponent());
        }
    }

    public T getView() {
        return view.get();
    }

    public Context getContext() {
        return getView().getContext();
    }

}
