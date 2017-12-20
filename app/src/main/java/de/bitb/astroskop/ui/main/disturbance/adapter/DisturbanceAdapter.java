package de.bitb.astroskop.ui.main.disturbance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.bitb.astroskop.ui.main.disturbance.DisturbanceViewPresenter;
import de.bornholdtlee.snh.R;

public class DisturbanceAdapter extends RecyclerView.Adapter<DisturbanceViewHolder> {

    private final DisturbanceViewPresenter presenter;
    private final LayoutInflater inflater;

    public DisturbanceAdapter(Context context, DisturbanceViewPresenter presenter) {
        super();
        this.presenter = presenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DisturbanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_disturbance_row, parent, false);
        return new DisturbanceViewHolder(presenter, view);
    }

    @Override
    public void onBindViewHolder(DisturbanceViewHolder holder, int position) {
        presenter.onBindDisturbanceAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getDisturbanceCount();
    }
}
