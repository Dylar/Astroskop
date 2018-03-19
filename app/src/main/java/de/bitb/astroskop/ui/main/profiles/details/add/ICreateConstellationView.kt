package de.bitb.astroskop.ui.main.profiles.details.add

import de.bitb.astroskop.model.Constellation
import de.bitb.astroskop.ui.base.IBaseView

internal interface ICreateConstellationView : IBaseView {
    fun finishConstellation(constellation: Constellation)
}
