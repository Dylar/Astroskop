package de.bitb.astroskop.ui.base.adapter


import android.support.v7.widget.RecyclerView
import android.view.View

import butterknife.ButterKnife
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter
import lombok.Getter

abstract class BaseViewHolder<M>(presenter: AdapterPresenter<*>, view: View) : RecyclerView.ViewHolder(view), IAdapterView<M> {

    @Getter
    val presenter: AdapterPresenter<M>

    init {
        this.presenter = presenter
        ButterKnife.bind(this, view)
    }

    abstract override fun bindValues(model: M)
}
