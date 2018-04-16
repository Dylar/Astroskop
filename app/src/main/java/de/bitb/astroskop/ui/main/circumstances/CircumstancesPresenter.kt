package de.bitb.astroskop.ui.main.circumstances

import java.util.ArrayList

import javax.inject.Inject

import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.controller.CircumstanceController
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.injection.components.AppComponent
import de.bitb.astroskop.model.Circumstance
import de.bitb.astroskop.ui.base.BasePresenter
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter
import de.bitb.astroskop.ui.base.adapter.IAdapterView

class CircumstancesPresenter(application: BaseApplication, view: ICircumstancesView) : BasePresenter<ICircumstancesView>(application, view), AdapterPresenter<Circumstance>, IInjection {

    @Inject
    var circumstanceController: CircumstanceController? = null

    private var circumstances: List<Circumstance> = ArrayList()

    override val adapterItemCount: Int
        get() = circumstances.size

    override fun inject(appComponent: AppComponent?) {
        appComponent?.inject(this)
    }

    fun onCreate() {
        refreshView()
    }

    fun onGoClick() {
        circumstanceController!!.createRandomCircumstance()
        refreshView()
    }

    private fun refreshView() {
        circumstances = circumstanceController!!.allCircumstances
        getView().refreshView()
    }

    override fun onBindAtPosition(holder: IAdapterView<Circumstance>, position: Int) {
        holder.bindValues(circumstances[position])
    }

    override fun onItemClicked(model: Circumstance) {
        getView().openCircumstanceDetails(model)
    }

    override fun onItemLongClicked(circumstance: Circumstance) {
        getView().openDeleteDialog(circumstance)
    }

    fun deleteCircumstance(circumstance: Circumstance) {
        circumstanceController!!.delete(circumstance)
        refreshView()
    }

}
