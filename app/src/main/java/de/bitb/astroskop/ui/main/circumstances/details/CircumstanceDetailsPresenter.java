package de.bitb.astroskop.ui.main.circumstances.details;

import javax.inject.Inject;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.controller.CircumstanceController;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.ui.base.BasePresenter;

public class CircumstanceDetailsPresenter extends BasePresenter<ICircumstancesDetailView> implements IInjection {

    @Inject
    protected CircumstanceController circumstanceController;

    private Circumstance circumstance;

    public CircumstanceDetailsPresenter(AstroApplication application, ICircumstancesDetailView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void onCreate(String circumstanceUuid) {
        circumstance = circumstanceController.getByUUID(circumstanceUuid);
        getView().initZodiacInfo(circumstance.getZodiac());
        getView().initHouseInfo(circumstance.getHouse());
        getView().initRulerInfo(circumstance.getRuler());

    }

}
