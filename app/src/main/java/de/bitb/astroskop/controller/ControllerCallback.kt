package de.bitb.astroskop.controller

class ControllerCallback<in O> {
    fun onSuccess(response: List<O>) {
        //implement if needed
    }

    fun onSuccess(response: O) {
        //implement if needed
    }

    fun onSuccess() {
        //implement if needed
    }

    fun onFailure() {
        //implement if needed
    }

}
