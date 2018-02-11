package de.bitb.astroskop.ui.main.profiles.details.add;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.model.Constellation;
import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import de.bitb.astroskop.ui.base.ActionbarHandler;
import de.bitb.astroskop.ui.base.BaseActivity;
import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.ui.base.IToolbarView;
import de.bitb.astroskop.ui.base.MVPActivity;

import static de.bitb.astroskop.Constants.NULL_INTEGER;

public class CreateConstellationActivity extends MVPActivity<ICreateConstellationView, CreateConstellationPresenter> implements IToolbarView, ICreateConstellationView, IBind {

    public static final int REQUEST_CREATE_CONSTELLATION = 666;
    public static final String KEY_CONSTELLATION = "key_constellation";

    @BindView(R.id.create_constellation_zodiac)
    protected Spinner zodiacSpinner;
    @BindView(R.id.create_constellation_house)
    protected Spinner houseSpinner;
    @BindView(R.id.create_constellation_ruler)
    protected Spinner rulerSpinner;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, CreateConstellationActivity.class);
        activity.startActivityForResult(intent, REQUEST_CREATE_CONSTELLATION);
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
                getPresenter().onSaveClicked((Zodiac) zodiacSpinner.getSelectedItem(), (House) houseSpinner.getSelectedItem(), (Ruler) rulerSpinner.getSelectedItem());
                return true;
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zodiacSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                Zodiac.values()));
        houseSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                House.values()));
        houseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                resetSpinner(rulerSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//nothing
            }
        });

        rulerSpinner.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                Ruler.values()));
        rulerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                resetSpinner(houseSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//nothing
            }
        });
    }

    private void resetSpinner(Spinner spinner) {
        spinner.setSelection(0);
    }

    @Override
    public void finishConstellation(Constellation constellation) {
        if (constellation != null) {
            Intent data = new Intent();
            data.putExtra(KEY_CONSTELLATION, constellation);
            setResult(RESULT_OK, data);
        }
        finish();
    }
}
