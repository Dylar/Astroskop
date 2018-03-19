package de.bitb.astroskop.ui.main.profiles.details.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

import butterknife.BindView
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.R
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.model.Constellation
import de.bitb.astroskop.model.enums.House
import de.bitb.astroskop.model.enums.Ruler
import de.bitb.astroskop.model.enums.Zodiac
import de.bitb.astroskop.ui.base.ActionbarHandler
import de.bitb.astroskop.ui.base.IToolbarView
import de.bitb.astroskop.ui.base.MVPActivity

import de.bitb.astroskop.Constants.NULL_INTEGER

class CreateConstellationActivity : MVPActivity<ICreateConstellationView, CreateConstellationPresenter>(), IToolbarView, ICreateConstellationView, IBind {

    @BindView(R.id.create_constellation_zodiac)
    var zodiacSpinner: Spinner? = null
    @BindView(R.id.create_constellation_house)
    var houseSpinner: Spinner? = null
    @BindView(R.id.create_constellation_ruler)
    var rulerSpinner: Spinner? = null

    override val layoutId: Int
        get() = R.layout.activity_create_constellation

    override val actionbarCallback: ActionbarHandler.ActionbarCallback
        get() = object : ActionbarHandler.ActionbarCallback() {

            override val actionbarButton1Icon: Int
                get() = R.drawable.ic_smile

            override fun onActionbarButton1Clicked(): Boolean {
                presenter!!.onSaveClicked(zodiacSpinner!!.selectedItem as Zodiac, houseSpinner!!.selectedItem as House, rulerSpinner!!.selectedItem as Ruler)
                return true
            }
        }

    override fun createPresenter(application: BaseApplication): CreateConstellationPresenter {
        return CreateConstellationPresenter(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zodiacSpinner!!.adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                Zodiac.values())
        houseSpinner!!.adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                House.values())
        houseSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                resetSpinner(rulerSpinner)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                //nothing
            }
        }

        rulerSpinner!!.adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                Ruler.values())
        rulerSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                resetSpinner(houseSpinner)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {
                //nothing
            }
        }
    }

    private fun resetSpinner(spinner: Spinner?) {
        spinner!!.setSelection(0)
    }

    override fun finishConstellation(constellation: Constellation?) {
        if (constellation != null) {
            val data = Intent()
            data.putExtra(KEY_CONSTELLATION, constellation)
            setResult(Activity.RESULT_OK, data)
        }
        finish()
    }

    companion object {

        val REQUEST_CREATE_CONSTELLATION = 666
        val KEY_CONSTELLATION = "key_constellation"

        fun startActivity(activity: Activity) {
            val intent = Intent(activity, CreateConstellationActivity::class.java)
            activity.startActivityForResult(intent, REQUEST_CREATE_CONSTELLATION)
        }
    }
}
