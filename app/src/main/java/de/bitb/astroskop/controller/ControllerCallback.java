package de.bitb.astroskop.controller;

import java.util.List;

public class ControllerCallback<O> {
    public void onSuccess(List<O> response) {
        //implement if needed
    }

    public void onSuccess(O response) {
        //implement if needed
    }

    public void onSuccess() {
        //implement if needed
    }

    public void onFailure() {
        //implement if needed
    }

}
