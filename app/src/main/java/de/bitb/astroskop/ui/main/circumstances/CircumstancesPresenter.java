package de.bitb.astroskop.ui.main.circumstances;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import de.bitb.astroskop.ui.base.BasePresenter;

public class CircumstancesPresenter extends BasePresenter<ICircumstancesView>{


    public CircumstancesPresenter(AstroApplication application, ICircumstancesView view) {
        super(application, view);
    }

    public void onGoClick() {
        getView().refreshView(createCircumstance());
    }

    private Circumstance createCircumstance() {
        Circumstance circumstance = new Circumstance();
        circumstance.setZodiac(Zodiac.getRandom());
        circumstance.setHouse(House.getRandom());
        circumstance.setRuler(Ruler.getRandom());
        return circumstance;
    }
}
