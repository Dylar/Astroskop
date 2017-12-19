package de.bornholdtlee.snh.ui.main.constructions.adapter;


import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.model.Street;
import de.bornholdtlee.snh.ui.base.BaseViewHolder;
import de.bornholdtlee.snh.ui.main.constructions.DistrictViewPresenter;

public class DistrictViewHolder extends BaseViewHolder<DistrictViewPresenter, Street> implements IDistrictRowView {

    @BindView(R.id.adapter_district_row_street)
    protected TextView streetTV;
    @BindView(R.id.adapter_district_row_size)
    protected TextView sizeTV;
    @BindView(R.id.adapter_district_row_start)
    protected TextView startTV;
    @BindView(R.id.adapter_district_row_end)
    protected TextView endTV;
    @BindView(R.id.adapter_district_row_details)
    protected View details;
    @BindView(R.id.adapter_district_row_arrow)
    protected View arrow;

    private Typeface boldTypeface;

    public DistrictViewHolder(DistrictViewPresenter presenter, View view) {
        super(presenter, view);
        boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
        sizeTV.setTypeface(boldTypeface);
        startTV.setTypeface(boldTypeface);
        endTV.setTypeface(boldTypeface);
    }

    @Override
    public void bindValues(Street street) {
        openStreet(street, getPresenter().isStreetOpen(street));

        streetTV.setText(street.getName() + " " + street.getNumber());
        sizeTV.setText(street.getConstructionsSize());
        startTV.setText(street.getConstructionsStart());
        endTV.setText(street.getConstructionsEnd());

        itemView.setOnClickListener(view -> {
            openStreet(street, getPresenter().isStreetOpen(street));
        });
    }

    private void openStreet(Street street, boolean open) {
        getPresenter().openStreet(street);
        details.setVisibility(open ? View.VISIBLE : View.GONE);
        streetTV.setTextColor(ContextCompat.getColor(itemView.getContext(), open ? R.color.text_blue : R.color.text_gray));
        arrow.setBackgroundTintList(itemView.getResources().getColorStateList(open ? R.color.icon_blue : R.color.icon_gray));
        arrow.setRotation(open ? 90 : 180);
        streetTV.setTypeface(open ? boldTypeface : null);
    }
}
