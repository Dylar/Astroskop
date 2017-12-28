package de.bitb.astroskop.ui.base.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import lombok.Getter;

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder implements IAdapterView<M>{

    @Getter
    private final AdapterPresenter<M> presenter;

    public BaseViewHolder(AdapterPresenter presenter, View view) {
        super(view);
        this.presenter = presenter;
        ButterKnife.bind(this, view);
    }

    public abstract void bindValues(M model);
}
