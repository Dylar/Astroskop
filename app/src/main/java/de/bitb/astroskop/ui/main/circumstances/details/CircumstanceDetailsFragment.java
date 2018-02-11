package de.bitb.astroskop.ui.main.circumstances.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.bitb.astroskop.AstroApplication;
import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.model.enums.House;
import de.bitb.astroskop.model.enums.Ruler;
import de.bitb.astroskop.model.enums.Zodiac;
import de.bitb.astroskop.ui.base.BaseFragment;

import static de.bitb.astroskop.ui.main.circumstances.details.CircumstanceDetailsActivity.KEY_CIRCUMSTANCE_UUID;

public class CircumstanceDetailsFragment extends BaseFragment implements IBind, ICircumstancesDetailView {
    @BindView(R.id.fragment_details_info_zodiac)
    protected View infosZodiac;
    @BindView(R.id.fragment_details_info_house)
    protected View infosHouse;
    @BindView(R.id.fragment_details_info_ruler)
    protected View infosRuler;

    private CircumstanceDetailsPresenter detailsPresenter;

    public static CircumstanceDetailsFragment createInstance(String uuid) {
        CircumstanceDetailsFragment frag = new CircumstanceDetailsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_CIRCUMSTANCE_UUID, uuid);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailsPresenter = new CircumstanceDetailsPresenter((AstroApplication) getActivity().getApplication(), this);
        detailsPresenter.onCreate(getArguments().getString(KEY_CIRCUMSTANCE_UUID));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circumstances_details;
    }

    @Override
    public void initZodiacInfo(Zodiac zodiac) {
        initInfo(zodiac, infosZodiac);
    }

    @Override
    public void initHouseInfo(House house) {
        initInfo(house.getZodiac(), infosHouse);
    }

    @Override
    public void initRulerInfo(Ruler ruler) {
        initInfo(ruler.getZodiacs(), infosRuler);
    }

    private void initInfo(Zodiac zodiac, View infoView) {
        List<Zodiac> zodiacs = new ArrayList<>();
        zodiacs.add(zodiac);
        initInfo(zodiacs, infoView);
    }

    private void initInfo(List<Zodiac> zodiacs, View infoView) {
        Zodiac zodiac = zodiacs.get(0);

        String zodiacName = zodiac.name();
        String houseName = zodiac.getHouse().name();
        String rulerName = zodiac.getRuler().name();
        String elementName = zodiac.getElement().name();
        String qualityName = zodiac.getQuality().name();
        String genderName = zodiac.getGender().name();

        if(zodiacs.size() == 2){
            Zodiac zodiac2 = zodiacs.get(1);
            zodiacName += ", " + zodiac2.name();
            houseName += ", " + zodiac2.getHouse().name();
            rulerName += ", " + zodiac2.getRuler().name();
            elementName += ", " + zodiac2.getElement().name();
            qualityName += ", " + zodiac2.getQuality().name();
            genderName += ", " + zodiac2.getGender().name();
        }

        TextView textView = infoView.findViewById(R.id.detail_info_zodiac);
        textView.setText(zodiacName);
        textView = infoView.findViewById(R.id.detail_info_house);
        textView.setText(houseName);
        textView = infoView.findViewById(R.id.detail_info_ruler);
        textView.setText(rulerName);
        textView = infoView.findViewById(R.id.detail_info_element);
        textView.setText(elementName);
        textView = infoView.findViewById(R.id.detail_info_quality);
        textView.setText(qualityName);
        textView = infoView.findViewById(R.id.detail_info_gender);
        textView.setText(genderName);
    }
}
