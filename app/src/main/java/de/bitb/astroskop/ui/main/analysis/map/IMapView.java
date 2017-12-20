package de.bitb.astroskop.ui.main.analysis.map;


import de.bitb.astroskop.ui.main.analysis.IAnalysisTabView;
import de.bitb.astroskop.ui.main.analysis.IAnalysisView;

public interface IMapView extends IAnalysisTabView, IAnalysisView {

    void tintAltona(int consumption);

    void tintEimsbuettel(int consumption);

    void tintNord(int consumption);

    void tintWandsbek(int consumption);

    void tintBergedorf(int consumption);

    void tintHarburg(int consumption);

    void tintMitte(int consumption);

}
