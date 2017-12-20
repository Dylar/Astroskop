package de.bitb.astroskop.injection.components;

import dagger.Component;
import de.bitb.astroskop.controller.ConstructionsController;
import de.bitb.astroskop.controller.DisturbanceController;
import de.bitb.astroskop.controller.PowerController;
import de.bitb.astroskop.injection.ApplicationScope;
import de.bitb.astroskop.injection.modules.DatabaseModule;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.main.constructions.ConstructionsViewPresenter;
import de.bitb.astroskop.ui.main.constructions.DistrictViewPresenter;
import de.bitb.astroskop.ui.main.disturbance.DisturbanceViewPresenter;
import de.bitb.astroskop.ui.report.ReportAlternativeFragment;
import de.bitb.astroskop.SNHApplication;
import de.bitb.astroskop.injection.modules.ApplicationModule;
import de.bitb.astroskop.injection.modules.ControllerModule;
import de.bitb.astroskop.injection.modules.NetworkModule;
import de.bitb.astroskop.injection.modules.UtilsModule;
import de.bitb.astroskop.ui.IntroActivity;
import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.ui.main.MainActivity;
import de.bitb.astroskop.ui.main.analysis.AnalysisTabFragment;
import de.bitb.astroskop.ui.main.analysis.data.DataFragment;
import de.bitb.astroskop.ui.main.analysis.data.DataViewPresenter;
import de.bitb.astroskop.ui.main.analysis.map.MapViewPresenter;
import de.bitb.astroskop.ui.main.constructions.ConstructionsFragment;
import de.bitb.astroskop.ui.main.home.HomeViewPresenter;
import de.bitb.astroskop.ui.menu.MenuFragment;
import de.bitb.astroskop.ui.report.ReportPresenter;
import de.bitb.astroskop.ui.report.ReportTabBaseFragment;
import de.bitb.astroskop.ui.report.form.search.ReportFormSearchActivity;
import de.bitb.astroskop.ui.report.form.search.SearchViewPresenter;

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
