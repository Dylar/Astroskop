package de.bitb.astroskop.ui.base.adapter;

public interface AdapterPresenter<M> {

    int getAdapterItemCount();

    void onBindAtPosition(IAdapterView<M> holder, int position);

    void onItemClicked(M model);

    void onItemLongClicked(M model);
}
