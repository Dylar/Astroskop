package de.bornholdtlee.snh.ui.main.constructions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.enums.AnimationType;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.ui.base.BaseFragment;
import de.bornholdtlee.snh.ui.main.constructions.adapter.DistrictAdapter;

public class DistrictFragment extends BaseFragment implements IDistrictView, IBind {

    private static final String KEY_DISTRICT = "district";

    @BindView(R.id.fragment_district_error)
    protected View networkErrorView;

    @BindView(R.id.fragment_district_list)
    protected RecyclerView recyclerView;

    private DistrictViewPresenter presenter;
    private DistrictAdapter adapter;

    public static DistrictFragment createInstance(String district) {
        Bundle args = new Bundle();
        args.putString(KEY_DISTRICT, district);
        DistrictFragment fragment = new DistrictFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new DistrictViewPresenter((SNHApplication) getActivity().getApplication(), this, getArguments().getString(KEY_DISTRICT));
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new DistrictAdapter(getContext(), presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected boolean showInfoActionBarButton() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_districts;
    }

    @Override
    public AnimationType getAnimationType() {
        return AnimationType.SLIDE_RIGHT_POP_LEFT;
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_navbar_back;
    }

    @Override
    public void showNetworkErrorScreen() {
        networkErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshView() {
        networkErrorView.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showListEmpty() {
//nothing
    }

    @Override
    public void onRefreshClicked() {
        presenter.refreshView();
    }
}