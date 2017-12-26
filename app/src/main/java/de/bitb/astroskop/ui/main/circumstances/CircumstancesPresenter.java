package de.bitb.astroskop.ui.main.circumstances;

import java.util.ArrayList;
import java.util.List;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import de.bitb.astroskop.ui.base.BasePresenter;
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import de.bitb.astroskop.ui.base.adapter.IAdapterView;

public class CircumstancesPresenter extends BasePresenter<ICircumstancesView> implements AdapterPresenter {

    private List<Circumstance> circumstances = new ArrayList<>();

    public CircumstancesPresenter(AstroApplication application, ICircumstancesView view) {
        super(application, view);
    }

    public void onGoClick() {
        circumstances.add(createCircumstance());
        getView().refreshView();
    }

    private Circumstance createCircumstance() {
        Circumstance circumstance = new Circumstance();
        circumstance.setZodiac(Zodiac.getRandom());
        circumstance.setHouse(House.getRandom());
        circumstance.setRuler(Ruler.getRandom());
        return circumstance;
    }

    @Override
    public int getAdapterItemCount() {
        return circumstances.size();
    }

    @Override
    public void onBindAtPosition(IAdapterView holder, int position) {
        holder.bindValues(circumstances.get(position));
    }
}
