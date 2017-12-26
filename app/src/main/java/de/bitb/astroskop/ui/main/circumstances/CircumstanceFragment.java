package de.bitb.astroskop.ui.main.circumstances;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.OnClick;
import de.bitb.astroskop.R;
import de.bitb.astroskop.injection.IBind;
import de.bitb.astroskop.model.Circumstance;
import de.bitb.astroskop.ui.base.IBaseView;
import de.bitb.astroskop.ui.base.NavigationBaseFragment;
import de.bitb.astroskop.ui.main.MainActivity;

public class CircumstanceFragment extends NavigationBaseFragment implements IBind, ICircumstancesView {

    @BindView(R.id.fragment_circumstances_result)
    protected RecyclerView recyclerView;

    private CircumstanceAdapter circumstanceAdapter;

    private CircumstancesPresenter presenter;

    public static CircumstanceFragment createInstance() {
        return new CircumstanceFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.presenter = new CircumstancesPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        setToolbarTitle("Circumstance");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circumstances;
    }

    @Override
    public int getNavigationPosition() {
        return MainActivity.TAB_HOME;
    }

    @OnClick(R.id.fragment_circumstances_button_go)
    protected void onGoClicked() {
        presenter.onGoClick();
    }

    @Override
    public void refreshView() {
        rec
    }
}