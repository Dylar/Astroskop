package de.bitb.astroskop.model

import java.io.Serializable

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ScanResult(deviceInfo: String, deviceType: String) : Serializable {

    private var gcp: String? = null
    private var deviceId: String? = null

    init {
        setIdAndVendorByDeviceInfo(deviceInfo)
    }

    private fun setIdAndVendorByDeviceInfo(deviceInfo: String) {
        val uriArray = deviceInfo.split(REGEX_URI.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (uriArray.size == 0) {
            return
        }

        val deviceInfoArray = uriArray[uriArray.size - 1].split(REGEX_DEVICE_INFO.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (deviceInfoArray.size == 0) {
            return
        }

        this.gcp = deviceInfoArray[0]
        this.deviceId = deviceInfoArray[1]
    }

    companion object {

        private val REGEX_URI = ":"
        private val REGEX_DEVICE_INFO = "\\."
    }

}
