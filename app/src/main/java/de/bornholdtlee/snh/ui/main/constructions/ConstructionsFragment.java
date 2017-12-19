package de.bornholdtlee.snh.ui.main.constructions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.ui.base.NavigationBaseFragment;
import de.bornholdtlee.snh.ui.main.IMainView;
import de.bornholdtlee.snh.ui.main.constructions.adapter.ConstructionsAdapter;
import de.bornholdtlee.snh.ui.main.constructions.adapter.DistrictAdapter;
import de.bornholdtlee.snh.utils.CommonUtils;

import static de.bornholdtlee.snh.ui.main.MainActivity.TAB_CONSTRUCTIONS;

public class ConstructionsFragment extends NavigationBaseFragment implements IConstructionsView, IDistrictView, IBind, IInjection, TextWatcher {

    @Inject
    protected CommonUtils commonUtils;

    @BindView(R.id.fragment_constructions_error)
    protected View networkErrorView;

    @BindView(R.id.fragment_constructions_list_empty)
    protected View emptyView;

    @BindView(R.id.fragment_constructions_search)
    protected EditText searchEditText;

    @BindView(R.id.fragment_constructions_search_container)
    protected View searchContainer;

    @BindView(R.id.fragment_constructions_list)
    protected RecyclerView recyclerView;

    private ConstructionsViewPresenter constructionsViewPresenter;
    private DistrictViewPresenter districtViewPresenter;
    private IMainView mainView;
    private ConstructionsAdapter constructionAdapter;
    private DistrictAdapter districtAdapter;

    private boolean searching;

    public static ConstructionsFragment createInstance() {
        return new ConstructionsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainView = (IMainView) context;
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchEditText.addTextChangedListener(this);
        districtViewPresenter = new DistrictViewPresenter((SNHApplication) getActivity().getApplication(), this);
        constructionsViewPresenter = new ConstructionsViewPresenter((SNHApplication) getActivity().getApplication(), this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        setConstructionsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setConstructionsAdapter() {
        if (constructionAdapter == null) {
            constructionAdapter = new ConstructionsAdapter(getContext(), constructionsViewPresenter);
        }
        recyclerView.setAdapter(constructionAdapter);
    }

    private void setDistrictAdapter() {
        if (districtAdapter == null) {
            districtAdapter = new DistrictAdapter(getContext(), districtViewPresenter);
        }
        recyclerView.setAdapter(districtAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle(getString(R.string.fragment_constructions_title));
        if (searching) {
            districtViewPresenter.searchStreets(searchEditText.getText().toString());
        } else {
            constructionsViewPresenter.onResume();
        }
    }

    @Override
    protected boolean showInfoActionBarButton() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_constructions;
    }

    @Override
    public int getNavigationPosition() {
        return TAB_CONSTRUCTIONS;
    }

    @Override
    public void showNetworkErrorScreen() {
        networkErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefreshClicked() {
        if (searching) {
            districtViewPresenter.refreshView();
        } else {
            constructionsViewPresenter.refreshView();
        }
    }

    public void openDistrictView(String district) {
        mainView.openDistrictView(district);
    }

    @Override
    public void refreshView() {
        emptyView.setVisibility(View.GONE);
        networkErrorView.setVisibility(View.GONE);
        if (searching) {
            districtAdapter.notifyDataSetChanged();
        } else {
            constructionAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showListEmpty() {
        networkErrorView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//nothing
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        emptyView.setVisibility(View.GONE);
        if (TextUtils.isEmpty(charSequence)) {
            searching = false;
            setConstructionsAdapter();
        } else if (!searching) {
            searching = true;
            setDistrictAdapter();
        }
        if (searching) {
            districtViewPresenter.searchStreets(charSequence.toString());
        }

        searchEditText.setCompoundDrawablesWithIntrinsicBounds(searching ? 0 : R.drawable.ic_search, 0, 0, 0);
    }

    @Override
    public void afterTextChanged(Editable editable) {
//nothing
    }

    @OnClick(R.id.fragment_constructons_search_cancel)
    public void onCancelClicked() {
        searchEditText.setText("");
        searchContainer.requestFocus();
        commonUtils.closeKeyboard(getContext(), searchContainer.getWindowToken());
    }
}