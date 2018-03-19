package de.bitb.astroskop.ui.main.circumstances

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView

import butterknife.BindView
import de.bitb.astroskop.R
import de.bitb.astroskop.model.Circumstance
import de.bitb.astroskop.model.enums.House
import de.bitb.astroskop.model.enums.Ruler
import de.bitb.astroskop.model.enums.Zodiac
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter
import de.bitb.astroskop.ui.base.adapter.BaseViewHolder

internal class CircumstanceViewHolder(presenter: AdapterPresenter<Circumstance>, view: View) : BaseViewHolder<Circumstance>(presenter, view) {

    @BindView(R.id.adapter_circumstance_zodiac)
    var zodiacTV: TextView? = null
    @BindView(R.id.adapter_circumstance_house)
    var houseTV: TextView? = null
    @BindView(R.id.adapter_circumstance_ruler)
    var rulerTV: TextView? = null

    override fun bindValues(circumstance: Circumstance) {
        val context = itemView.context

        val zodiac = circumstance.zodiac
        zodiacTV!!.text = "Zodiac: " + zodiac.name
        zodiacTV!!.setBackgroundColor(ContextCompat.getColor(context, zodiac.color))

        val house = circumstance.house
        houseTV!!.text = "House: " + house.name
        houseTV!!.setBackgroundColor(ContextCompat.getColor(context, house.color))

        val ruler = circumstance.ruler
        rulerTV!!.text = "Ruler: " + ruler.name
        val zodiacs = ruler.zodiacs
        if (zodiacs.size == 1) {
            rulerTV!!.setBackgroundColor(ContextCompat.getColor(context, zodiacs[0].color))
        } else {
            rulerTV!!.setBackgroundResource(if (ruler == Ruler.MERCURY) R.drawable.color_ruler_mercury else R.drawable.color_ruler_venus)
        }

        itemView.setOnClickListener { view -> presenter.onItemClicked(circumstance) }
        itemView.setOnLongClickListener { view ->
            presenter.onItemLongClicked(circumstance)
            true
        }
    }
}
