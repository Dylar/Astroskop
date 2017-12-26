package de.bitb.astroskop.ui.main.circumstances;

import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.ui.base.IBaseView;

public interface ICircumstancesView extends IBaseView{

    void refreshView(Circumstance circumstance);
}
