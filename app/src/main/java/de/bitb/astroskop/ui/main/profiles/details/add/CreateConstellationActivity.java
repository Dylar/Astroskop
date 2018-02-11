package de.bitb.astroskop.ui.main.profiles.details.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.model.Constellation;
import de.bitb.astroskop.ui.base.ActionbarHandler;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.ui.base.MVPActivity;

public class CreateConstellationActivity extends MVPActivity<ICreateConstellationView, CreateConstellationPresenter> implements ICreateConstellationView, IBind {

    public static final int REQUEST_CREATE_CONSTELLATION = 666;
    private static final String KEY_CONSTELLATION = "key_constellation";

    @BindView(R.id.create_constellation_zodiac)
    protected EditText zodiacET;
    @BindView(R.id.create_constellation_house)
    protected EditText houseET;
    @BindView(R.id.create_constellation_ruler)
    protected EditText rulerET;

    public static void startActivity(BaseFragment fragment) {
        Intent intent = new Intent(fragment.getContext(), CreateConstellationActivity.class);
        fragment.startActivityForResult(intent, REQUEST_CREATE_CONSTELLATION);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_constellation;
    }

    @Override
    protected CreateConstellationPresenter createPresenter(AstroApplication application) {
        return new CreateConstellationPresenter(application, this);
    }

    @Override
    public ActionbarHandler.ActionbarCallback getActionbarCallback() {
        return new ActionbarHandler.ActionbarCallback() {

            @Override
            public int getActionbarButton1Icon() {
                return R.drawable.ic_smile;
            }

            @Override
            public boolean onActionbarButton1Clicked() {
                getPresenter().onSaveClicked(zodiacET.getText().toString(), houseET.getText().toString(), rulerET.getText().toString());
                return true;
            }
        };
    }

    @Override
    public void finishConstellation(Constellation constellation) {
        Intent data = new Intent();
        data.putExtra(KEY_CONSTELLATION, constellation);
        setResult(RESULT_OK, data);
    }
}
