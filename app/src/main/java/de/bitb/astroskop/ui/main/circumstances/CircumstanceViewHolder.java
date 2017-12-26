package de.bitb.astroskop.ui.main.circumstances;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import de.bitb.astroskop.R;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import de.bitb.astroskop.ui.base.adapter.BaseViewHolder;

class CircumstanceViewHolder extends BaseViewHolder<Circumstance> {

    @BindView(R.id.adapter_circumstance_zodiac)
    protected TextView zodiacTV;
    @BindView(R.id.adapter_circumstance_house)
    protected TextView houseTV;
    @BindView(R.id.adapter_circumstance_ruler)
    protected TextView ruleTV;

    public CircumstanceViewHolder(AdapterPresenter presenter, View view) {
        super(presenter, view);
    }

    @Override
    public void bindValues(Circumstance construction) {
        zodiacTV.setText("Zodiac: " + construction.getZodiac().name());
        zodiacTV.setText("House: " + construction.getHouse().name());
        zodiacTV.setText("Ruler: " + construction.getRuler().name());
    }

}
