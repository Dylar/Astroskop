package de.bitb.astroskop.ui.main.circumstances

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import butterknife.BindView
import butterknife.OnClick
import de.bitb.astroskop.BaseApplication
import de.bitb.astroskop.R
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.model.Circumstance
import de.bitb.astroskop.ui.base.NavigationBaseFragment
import de.bitb.astroskop.ui.base.adapter.SingleRecyclerAdapter
import de.bitb.astroskop.ui.main.MainActivity
import de.bitb.astroskop.ui.main.circumstances.details.CircumstanceDetailsActivity

class CircumstanceFragment : NavigationBaseFragment(), IBind, ICircumstancesView {

    @BindView(R.id.fragment_circumstances_result)
    var recyclerView: RecyclerView? = null

    private var circumstanceAdapter: SingleRecyclerAdapter<*, *>? = null

    private var presenter: CircumstancesPresenter? = null

    override val layoutId: Int
        get() = R.layout.fragment_circumstances

    override val navigationPosition: Int
        get() = MainActivity.TAB_CIRCUMSTANCES

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.presenter = CircumstancesPresenter(activity!!.application as BaseApplication, this)
        initRecyclerView()
        presenter!!.onCreate()
    }

    private fun initRecyclerView() {
        recyclerView!!.layoutManager = GridLayoutManager(context, 2)
        circumstanceAdapter = CircumstanceAdapter(context, presenter)
        recyclerView!!.adapter = circumstanceAdapter
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle("Circumstance")
    }

    @OnClick(R.id.fragment_circumstances_button_go)
    fun onGoClicked() {
        presenter!!.onGoClick()
    }

    override fun refreshView() {
        circumstanceAdapter!!.notifyDataSetChanged()
    }

    override fun openCircumstanceDetails(model: Circumstance) {
        CircumstanceDetailsActivity.startActivity(context, model.uuid)
    }

    override fun openDeleteDialog(circumstance: Circumstance) {
        dialogBuilder!!.showDeleteDialog(context) { dialogInterface, i -> presenter!!.deleteCircumstance(circumstance) }
    }

    companion object {

        fun createInstance(): CircumstanceFragment {
            return CircumstanceFragment()
        }
    }
}