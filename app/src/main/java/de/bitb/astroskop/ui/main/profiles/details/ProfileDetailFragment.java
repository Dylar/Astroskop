package de.bitb.astroskop.ui.main.profiles.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.R;
import de.bitb.astroskop.helper.Logger;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.model.Constellation;
import de.bitb.astroskop.model.Profile;
import de.bitb.astroskop.ui.base.ActionbarHandler;
import de.bitb.astroskop.ui.base.BaseFragment;
import de.bitb.astroskop.ui.base.MVPFragment;
import de.bitb.astroskop.ui.main.profiles.details.add.CreateConstellationActivity;

import static android.app.Activity.RESULT_OK;
import static de.bitb.astroskop.ui.main.profiles.details.ProfileActivity.KEY_PROFILE_UUID;
import static de.bitb.astroskop.ui.main.profiles.details.add.CreateConstellationActivity.REQUEST_CREATE_CONSTELLATION;

public class ProfileDetailFragment extends MVPFragment<IProfileDetailView, ProfileDetailPresenter> implements IProfileDetailView, IBind {

    @BindView(R.id.fragment_profile_detail_aries_list)
    protected ViewGroup ariesList;
    @BindView(R.id.fragment_profile_detail_taurus_list)
    protected ViewGroup taurusList;
    @BindView(R.id.fragment_profile_detail_gemini_list)
    protected ViewGroup geminiList;
    @BindView(R.id.fragment_profile_detail_cancer_list)
    protected ViewGroup cancerList;
    @BindView(R.id.fragment_profile_detail_leo_list)
    protected ViewGroup leoList;
    @BindView(R.id.fragment_profile_detail_virgo_list)
    protected ViewGroup virgoList;
    @BindView(R.id.fragment_profile_detail_libra_list)
    protected ViewGroup libraList;
    @BindView(R.id.fragment_profile_detail_scorpio_list)
    protected ViewGroup scorpioList;
    @BindView(R.id.fragment_profile_detail_sagittarius_list)
    protected ViewGroup sagittariusList;
    @BindView(R.id.fragment_profile_detail_capricorn_list)
    protected ViewGroup capricornList;
    @BindView(R.id.fragment_profile_detail_aquarius_list)
    protected ViewGroup aquariusList;
    @BindView(R.id.fragment_profile_detail_pisces_list)
    protected ViewGroup piscesList;

    public static ProfileDetailFragment createInstance(String uuid) {
        ProfileDetailFragment frag = new ProfileDetailFragment();
        Bundle args = new Bundle();
        args.putString(KEY_PROFILE_UUID, uuid);
        frag.setArguments(args);
        return frag;
    }

    @Override
    protected ProfileDetailPresenter createPresenter(AstroApplication application) {
        return new ProfileDetailPresenter(application, this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().onCreate(getArguments().getString(KEY_PROFILE_UUID));
    }

    @Override
    public ActionbarHandler.ActionbarCallback getActionbarCallback() {
        return new ActionbarHandler.ActionbarCallback() {
            @Override
            public int getActionbarButton1Icon() {
                return android.R.drawable.ic_menu_add;
            }

            @Override
            public boolean onActionbarButton1Clicked() {
                CreateConstellationActivity.startActivity(getActivity());
                return true;
            }
        };
    }

    @Override
    public void initProfile(Profile profile) {
        List<Constellation> constellations = profile.getConstellations();
        ariesList.removeAllViews();
        taurusList.removeAllViews();
        geminiList.removeAllViews();
        cancerList.removeAllViews();
        leoList.removeAllViews();
        virgoList.removeAllViews();
        libraList.removeAllViews();
        scorpioList.removeAllViews();
        sagittariusList.removeAllViews();
        capricornList.removeAllViews();
        aquariusList.removeAllViews();
        piscesList.removeAllViews();
        for (Constellation constellation : constellations) {
            ViewGroup viewGroup = null;
            switch (constellation.getZodiac()) {
                case ARIES:
                    viewGroup = ariesList;
                    break;
                case TAURUS:
                    viewGroup = taurusList;
                    break;
                case GEMINI:
                    viewGroup = geminiList;
                    break;
                case CANCER:
                    viewGroup = cancerList;
                    break;
                case LEO:
                    viewGroup = leoList;
                    break;
                case VIRGO:
                    viewGroup = virgoList;
                    break;
                case LIBRA:
                    viewGroup = libraList;
                    break;
                case SCORPIO:
                    viewGroup = scorpioList;
                    break;
                case SAGITTARIUS:
                    viewGroup = sagittariusList;
                    break;
                case CAPRICORN:
                    viewGroup = capricornList;
                    break;
                case AQUARIUS:
                    viewGroup = aquariusList;
                    break;
                case PISCES:
                    viewGroup = piscesList;
                    break;
            }
            boolean isHouse = constellation.isHouse();
            View view = LayoutInflater.from(getContext()).inflate(R.layout.profile_detail_constellation_list_item, null);
            TextView tv = view.findViewById(R.id.profile_detail_list_item_label);
            tv.setText(isHouse ? "House:" : "Ruler: ");
            tv = view.findViewById(R.id.profile_detail_list_item_text);
            tv.setText(isHouse ? constellation.getHouse().name() : constellation.getRuler().name());

            viewGroup.addView(view);
        }
    }

    @Override
    public void refreshView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile_detail;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CREATE_CONSTELLATION) {
            getPresenter().addConstellation((Constellation) data.getSerializableExtra(CreateConstellationActivity.KEY_CONSTELLATION));
        }
    }
}
