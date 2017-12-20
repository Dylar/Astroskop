package de.bitb.astroskop.ui.main.constructions.adapter;


import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import de.bitb.astroskop.ui.base.BaseViewHolder;
import de.bitb.astroskop.ui.main.constructions.ConstructionsViewPresenter;
import de.bornholdtlee.snh.R;

class ConstructionsViewHolder extends BaseViewHolder<ConstructionsViewPresenter, String> implements IConstructionRowView {

    @BindView(R.id.adapter_constructions_row_text)
    TextView districtTV;

    public ConstructionsViewHolder(ConstructionsViewPresenter presenter, View view) {
        super(presenter, view);
    }

    @Override
    public void bindValues(String district) {
        districtTV.setText(district);
        itemView.setOnClickListener(view -> getPresenter().onDistrictClicked(district));
    }
}
