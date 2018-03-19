package de.bitb.astroskop.ui.main


import de.bitb.astroskop.ui.base.IBaseView

interface IMainView : IBaseView {
    fun navigateToHomeScreen()

    fun navigateToCircumstanceScreen()

    fun navigateToScreen(tabId: Int)
}
