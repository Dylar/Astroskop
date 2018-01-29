package de.bitb.astroskop.ui.main.profiles;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.ui.base.NavigationBaseFragment;
import de.bitb.astroskop.ui.base.adapter.SingleRecyclerAdapter;
import de.bitb.astroskop.ui.main.MainActivity;
import de.bitb.astroskop.ui.main.profiles.details.ProfileActivity;

public class ProfilesFragment extends NavigationBaseFragment implements IBind, IProfilesView {

    @BindView(R.id.fragment_profile_list)
    protected RecyclerView recyclerView;

    @BindView(R.id.fragment_profile_edittext_name)
    protected EditText nameET;

    private SingleRecyclerAdapter profileAdapter;

    private ProfilesPresenter presenter;

    public static ProfilesFragment createInstance() {
        return new ProfilesFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter = new ProfilesPresenter((AstroApplication) getActivity().getApplication(), this);
        initRecyclerView();
        presenter.onCreate();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        profileAdapter = new ProfilesAdapter(getContext(), presenter);
        recyclerView.setAdapter(profileAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("Profiles");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profiles_overview;
    }

    @Override
    public int getNavigationPosition() {
        return MainActivity.TAB_PROFILES;
    }

    @OnClick(R.id.fragment_profile_button_create)
    protected void onCreateProfile() {
        presenter.createProfile(nameET.getText().toString());
    }

    @Override
    public void refreshView() {
        profileAdapter.notifyDataSetChanged();
    }

    @Override
    public void openProfile(Profile profile) {
        ProfileActivity.startActivity(getContext(), profile.getUuid());
    }

    @Override
    public void openDeleteDialog(Profile profile) {
        dialogBuilder.showDeleteDialog(getContext(), (dialogInterface, i) -> presenter.deleteProfile(profile));
    }

}