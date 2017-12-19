package de.bornholdtlee.snh.ui.report.form.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.api.ControllerCallback;
import de.bornholdtlee.snh.model.NumberSearchResult;
import de.bornholdtlee.snh.ui.report.ReportPresenter;

public class SearchNumberAdapter extends BaseAdapter implements Filterable {

    private final LayoutInflater inflater;
    private final ReportPresenter presenter;
    private List<NumberSearchResult> resultList = new ArrayList<>();

    public SearchNumberAdapter(Context context, ReportPresenter presenter) {
        super();
        this.presenter = presenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View content = view;
        if (content == null) {
            content = inflater.inflate(R.layout.adapter_street_row, viewGroup, false);
            content.findViewById(R.id.adapter_district_row_arrow).setVisibility(View.GONE);
        }
        ((TextView) content.findViewById(R.id.adapter_district_row_street)).setText(getItem(i).get(0));
        return content;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public NumberSearchResult getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    presenter.searchNumber(constraint.toString(), new ControllerCallback<NumberSearchResult>() {
                        @Override
                        public void onSuccess(List<NumberSearchResult> response) {
                            filterResults.values = response;
                            filterResults.count = response.size();
                            publishResults(constraint, filterResults);
                        }

                        @Override
                        public void onSuccess(NumberSearchResult response) {
                            //nothing
                        }

                        @Override
                        public void onFailure() {
                            //do nothing
                        }
                    });

                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    resultList = (List<NumberSearchResult>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }
}
