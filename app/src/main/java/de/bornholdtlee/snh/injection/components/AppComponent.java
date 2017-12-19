package de.bornholdtlee.snh.injection.components;

import dagger.Component;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.controller.ConstructionsController;
import de.bornholdtlee.snh.controller.DisturbanceController;
import de.bornholdtlee.snh.controller.PowerController;
import de.bornholdtlee.snh.injection.ApplicationScope;
import de.bornholdtlee.snh.injection.modules.ApplicationModule;
import de.bornholdtlee.snh.injection.modules.ControllerModule;
import de.bornholdtlee.snh.injection.modules.DatabaseModule;
import de.bornholdtlee.snh.injection.modules.NetworkModule;
import de.bornholdtlee.snh.injection.modules.UtilsModule;
import de.bornholdtlee.snh.ui.IntroActivity;
import de.bornholdtlee.snh.ui.base.BaseActivity;
import de.bornholdtlee.snh.ui.base.BaseFragment;
import de.bornholdtlee.snh.ui.main.MainActivity;
import de.bornholdtlee.snh.ui.main.analysis.AnalysisTabFragment;
import de.bornholdtlee.snh.ui.main.analysis.data.DataFragment;
import de.bornholdtlee.snh.ui.main.analysis.data.DataViewPresenter;
import de.bornholdtlee.snh.ui.main.analysis.map.MapViewPresenter;
import de.bornholdtlee.snh.ui.main.constructions.ConstructionsFragment;
import de.bornholdtlee.snh.ui.main.constructions.ConstructionsViewPresenter;
import de.bornholdtlee.snh.ui.main.constructions.DistrictViewPresenter;
import de.bornholdtlee.snh.ui.main.disturbance.DisturbanceViewPresenter;
import de.bornholdtlee.snh.ui.main.home.HomeViewPresenter;
import de.bornholdtlee.snh.ui.menu.MenuFragment;
import de.bornholdtlee.snh.ui.report.ReportAlternativeFragment;
import de.bornholdtlee.snh.ui.report.ReportPresenter;
import de.bornholdtlee.snh.ui.report.ReportTabBaseFragment;
import de.bornholdtlee.snh.ui.report.form.search.ReportFormSearchActivity;
import de.bornholdtlee.snh.ui.report.form.search.SearchViewPresenter;

@ApplicationScope
@Component(modules = {ApplicationModule.class, ControllerModule.class, UtilsModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent {

    void inject(SNHApplication application);
    void inject(DisturbanceController disturbanceController);
    void inject(ConstructionsController constructionsController);
    void inject(PowerController powerController);

    void inject(BaseActivity baseActivity);
    void inject(IntroActivity introActivity);
    void inject(MainActivity mainActivity);
    void inject(ReportFormSearchActivity reportFormSearchActivity);
    void inject(ReportAlternativeFragment reportAlternativeFragment);

    void inject(BaseFragment baseFragment);
    void inject(MenuFragment menuFragment);
    void inject(ConstructionsFragment constructionsFragment);
    void inject(ReportTabBaseFragment reportTabBaseFragment);
    void inject(AnalysisTabFragment analysisTabFragment);
    void inject(DataFragment dataFragment);

    void inject(HomeViewPresenter homeViewPresenter);
    void inject(ConstructionsViewPresenter constructionsViewPresenter);
    void inject(DistrictViewPresenter districtViewPresenter);
    void inject(DisturbanceViewPresenter disturbanceViewPresenter);
    void inject(ReportPresenter reportPresenter);
    void inject(SearchViewPresenter searchViewPresenter);
    void inject(MapViewPresenter mapViewPresenter);
    void inject(DataViewPresenter dataViewPresenter);

}
