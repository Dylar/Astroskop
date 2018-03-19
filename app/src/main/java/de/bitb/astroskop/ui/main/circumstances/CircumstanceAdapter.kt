package de.bitb.astroskop.ui.main.circumstances

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

import de.bitb.astroskop.R
import de.bitb.astroskop.model.Circumstance
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter
import de.bitb.astroskop.ui.base.adapter.SingleRecyclerAdapter

class CircumstanceAdapter(context: Context, presenter: AdapterPresenter<Circumstance>) : SingleRecyclerAdapter<Circumstance, CircumstanceViewHolder>(context, presenter) {

    protected override val layoutId: Int
        get() = R.layout.adapter_circumstance_item

    override fun newHolder(presenter: AdapterPresenter<Circumstance>, view: View): CircumstanceViewHolder {
        return CircumstanceViewHolder(presenter, view)
    }

}
