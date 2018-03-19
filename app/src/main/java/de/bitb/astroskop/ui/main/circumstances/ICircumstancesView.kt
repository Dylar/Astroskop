package de.bitb.astroskop.ui.main.circumstances

import de.bitb.astroskop.model.Circumstance
import de.bitb.astroskop.ui.base.IBaseView

interface ICircumstancesView : IBaseView {

    fun refreshView()

    fun openCircumstanceDetails(circumstance: Circumstance)

    fun openDeleteDialog(circumstance: Circumstance)
}
