package de.bitb.astroskop.ui.main.circumstances.details

import android.os.Bundle
import android.view.View
import android.widget.TextView

import java.util.ArrayList

import butterknife.BindView
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.R
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.model.enums.House
import de.bitb.astroskop.model.enums.Ruler
import de.bitb.astroskop.model.enums.Zodiac
import de.bitb.astroskop.ui.base.BaseFragment

import de.bitb.astroskop.ui.main.circumstances.details.CircumstanceDetailsActivity.Companion.KEY_CIRCUMSTANCE_UUID

class CircumstanceDetailsFragment : BaseFragment(), IBind, ICircumstancesDetailView {
    @BindView(R.id.fragment_details_info_zodiac)
    var infosZodiac: View? = null
    @BindView(R.id.fragment_details_info_house)
    var infosHouse: View? = null
    @BindView(R.id.fragment_details_info_ruler)
    var infosRuler: View? = null

    private var detailsPresenter: CircumstanceDetailsPresenter? = null

    override val layoutId: Int
        get() = R.layout.fragment_circumstances_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsPresenter = CircumstanceDetailsPresenter(activity!!.application as BaseApplication, this)
        detailsPresenter!!.onCreate(arguments!!.getString(KEY_CIRCUMSTANCE_UUID))
    }

    override fun initZodiacInfo(zodiac: Zodiac) {
        initInfo(zodiac, infosZodiac)
    }

    override fun initHouseInfo(house: House) {
        initInfo(house.zodiac, infosHouse)
    }

    override fun initRulerInfo(ruler: Ruler) {
        initInfo(ruler.zodiacs, infosRuler)
    }

    private fun initInfo(zodiac: Zodiac?, infoView: View?) {
        val zodiacs = ArrayList<Zodiac>()
        zodiacs.add(zodiac!!)
        initInfo(zodiacs, infoView)
    }

    private fun initInfo(zodiacs: List<Zodiac>, infoView: View?) {
        val zodiac = zodiacs[0]

        var zodiacName = zodiac.name
        var houseName = zodiac.house.name
        var rulerName = zodiac.ruler.name
        var elementName = zodiac.element.name
        var qualityName = zodiac.quality.name
        var genderName = zodiac.gender.name

        if (zodiacs.size == 2) {
            val zodiac2 = zodiacs[1]
            zodiacName += ", " + zodiac2.name
            houseName += ", " + zodiac2.house.name
            rulerName += ", " + zodiac2.ruler.name
            elementName += ", " + zodiac2.element.name
            qualityName += ", " + zodiac2.quality.name
            genderName += ", " + zodiac2.gender.name
        }

        var textView = infoView!!.findViewById<TextView>(R.id.detail_info_zodiac)
        textView.text = zodiacName
        textView = infoView.findViewById(R.id.detail_info_house)
        textView.text = houseName
        textView = infoView.findViewById(R.id.detail_info_ruler)
        textView.text = rulerName
        textView = infoView.findViewById(R.id.detail_info_element)
        textView.text = elementName
        textView = infoView.findViewById(R.id.detail_info_quality)
        textView.text = qualityName
        textView = infoView.findViewById(R.id.detail_info_gender)
        textView.text = genderName
    }

    companion object {

        fun createInstance(uuid: String): CircumstanceDetailsFragment {
            val frag = CircumstanceDetailsFragment()
            val args = Bundle()
            args.putString(KEY_CIRCUMSTANCE_UUID, uuid)
            frag.arguments = args
            return frag
        }
    }
}
