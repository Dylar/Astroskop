package de.bitb.astroskop.ui.main.constructions;

import java.util.List;

import javax.inject.Inject;

import de.bornholdtlee.snh.R;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.api.ControllerCallback;
import de.bitb.astroskop.controller.ConstructionsController;
import de.bitb.astroskop.injection.IInjection;
import de.bitb.astroskop.injection.components.AppComponent;
import de.bitb.astroskop.model.Construction;
import de.bitb.astroskop.ui.base.BasePresenter;
import de.bitb.astroskop.ui.main.constructions.adapter.IConstructionRowView;
import de.bitb.astroskop.utils.NetworkUtils;

public class ConstructionsViewPresenter extends BasePresenter<IConstructionsView> implements IInjection {

    @Inject
    protected ConstructionsController constructionsController;

    @Inject
    protected NetworkUtils networkUtils;

    private List<String> districts;

    public ConstructionsViewPresenter(SNHApplication application, IConstructionsView view) {
        super(application, view);
    }

    public void refreshView() {
        if (networkUtils.isNetworkAvailable()) {
            if (districts.isEmpty()) {
                getView().showListEmpty();
            } else {
                getView().refreshView();
            }
        } else {
            getView().showNetworkErrorScreen();
        }
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    public void onResume() {
        getTracker().trackScreenView(getContext().getString(R.string.tracker_constructions));
        tryLoadConstructions();
    }

    public void tryLoadConstructions() {
        districts = constructionsController.getDistrictsList();
        constructionsController.loadAllConstructions(new ControllerCallback<Construction>() {
            @Override
            public void onSuccess(List<Construction> response) {
                districts = constructionsController.getDistrictsList();
                refreshView();
            }

            @Override
            public void onFailure() {
                getView().showNetworkErrorScreen();
            }
        });
    }

    public int getConstructionsCount() {
        return districts.size();
    }

    public void onBindConstructionAtPosition(IConstructionRowView holder, int position) {
        holder.bindValues(districts.get(position));
    }

    public void onDistrictClicked(String district) {
        getView().openDistrictView(district);
    }

}
