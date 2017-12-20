package de.bitb.astroskop.ui.main.disturbance.adapter;


import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import de.bitb.astroskop.model.Disturbance;
import de.bitb.astroskop.ui.base.BaseViewHolder;
import de.bitb.astroskop.ui.main.disturbance.DisturbanceViewPresenter;
import de.bornholdtlee.snh.R;

public class DisturbanceViewHolder extends BaseViewHolder<DisturbanceViewPresenter, Disturbance> implements IDisturbanceRowView {

    @BindView(R.id.adapter_disturbance_row_title)
    protected TextView title;
    @BindView(R.id.adapter_disturbance_row_date)
    protected TextView date;
    @BindView(R.id.adapter_disturbance_row_date_start)
    protected TextView dateStart;
    @BindView(R.id.adapter_disturbance_row_date_end)
    protected TextView dateEnd;

    public DisturbanceViewHolder(DisturbanceViewPresenter presenter, View view) {
        super(presenter, view);
    }

    @Override
    public void bindValues(Disturbance disturbance) {
        String districtString = "";
        for (String district : disturbance.getDistrict()) {
            if (!TextUtils.isEmpty(districtString)) {
                districtString += ", ";
            }
            districtString += district;
        }
        boolean plural = disturbance.getDistrict().size() > 1;
        title.setText(plural ? itemView.getResources().getText(R.string.fragment_disturbance_row_header) : districtString);
        date.setText(disturbance.getDate());
        dateStart.setText(Html.fromHtml(itemView.getContext().getString(R.string.fragment_disturbance_date_start, disturbance.getStart())));
        dateEnd.setText(Html.fromHtml(itemView.getContext().getString(
                plural
                        ? R.string.fragment_disturbance_date_end_plural
                        : R.string.fragment_disturbance_date_end, districtString, disturbance.getEnd())));
    }
}
