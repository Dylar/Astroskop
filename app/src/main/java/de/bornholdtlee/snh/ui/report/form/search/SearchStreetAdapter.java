package de.bornholdtlee.snh.ui.report.form.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.bornholdtlee.snh.R;

public class SearchStreetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final SearchViewPresenter presenter;
    private final LayoutInflater inflater;

    public SearchStreetAdapter(Context context, SearchViewPresenter presenter) {
        super();
        this.presenter = presenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_street_row, parent, false);
        return new SearchViewHolder(presenter, view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        presenter.onBindSearchAtPosition((ISearchRowView) holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getSearchCount();
    }
}
