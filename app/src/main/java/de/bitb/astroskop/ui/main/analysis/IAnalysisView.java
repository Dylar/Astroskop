package de.bitb.astroskop.ui.main.analysis;

import de.bitb.astroskop.ui.base.IBaseView;

public interface IAnalysisView extends IBaseView {
    void showNoNetworkError();

    void hideNoNetworkError();
}
