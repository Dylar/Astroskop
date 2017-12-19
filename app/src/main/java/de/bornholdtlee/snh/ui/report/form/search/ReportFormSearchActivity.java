package de.bornholdtlee.snh.ui.report.form.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import de.bornholdtlee.snh.R;
import de.bornholdtlee.snh.SNHApplication;
import de.bornholdtlee.snh.enums.AnimationType;
import de.bornholdtlee.snh.injection.IBind;
import de.bornholdtlee.snh.injection.IInjection;
import de.bornholdtlee.snh.injection.components.AppComponent;
import de.bornholdtlee.snh.ui.base.BaseActivity;
import de.bornholdtlee.snh.ui.base.SimpleBaseActivity;

public class ReportFormSearchActivity extends SimpleBaseActivity implements ISearchView, TextWatcher, IInjection, IBind {

    public static final int REQUEST_CODE = 666;
    public static final String KEY_STREET_NAME = "streetName";
    public static final String KEY_DISTRICT_NAME = "districtName";

//    @BindView(R.id.activity_report_form_error)
//    protected View networkErrorView;

    @BindView(R.id.activity_report_form_list_empty)
    protected View emptyView;

    @BindView(R.id.activity_report_form_search)
    protected EditText searchEditText;

    @BindView(R.id.activity_report_form_search_cancel)
    protected View searchCancelBtn;

    @BindView(R.id.activity_report_form_search_container)
    protected View searchContainer;

    @BindView(R.id.activity_report_form_list)
    protected RecyclerView recyclerView;

    private SearchStreetAdapter searchStreetAdapter;
    private SearchViewPresenter searchViewPresenter;

    public static void startActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, ReportFormSearchActivity.class);
        activity.startActivityForResult(intent, REQUEST_CODE);
        activity.getUiUtils().setAnimation(activity, AnimationType.SLIDE_BOTTOM_POP_BOTTOM, true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchEditText.addTextChangedListener(this);
        searchViewPresenter = new SearchViewPresenter((SNHApplication) getApplication(), this);
        initRecyclerView();
        searchEditText.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchViewPresenter.onResume();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_report_form_search;
    }

    @Override
    public void inject(AppComponent appComponent) {
        appComponent.inject(this);
    }

    private void initRecyclerView() {
        setDistrictAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setDistrictAdapter() {
        if (searchStreetAdapter == null) {
            searchStreetAdapter = new SearchStreetAdapter(getContext(), searchViewPresenter);
        }
        recyclerView.setAdapter(searchStreetAdapter);
    }

    @Override
    public void showNetworkErrorScreen() {
//        networkErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshView() {
        emptyView.setVisibility(View.GONE);
//        networkErrorView.setVisibility(View.GONE);
        searchStreetAdapter.notifyDataSetChanged();
    }

    @Override
    public void showListEmpty() {
//        networkErrorView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void streetSelected(String streetName, String districtName) {
        commonUtils.closeKeyboard(getContext(), searchContainer.getWindowToken());
        Intent returnIntent = new Intent();
        returnIntent.putExtra(KEY_STREET_NAME, streetName);
        returnIntent.putExtra(KEY_DISTRICT_NAME, districtName);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//nothing
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        emptyView.setVisibility(View.GONE);
        searchViewPresenter.searchStreets(charSequence.toString());
        searchEditText.setCompoundDrawablesWithIntrinsicBounds(TextUtils.isEmpty(charSequence) ? R.drawable.ic_search : 0, 0, 0, 0);
    }

    @Override
    public void afterTextChanged(Editable editable) {
//nothing
    }

    @OnClick(R.id.activity_report_form_search_cancel)
    public void onCancelClicked() {
        searchEditText.setText("");
        searchContainer.requestFocus();
        commonUtils.closeKeyboard(getContext(), searchContainer.getWindowToken());
    }

}
