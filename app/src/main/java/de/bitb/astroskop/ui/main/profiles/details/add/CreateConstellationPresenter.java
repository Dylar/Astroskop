package de.bitb.astroskop.ui.main.profiles.details.add;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.model.Constellation;
import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import de.bitb.astroskop.ui.base.BasePresenter;

public class CreateConstellationPresenter extends BasePresenter<ICreateConstellationView> implements IInjection {

    public CreateConstellationPresenter(AstroApplication application, ICreateConstellationView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void onSaveClicked(Zodiac zodiac, House house, Ruler ruler) {
        Constellation constellation = Constellation.create(zodiac, house, ruler);
        getView().finishConstellation(constellation);
    }

}
