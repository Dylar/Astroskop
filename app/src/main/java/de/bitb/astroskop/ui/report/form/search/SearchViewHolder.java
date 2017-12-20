package de.bitb.astroskop.ui.report.form.search;


import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import de.bitb.astroskop.model.StreetSearchResult;
import de.bitb.astroskop.ui.base.BaseViewHolder;
import de.bornholdtlee.snh.R;

public class SearchViewHolder extends BaseViewHolder<SearchViewPresenter, StreetSearchResult> implements ISearchRowView {

    @BindView(R.id.adapter_district_row_street)
    TextView streetTV;

    @BindView(R.id.adapter_district_row_size)
    TextView sizeTV;

    @BindView(R.id.adapter_district_row_start)
    TextView startTV;

    @BindView(R.id.adapter_district_row_end)
    TextView endTV;

    @BindView(R.id.adapter_district_row_details)
    View details;

    @BindView(R.id.adapter_district_row_arrow)
    View arrow;

    public SearchViewHolder(SearchViewPresenter presenter, View view) {
        super(presenter, view);
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
        sizeTV.setTypeface(boldTypeface);
        startTV.setTypeface(boldTypeface);
        endTV.setTypeface(boldTypeface);
    }

    @Override
    public void bindValues(StreetSearchResult result) {
        arrow.setVisibility(View.GONE);
        streetTV.setText(result.getStreet());

        itemView.setOnClickListener(view -> getPresenter().selectStreet(result.getStreet(), result.getCity()));
    }
}
