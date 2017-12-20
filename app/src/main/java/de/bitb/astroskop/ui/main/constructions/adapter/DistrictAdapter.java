package de.bitb.astroskop.ui.main.constructions.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.bitb.astroskop.ui.main.constructions.DistrictViewPresenter;
import de.bornholdtlee.snh.R;

public class DistrictAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final DistrictViewPresenter presenter;
    private final LayoutInflater inflater;

    public DistrictAdapter(Context context, DistrictViewPresenter presenter) {
        super();
        this.presenter = presenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_street_row, parent, false);
        return new DistrictViewHolder(presenter, view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        presenter.onBindConstructionAtPosition((IDistrictRowView) holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getConstructionsCount();
    }
}
