package de.bitb.astroskop.ui.base.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import de.bitb.astroskop.ui.base.adapter.AdapterPresenter
import de.bitb.astroskop.ui.base.adapter.BaseViewHolder
import de.bitb.astroskop.ui.base.adapter.IAdapterView

abstract class SingleRecyclerAdapter<M, H : BaseViewHolder<M>>(context: Context, private val presenter: AdapterPresenter<M>) : RecyclerView.Adapter<H>() {
    private val inflater: LayoutInflater

    protected abstract val layoutId: Int

    init {
        this.inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        val view = inflater.inflate(layoutId, parent, false)
        return newHolder(presenter, view)
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        presenter.onBindAtPosition(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.adapterItemCount
    }

    protected abstract fun newHolder(presenter: AdapterPresenter<M>, view: View): H
}