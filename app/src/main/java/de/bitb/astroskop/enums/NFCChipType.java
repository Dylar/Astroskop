package de.bitb.astroskop.enums;

import lombok.Getter;

@Getter
public enum NFCChipType {
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

    private final String storageSize;
    private final String addressPW;
    private final String addressPACK;
    private final String addressAuth0;
    private final String memoryStart;
    private final String memoryEnd;

    NFCChipType(String storageSizeAsHexString,
                String addressPW, String addressPACK, String addressAuth0,
                String memoryStart, String memoryEnd) {
        this.storageSize = storageSizeAsHexString;
        this.addressPW = addressPW;
        this.addressPACK = addressPACK;
        this.addressAuth0 = addressAuth0;
        this.memoryStart = memoryStart;
        this.memoryEnd = memoryEnd;
    }

    public static NFCChipType getNTAG21xChipType(byte storageSizeInByte) {
        String storage = String.format("%02X", storageSizeInByte);
        for (NFCChipType nfcChipType : values()) {
            if (nfcChipType.getStorageSize().equals(storage)) {
                return nfcChipType;
            }
        }
        return UNKNOWN;
    }

    public int getAddressPWValue() {
        return Integer.parseInt(getAddressPW(), 16);
    }

    public int getAddressPackValue() {
        return Integer.parseInt(getAddressPACK(), 16);
    }

    public byte getAddressAuth0Value() {
        return Byte.parseByte(getAddressAuth0(), 16);
    }
}
