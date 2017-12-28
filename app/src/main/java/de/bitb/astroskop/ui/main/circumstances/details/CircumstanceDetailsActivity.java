package de.bitb.astroskop.ui.main.circumstances.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import de.bitb.astroskop.ui.base.BaseActivity;

public class CircumstanceDetailsActivity extends BaseActivity {

    public static final String KEY_CIRCUMSTANCE_UUID = "key_circumstance_uuid";

    public static void startActivity(Context context, String uuid) {
        Intent intent = new Intent(context, CircumstanceDetailsActivity.class);
        intent.putExtra(KEY_CIRCUMSTANCE_UUID, uuid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(CircumstanceDetailsFragment.createInstance(getIntent().getStringExtra(KEY_CIRCUMSTANCE_UUID)), false);
    }
}
