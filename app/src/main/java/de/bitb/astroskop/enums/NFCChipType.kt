package de.bitb.astroskop.enums

import lombok.Getter

@Getter
enum class NFCChipType constructor(val storageSize: String,
                                           val addressPW: String, val addressPACK: String, val addressAuth0: String,
                                           val memoryStart: String, val memoryEnd: String) {
    NTAG213("0F",
            "2B", "2C", "29",
            "04", "27"),
    NTAG215("11",
            "85", "86", "83",
            "04", "81"),
    NTAG216("13",
            "E5", "E6", "E3",
            "04", "E1"),
    UNKNOWN("XX", "XX", "XX", "XX", "XX", "XX");

    val addressPWValue: Int
        get() = Integer.parseInt(addressPW, 16)

    val addressPackValue: Int
        get() = Integer.parseInt(addressPACK, 16)

    val addressAuth0Value: Byte
        get() = java.lang.Byte.parseByte(addressAuth0, 16)

    companion object {

        fun getNTAG21xChipType(storageSizeInByte: Byte): NFCChipType {
            val storage = String.format("%02X", storageSizeInByte)
            for (nfcChipType in values()) {
                if (nfcChipType.storageSize == storage) {
                    return nfcChipType
                }
            }
            return UNKNOWN
        }
    }
}
