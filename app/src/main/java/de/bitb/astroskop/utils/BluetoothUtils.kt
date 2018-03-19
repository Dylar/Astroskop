package de.bitb.astroskop.utils


import android.bluetooth.BluetoothAdapter

import de.bitb.astroskop.exceptions.TechnologyDisabledException
import de.bitb.astroskop.exceptions.TechnologyNotExistingException


class BluetoothUtils {

    @Throws(TechnologyNotExistingException::class, TechnologyDisabledException::class)
    fun checkForBluetooth() {
        val adapter = BluetoothAdapter.getDefaultAdapter()

        if (adapter == null) {
            throw TechnologyNotExistingException()
        } else if (!adapter.isEnabled) {
            throw TechnologyDisabledException()
        }
    }
}
