package de.bitb.astroskop.ui.base.adapter

interface AdapterPresenter<M> {

    val adapterItemCount: Int

    fun onBindAtPosition(holder: IAdapterView<M>, position: Int)

    fun onItemClicked(model: M)

    fun onItemLongClicked(model: M)
}
