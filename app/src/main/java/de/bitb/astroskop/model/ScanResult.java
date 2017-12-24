package de.bornholdtlee.dbsystel.model;

import java.io.Serializable;

import de.bornholdtlee.dbsystel.enums.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScanResult implements Serializable {

    private static final String REGEX_URI = ":";
    private static final String REGEX_DEVICE_INFO = "\\.";

    private String gcp;
    private String deviceId;
    private DeviceType type;

    public ScanResult(String deviceInfo, String deviceType) {
        type = DeviceType.getByScanType(deviceType);
        setIdAndVendorByDeviceInfo(deviceInfo);
    }

    private void setIdAndVendorByDeviceInfo(String deviceInfo) {
        String[] uriArray = deviceInfo.split(REGEX_URI);
        if (uriArray.length == 0) {
            return;
        }

        String[] deviceInfoArray = uriArray[uriArray.length - 1].split(REGEX_DEVICE_INFO);
        if (deviceInfoArray.length == 0) {
            return;
        }

        this.gcp = deviceInfoArray[0];
        this.deviceId = deviceInfoArray[1];
    }

}
