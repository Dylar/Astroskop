package de.bornholdtlee.snh.ui.base;


import android.content.Context;
import android.os.Bundle;

public interface IBaseView {

    Context getContext();

    Bundle getArguments();

    boolean willNotCrash();

}
