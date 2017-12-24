package de.bitb.astroskop.utils;


import android.bluetooth.BluetoothAdapter;

import de.bitb.astroskop.exceptions.TechnologyDisabledException;
import de.bitb.astroskop.exceptions.TechnologyNotExistingException;


public class BluetoothUtils {

    public void checkForBluetooth() throws TechnologyNotExistingException, TechnologyDisabledException {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

        if (adapter == null) {
            throw new TechnologyNotExistingException();
        } else if (!adapter.isEnabled()) {
            throw new TechnologyDisabledException();
        }
    }
}
