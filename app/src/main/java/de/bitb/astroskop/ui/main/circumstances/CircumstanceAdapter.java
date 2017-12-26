package de.bitb.astroskop.ui.main.circumstances;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import de.bitb.astroskop.R;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import de.bitb.astroskop.ui.base.adapter.SingleRecyclerAdapter;

public class CircumstanceAdapter extends SingleRecyclerAdapter<Circumstance, CircumstanceViewHolder> {

    public CircumstanceAdapter(Context context, AdapterPresenter presenter) {
        super(context, presenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.adapter_circumstance_item;
    }

    @Override
    protected CircumstanceViewHolder newHolder(AdapterPresenter presenter, View view) {
        return new CircumstanceViewHolder(presenter, view);
    }

}
