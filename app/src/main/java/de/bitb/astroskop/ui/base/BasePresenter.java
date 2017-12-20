package de.bitb.astroskop.ui.base;

import android.content.Context;

import com.etracker.tracking.Tracker;

import java.lang.ref.WeakReference;

import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.SNHApplication;
import lombok.Getter;

public class BasePresenter<T extends IBaseView> {

    @Getter
    protected Tracker tracker;

    private WeakReference<T> view;

    public BasePresenter(SNHApplication application, T view) {
        this.view = new WeakReference<>(view);
        if (this instanceof IInjection) {
            ((IInjection) this).inject(application.getAppComponent());
        }
        tracker = application.getTracker();
    }

    public T getView() {
        return view.get();
    }

    public Context getContext() {
        return getView().getContext();
    }

}
