package de.bitb.astroskop.ui.main.profiles;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.ui.base.NavigationBaseFragment;
import de.bitb.astroskop.ui.base.adapter.SingleRecyclerAdapter;
import de.bitb.astroskop.ui.main.MainActivity;
import de.bitb.astroskop.ui.main.circumstances.CircumstanceAdapter;
import de.bitb.astroskop.ui.main.circumstances.CircumstancesPresenter;
import de.bitb.astroskop.ui.main.circumstances.ICircumstancesView;
import de.bitb.astroskop.ui.main.circumstances.details.CircumstanceDetailsActivity;

public class ProfilesOverviewFragment extends NavigationBaseFragment implements IBind, IProfilesOverviewView {

    @BindView(R.id.fragment_profile_list)
    protected RecyclerView recyclerView;

    @BindView(R.id.fragment_profile_edittext_name)
    protected EditText nameET;

    private SingleRecyclerAdapter circumstanceAdapter;

    private ProfilesOverviewPresenter presenter;

    public static ProfilesOverviewFragment createInstance() {
        return new ProfilesOverviewFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter = new ProfilesOverviewPresenter((AstroApplication) getActivity().getApplication(), this);
        initRecyclerView();
        presenter.onCreate();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        circumstanceAdapter = new ProfilesOverviewAdapter(getContext(), presenter);
        recyclerView.setAdapter(circumstanceAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("Profiles");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circumstances;
    }

    @Override
    public int getNavigationPosition() {
        return MainActivity.TAB_CIRCUMSTANCES;
    }

    @OnClick(R.id.fragment_profile_button_create)
    protected void onCreateProfile() {
        presenter.createProfile(nameET.getText().toString());
    }

    @Override
    public void refreshView() {
        circumstanceAdapter.notifyDataSetChanged();
    }

    @Override
    public void openProfile(Profile profile) {
        dialogBuilder.showDeleteDialog(getContext(), (dialogInterface, i) -> presenter.deleteProfile(profile));
    }
}