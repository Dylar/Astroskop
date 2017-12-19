package de.bornholdtlee.snh.ui.main.constructions.adapter;


import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.ui.base.BaseViewHolder;
import de.bornholdtlee.snh.ui.main.constructions.ConstructionsViewPresenter;

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
