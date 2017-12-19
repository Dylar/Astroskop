package de.bornholdtlee.snh.ui.main.analysis.data;

import com.github.mikephil.charting.data.Entry;

import java.util.List;

import de.bornholdtlee.snh.ui.main.analysis.IAnalysisTabView;
import de.bornholdtlee.snh.ui.main.analysis.IAnalysisView;

public interface IDataView extends IAnalysisTabView, IAnalysisView {

    void updateChart(List<Entry> entries);

    void updateCurrentPower(int powerHamburg, String filterHamburg);

    void openFilter(String selectedFilter);

    void updateLastSync(long updateTimestamp);
}
