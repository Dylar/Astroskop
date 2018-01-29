package de.bitb.astroskop.ui.main.profiles;

import android.content.Context;
import android.view.View;

import de.bitb.astroskop.R;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import de.bitb.astroskop.ui.base.adapter.SingleRecyclerAdapter;

public class ProfilesAdapter extends SingleRecyclerAdapter<Profile, ProfilesViewHolder> {

    public ProfilesAdapter(Context context, AdapterPresenter<Profile> presenter) {
        super(context, presenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.adapter_profile_item;
    }

    @Override
    protected ProfilesViewHolder newHolder(AdapterPresenter<Profile> presenter, View view) {
        return new ProfilesViewHolder(presenter, view);
    }

}
