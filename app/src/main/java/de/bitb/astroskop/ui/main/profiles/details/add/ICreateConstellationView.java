package de.bitb.astroskop.ui.main.profiles.details.add;

import de.bitb.astroskop.model.Constellation;
import de.bitb.astroskop.ui.base.IBaseView;

interface ICreateConstellationView extends IBaseView{
    void finishConstellation(Constellation constellation);
}
