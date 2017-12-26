package de.bitb.astroskop.ui.base.adapter;

public interface AdapterPresenter {

    int getAdapterItemCount();

    void onBindAtPosition(IAdapterView holder, int position);

}
