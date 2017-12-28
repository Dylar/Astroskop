package de.bitb.astroskop.ui.main.circumstances;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.controller.CircumstanceController;
import de.bitb.astroskop.controller.ControllerCallback;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.ui.base.BasePresenter;
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import de.bitb.astroskop.ui.base.adapter.IAdapterView;

public class CircumstancesPresenter extends BasePresenter<ICircumstancesView> implements AdapterPresenter<Circumstance>, IInjection {

    @Inject
    protected CircumstanceController circumstanceController;

    private List<Circumstance> circumstances = new ArrayList<>();

    public CircumstancesPresenter(AstroApplication application, ICircumstancesView view) {
        super(application, view);
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void onCreate() {
        refreshView();
    }

    public void onGoClick() {
        circumstanceController.createRandomCircumstance();
        refreshView();
    }

    private void refreshView() {
        circumstances = circumstanceController.getAllCircumstances();
        getView().refreshView();
    }

    @Override
    public int getAdapterItemCount() {
        return circumstances.size();
    }

    @Override
    public void onBindAtPosition(IAdapterView<Circumstance> holder, int position) {
        holder.bindValues(circumstances.get(position));
    }

    @Override
    public void onItemClicked(Circumstance model) {
        getView().openCircumstanceDetails(model);
    }

    @Override
    public void onItemLongClicked(Circumstance circumstance) {
        getView().openDeleteDialog(circumstance);
    }

    public void deleteCircumstance(Circumstance circumstance) {
        circumstanceController.delete(circumstance);
        refreshView();
    }

}
