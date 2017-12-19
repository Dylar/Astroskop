package de.bornholdtlee.snh.ui.main.analysis.map;


import de.bornholdtlee.snh.ui.main.analysis.IAnalysisTabView;
import de.bornholdtlee.snh.ui.main.analysis.IAnalysisView;

public interface IMapView extends IAnalysisTabView, IAnalysisView {

    void tintAltona(int consumption);

    void tintEimsbuettel(int consumption);

    void tintNord(int consumption);

    void tintWandsbek(int consumption);

    void tintBergedorf(int consumption);

    void tintHarburg(int consumption);

    void tintMitte(int consumption);

}
