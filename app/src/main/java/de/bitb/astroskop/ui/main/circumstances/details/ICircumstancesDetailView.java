package de.bitb.astroskop.ui.main.circumstances.details;

import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import de.bitb.astroskop.ui.base.IBaseView;

interface ICircumstancesDetailView extends IBaseView{
    void initZodiacInfo(Zodiac zodiac);

    void initHouseInfo(House house);

    void initRulerInfo(Ruler ruler);
}
