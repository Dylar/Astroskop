package de.bitb.astroskop.ui.main.profiles;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import de.bitb.astroskop.R;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.ui.base.adapter.AdapterPresenter;
import de.bitb.astroskop.ui.base.adapter.BaseViewHolder;

class ProfilesViewHolder extends BaseViewHolder<Profile> {

    @BindView(R.id.adapter_profile_name)
    protected TextView nameTV;

    public ProfilesViewHolder(AdapterPresenter<Profile> presenter, View view) {
        super(presenter, view);
    }

    @Override
    public void bindValues(Profile profile) {
        nameTV.setText(profile.getName());
        itemView.setOnClickListener(view -> getPresenter().onItemClicked(profile));
        itemView.setOnLongClickListener(view -> {
            getPresenter().onItemLongClicked(profile);
            return true;
        });
    }
}
