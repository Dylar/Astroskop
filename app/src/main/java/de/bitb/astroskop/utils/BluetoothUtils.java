package de.bornholdtlee.dbsystel.utils;


import android.bluetooth.BluetoothAdapter;
import de.bornholdtlee.dbsystel.exceptions.TechnologyDisabledException;
import de.bornholdtlee.dbsystel.exceptions.TechnologyNotExistingException;

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
