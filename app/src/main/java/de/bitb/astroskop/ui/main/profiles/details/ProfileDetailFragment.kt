package de.bitb.astroskop.ui.main.profiles.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import butterknife.BindView
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.R
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.model.Constellation
import de.bitb.astroskop.model.Profile
import de.bitb.astroskop.ui.base.ActionbarHandler
import de.bitb.astroskop.ui.base.MVPFragment
import de.bitb.astroskop.ui.main.profiles.details.add.CreateConstellationActivity

import android.app.Activity.RESULT_OK
import de.bitb.astroskop.model.enums.Zodiac
import de.bitb.astroskop.ui.main.profiles.details.ProfileActivity.Companion.KEY_PROFILE_UUID
import de.bitb.astroskop.ui.main.profiles.details.add.CreateConstellationActivity.Companion.REQUEST_CREATE_CONSTELLATION

class ProfileDetailFragment : MVPFragment<IProfileDetailView, ProfileDetailPresenter>(), IProfileDetailView, IBind {

    @BindView(R.id.fragment_profile_detail_aries_list)
    var ariesList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_taurus_list)
    var taurusList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_gemini_list)
    var geminiList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_cancer_list)
    var cancerList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_leo_list)
    var leoList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_virgo_list)
    var virgoList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_libra_list)
    var libraList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_scorpio_list)
    var scorpioList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_sagittarius_list)
    var sagittariusList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_capricorn_list)
    var capricornList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_aquarius_list)
    var aquariusList: ViewGroup? = null
    @BindView(R.id.fragment_profile_detail_pisces_list)
    var piscesList: ViewGroup? = null

    override var actionbarCallback: ActionbarHandler.ActionbarCallback = object : ActionbarHandler.ActionbarCallback() {
            override val actionbarButton1Icon: Int
                get() = android.R.drawable.ic_menu_add

            override fun onActionbarButton1Clicked(): Boolean {
                CreateConstellationActivity.startActivity(activity!!)
                return true
            }
        }

    override val layoutId: Int
        get() = R.layout.fragment_profile_detail

    override fun createPresenter(application: BaseApplication): ProfileDetailPresenter {
        return ProfileDetailPresenter(application, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter!!.onCreate(arguments!!.getString(KEY_PROFILE_UUID))
    }

    override fun initProfile(profile: Profile) {
        val constellations = profile.constellations
        ariesList!!.removeAllViews()
        taurusList!!.removeAllViews()
        geminiList!!.removeAllViews()
        cancerList!!.removeAllViews()
        leoList!!.removeAllViews()
        virgoList!!.removeAllViews()
        libraList!!.removeAllViews()
        scorpioList!!.removeAllViews()
        sagittariusList!!.removeAllViews()
        capricornList!!.removeAllViews()
        aquariusList!!.removeAllViews()
        piscesList!!.removeAllViews()
        for (constellation in constellations!!) {
            var viewGroup: ViewGroup? = null
            when (constellation.zodiac) {
                Zodiac.ARIES -> viewGroup = ariesList
                Zodiac.TAURUS -> viewGroup = taurusList
                Zodiac.GEMINI -> viewGroup = geminiList
                Zodiac.CANCER -> viewGroup = cancerList
                Zodiac.LEO -> viewGroup = leoList
                Zodiac.VIRGO -> viewGroup = virgoList
                Zodiac.LIBRA -> viewGroup = libraList
                Zodiac.SCORPIO -> viewGroup = scorpioList
                Zodiac.SAGITTARIUS -> viewGroup = sagittariusList
                Zodiac.CAPRICORN -> viewGroup = capricornList
                Zodiac.AQUARIUS -> viewGroup = aquariusList
                Zodiac.PISCES -> viewGroup = piscesList
            }
            val isHouse = constellation.isHouse
            val view = LayoutInflater.from(context).inflate(R.layout.profile_detail_constellation_list_item, null)
            var tv = view.findViewById<TextView>(R.id.profile_detail_list_item_label)
            tv.text = if (isHouse) "House:" else "Ruler: "
            tv = view.findViewById(R.id.profile_detail_list_item_text)
            tv.text = if (isHouse) constellation.house!!.name else constellation.ruler!!.name

            viewGroup!!.addView(view)
        }
    }

    override fun refreshView() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CREATE_CONSTELLATION) {
            presenter!!.addConstellation(data!!.getSerializableExtra(CreateConstellationActivity.KEY_CONSTELLATION) as Constellation)
        }
    }

    companion object {

        fun createInstance(uuid: String): ProfileDetailFragment {
            val frag = ProfileDetailFragment()
            val args = Bundle()
            args.putString(KEY_PROFILE_UUID, uuid)
            frag.arguments = args
            return frag
        }
    }
}
