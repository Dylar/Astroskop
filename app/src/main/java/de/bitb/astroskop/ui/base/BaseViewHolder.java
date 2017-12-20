package de.bitb.astroskop.ui.base;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import lombok.Getter;

public abstract class BaseViewHolder<P extends BasePresenter<?>, M> extends RecyclerView.ViewHolder {

    @Getter
    private final P presenter;

    public BaseViewHolder(P presenter, View view) {
        super(view);
        this.presenter = presenter;
        ButterKnife.bind(this, view);
    }

    public abstract void bindValues(M construction);
}
