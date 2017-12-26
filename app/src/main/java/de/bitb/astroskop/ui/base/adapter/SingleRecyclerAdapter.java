package de.bitb.astroskop.ui.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import de.bitb.astroskop.ui.base.adapter.BaseViewHolder;
import de.bitb.astroskop.ui.base.adapter.IAdapterView;

public abstract class SingleRecyclerAdapter<M, H extends BaseViewHolder<M>> extends RecyclerView.Adapter<H> {

    private final AdapterPresenter presenter;
    private final LayoutInflater inflater;

    public SingleRecyclerAdapter(Context context, AdapterPresenter presenter) {
        super();
        this.presenter = presenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(getLayoutId(), parent, false);
        return newHolder(presenter, view);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        presenter.onBindAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getAdapterItemCount();
    }

    protected abstract int getLayoutId();

    protected abstract H newHolder(AdapterPresenter presenter, View view);
}