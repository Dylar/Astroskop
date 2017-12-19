package de.bornholdtlee.snh.ui.main.constructions.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.ui.main.constructions.ConstructionsViewPresenter;

public class ConstructionsAdapter extends RecyclerView.Adapter<ConstructionsViewHolder> {

    private final ConstructionsViewPresenter presenter;
    private final LayoutInflater inflater;

    public ConstructionsAdapter(Context context, ConstructionsViewPresenter presenter) {
        super();
        this.presenter = presenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ConstructionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_constructions_row, parent, false);
        return new ConstructionsViewHolder(presenter, view);
    }

    @Override
    public void onBindViewHolder(ConstructionsViewHolder holder, int position) {
        presenter.onBindConstructionAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getConstructionsCount();
    }
}
