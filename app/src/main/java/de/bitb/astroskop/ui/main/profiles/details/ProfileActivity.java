package de.bitb.astroskop.ui.main.profiles.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import de.bitb.astroskop.ui.base.ActionbarHandler;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.base.IToolbarView;

public class ProfileActivity extends BaseActivity implements IToolbarView{

    public static final String KEY_PROFILE_UUID = "key_profile_uuid";

    public static void startActivity(Context context, String uuid) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(KEY_PROFILE_UUID, uuid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(ProfileDetailFragment.createInstance(getIntent().getStringExtra(KEY_PROFILE_UUID)), false);
    }

}
