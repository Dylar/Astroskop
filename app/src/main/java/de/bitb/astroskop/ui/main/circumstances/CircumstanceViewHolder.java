package de.bitb.astroskop.ui.main.circumstances;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import de.bitb.astroskop.R;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import de.bitb.astroskop.ui.base.adapter.BaseViewHolder;

class CircumstanceViewHolder extends BaseViewHolder<Circumstance> {

    @BindView(R.id.adapter_circumstance_zodiac)
    protected TextView zodiacTV;
    @BindView(R.id.adapter_circumstance_house)
    protected TextView houseTV;
    @BindView(R.id.adapter_circumstance_ruler)
    protected TextView rulerTV;

    public CircumstanceViewHolder(AdapterPresenter<Circumstance> presenter, View view) {
        super(presenter, view);
    }

    @Override
    public void bindValues(Circumstance circumstance) {
        Context context = itemView.getContext();

        Zodiac zodiac = circumstance.getZodiac();
        zodiacTV.setText("Zodiac: " + zodiac.name());
        zodiacTV.setBackgroundColor(ContextCompat.getColor(context, zodiac.getColor()));

        House house = circumstance.getHouse();
        houseTV.setText("House: " + house.name());
        houseTV.setBackgroundColor(ContextCompat.getColor(context, house.getColor()));

        Ruler ruler = circumstance.getRuler();
        rulerTV.setText("Ruler: " + ruler.name());
        List<Zodiac> zodiacs = ruler.getZodiacs();
        if (zodiacs.size() == 1) {
            rulerTV.setBackgroundColor(ContextCompat.getColor(context, zodiacs.get(0).getColor()));
        } else {
            rulerTV.setBackgroundResource(ruler.equals(Ruler.MERCURY) ? R.drawable.color_ruler_mercury : R.drawable.color_ruler_venus);
        }

        itemView.setOnClickListener(view -> getPresenter().onItemClicked(circumstance));
        itemView.setOnLongClickListener(view -> {
            getPresenter().onItemLongClicked(circumstance);
            return true;
        });
    }
}
