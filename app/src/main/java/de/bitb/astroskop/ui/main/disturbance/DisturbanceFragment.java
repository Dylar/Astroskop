package de.bitb.astroskop.ui.main.disturbance;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.ui.base.NavigationBaseFragment;
import de.bitb.astroskop.ui.main.IMainView;
import de.bitb.astroskop.ui.main.MainActivity;
import de.bitb.astroskop.ui.main.disturbance.adapter.DisturbanceAdapter;
import de.bornholdtlee.snh.R;
import de.bitb.astroskop.SNHApplication;

public class DisturbanceFragment extends NavigationBaseFragment implements IDisturbanceView, IBind {

    @BindView(R.id.fragment_disturbance_list)
    protected RecyclerView recyclerView;

    @BindView(R.id.fragment_disturbance_list_empty)
    protected View emptyView;

    @BindView(R.id.fragment_disturbance_error)
    protected View networkErrorView;

    protected DisturbanceViewPresenter presenter;
    protected DisturbanceAdapter disturbanceAdapter;
    private IMainView mainView;

    public static DisturbanceFragment createInstance() {
        return new DisturbanceFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainView = (IMainView) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new DisturbanceViewPresenter((SNHApplication) getActivity().getApplication(), this);
        initRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle(getString(R.string.fragment_disturbance_title));
        presenter.onResume();
    }

    private void initRecyclerView() {
        disturbanceAdapter = new DisturbanceAdapter(getContext(), presenter);
        recyclerView.setAdapter(disturbanceAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @OnClick(R.id.fragment_disturbance_button_report_disturbance_container)
    public void onReportDisturbanceClicked() {
        mainView.navigateToReportDisturbanceScreen();
    }

    @Override
    protected boolean showInfoActionBarButton() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_disturbance;
    }

    @Override
    public int getNavigationPosition() {
        return MainActivity.TAB_DISTURBANCE;
    }

    @Override
    public void showNetworkErrorScreen() {
        networkErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListEmpty() {
        networkErrorView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshView() {
        emptyView.setVisibility(View.GONE);
        networkErrorView.setVisibility(View.GONE);
        disturbanceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefreshClicked() {
        super.onRefreshClicked();
        presenter.refreshView();
    }
}