package de.bornholdtlee.dbsystel.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import de.bornholdtlee.dbsystel.enums.GCPTag;
import de.bornholdtlee.dbsystel.enums.MountingLocation;
import de.bornholdtlee.dbsystel.enums.NFCChipType;
import de.bornholdtlee.dbsystel.exceptions.NFCNotEnoughSpaceException;
import de.bornholdtlee.dbsystel.exceptions.NFCReadOnlyException;
import de.bornholdtlee.dbsystel.exceptions.NFCTagIncompatibleException;
import de.bornholdtlee.dbsystel.exceptions.TechnologyDisabledException;
import de.bornholdtlee.dbsystel.exceptions.TechnologyNotExistingException;
import de.bornholdtlee.dbsystel.helper.Logger;
import de.bornholdtlee.dbsystel.model.ScanResult;
import de.bornholdtlee.dbsystel.tasks.WriteNFCTask;

public class NFCUtils {

    private static final int RECORDS_COUNT = 2;

    private static final byte NTAG21X_CMD_WRITE = (byte) 0xA2;
    private static final byte NTAG21X_CMD_READ = (byte) 0x30;
    private static final byte NTAG21X_CMD_VERSION = (byte) 0x60;
    private static final byte NTAG21X_CMD_AUTH = (byte) 0x1B;

    private static final int NTAG21X_PAGE_PW_ENABLE = 0;
    private static final int NTAG21X_PAGE_PW_DISABLE = 255;

    private static final String LOCK_PASSWORD = "0711";
    private static final String LOCK_PACK = "88";

    private static final String IDENTIFIER_CODE_URL = "https://ids-iot.noncd.db.de/deb7z4/";
    private static final String PROTOCOL_URN_EPC_ID = "1E";

    private static final int MAX_WRITE_ATTEMPTS = 10;
    private static final int PROGRESS_POST_DELAY = 10;
    private static final int PROGRESS_VALUE = 10;

    private static final String KEY_VENDOR_GCP = "vendorGCP";
    private static final String KEY_THING_ID = "thingId";
    private static final String KEY_THING_LOCATION_ID = "thingLocationId";

    private static final String[][] TECH_LIST = new String[][]{new String[]{NfcF.class.getName()
            , NfcA.class.getName()
            , IsoDep.class.getName()
            , NfcB.class.getName()
            , NfcV.class.getName()
            , Ndef.class.getName()
            , NdefFormatable.class.getName()
            , MifareClassic.class.getName()
            , MifareUltralight.class.getName()}};

    public void checkForNFC(final Context context) throws TechnologyDisabledException, TechnologyNotExistingException {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);

        if (nfcAdapter == null) {
            throw new TechnologyNotExistingException();
        } else if (!nfcAdapter.isEnabled()) {
            throw new TechnologyDisabledException();
        }
    }

    public void stopForegroundDispatch(final Activity activity) {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(activity);
        if (adapter != null && !activity.isDestroyed() && !activity.isFinishing()) {
            adapter.disableForegroundDispatch(activity);
        }
    }

    public void setupForegroundReadDispatch(final Activity activity) {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(activity);
        if (adapter != null) {
            Intent intent = new Intent(activity, activity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            final PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
            IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

            IntentFilter[] filters = new IntentFilter[]{techDetected, tagDetected, ndefDetected};

            adapter.enableForegroundDispatch(activity, pendingIntent, filters, TECH_LIST);
        }
    }

    public ScanResult handleReadIntent(Intent intent) throws NFCTagIncompatibleException {
        if (intent != null
                && (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())
                || NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction()))) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage message = (NdefMessage) rawMessages[0];

                if (message.getRecords() != null) {
                    NdefRecord[] records = message.getRecords();

                    if (records.length != RECORDS_COUNT) {
                        throw new NFCTagIncompatibleException();
                    }
                    String deviceInfo = new String(records[0].getPayload(), StandardCharsets.UTF_8);
                    String deviceType = new String(records[1].getPayload(), StandardCharsets.UTF_8);

                    deviceType = deviceType.trim().substring(2);
                    return new ScanResult(deviceInfo, deviceType);
                }
            }
        }
        return null;
    }

    public void setupForegroundWriteDispatch(Activity activity, GCPTag gcp, String thingId, MountingLocation side) {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(activity);
        if (adapter != null) {
            Intent nfcIntent = new Intent(activity, activity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            nfcIntent.putExtra(KEY_VENDOR_GCP, String.valueOf(gcp.getGcpId()));
            nfcIntent.putExtra(KEY_THING_ID, thingId);
            nfcIntent.putExtra(KEY_THING_LOCATION_ID, side.getLocationId());
            PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, nfcIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);

            adapter.enableForegroundDispatch(activity, pendingIntent, new IntentFilter[]{tagDetected}, null);
        }
    }

    public boolean handleWriteIntent(Intent intent, WriteNFCTask task)
            throws
            NFCReadOnlyException,
            NFCNotEnoughSpaceException,
            NFCTagIncompatibleException,
            IOException,
            InterruptedException {
        String gcp = intent.getStringExtra(KEY_VENDOR_GCP);
        String thingId = intent.getStringExtra(KEY_THING_ID);
        String thingSide = intent.getStringExtra(KEY_THING_LOCATION_ID);

        if (TextUtils.isEmpty(gcp) || TextUtils.isEmpty(thingId) || TextUtils.isEmpty(thingSide)) {
            return false;
        }

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        MifareUltralight nfc = MifareUltralight.get(tag);
        nfc.connect();

        NFCChipType version = checkVersion(nfc);
        Logger.debug("NFC CHIP VERSION: " + version.toString());
        task.postProgress(PROGRESS_VALUE);
        Thread.sleep(PROGRESS_POST_DELAY);

        if (version.equals(NFCChipType.UNKNOWN)) {
            nfc.close();
            return false;
        } else {
            byte[] pwd = LOCK_PASSWORD.getBytes(StandardCharsets.UTF_8);
            byte[] packRaw = LOCK_PACK.getBytes(StandardCharsets.UTF_8);
            byte[] pack = {packRaw[0], packRaw[1], 0, 0};

            if (isPasswordEnabled(nfc, version)) {
                task.postProgress(PROGRESS_VALUE);
                Thread.sleep(PROGRESS_POST_DELAY);

                if (!unlockNFC(nfc, pwd, packRaw)) {
                    throw new IOException();
                }
                task.postProgress(PROGRESS_VALUE);
                Thread.sleep(PROGRESS_POST_DELAY);

                configPassword(nfc, version, pwd, pack, false);
                task.postProgress(PROGRESS_VALUE);
                Thread.sleep(PROGRESS_POST_DELAY);
            } else {
                task.postProgress(PROGRESS_VALUE * 3);
                Thread.sleep(PROGRESS_POST_DELAY);
            }

            nfc.close();

            if (writeNFCMessage(nfc, tag, createNdefMessage(gcp, thingId, thingSide))) {
                task.postProgress(PROGRESS_VALUE);
                Thread.sleep(PROGRESS_POST_DELAY);

                nfc.connect();
                configPassword(nfc, version, pwd, pack, true);
                Thread.sleep(PROGRESS_POST_DELAY);
                task.postProgress(PROGRESS_VALUE);
            } else {
                return false;
            }
        }
        nfc.close();
        return true;
    }

    private NFCChipType checkVersion(MifareUltralight nfc) throws IOException {
        byte[] response = nfc.transceive(new byte[]{NTAG21X_CMD_VERSION});
        return NFCChipType.getNTAG21xChipType(response[6]);
    }

    private boolean unlockNFC(MifareUltralight nfc, byte[] pwd, byte[] pack) throws IOException {
        byte[] response = nfc.transceive(new byte[]{
                NTAG21X_CMD_AUTH, // PWD_AUTH
                pwd[0], pwd[1], pwd[2], pwd[3]
        });
        return response != null
                && response.length >= 2
                && pack[0] == response[0]
                && pack[1] == response[1];
    }

    private NdefMessage createNdefMessage(String gcp, String thingId, String thingSide) {
        String data = gcp + "." + thingSide + thingId;
        String data2 = "giai:" + data;

        NdefRecord record1 = NdefRecord.createUri(IDENTIFIER_CODE_URL + data + "/");
        NdefRecord record2 = NdefRecord.createUri(data2);
        byte[] payload = record2.getPayload();
        payload[0] = Byte.parseByte(PROTOCOL_URN_EPC_ID, 16);
        record2 = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_URI,
                null,
                payload);
        return new NdefMessage(new NdefRecord[]{record1, record2});
    }

    private boolean writeNFCMessage(MifareUltralight nfc, Tag tag, NdefMessage message) throws IOException, NFCTagIncompatibleException, NFCNotEnoughSpaceException, NFCReadOnlyException {
        int attempts = 0;
        boolean written = false;
        do {
            try {
                Ndef ndef = Ndef.get(tag);
                if (null == ndef) {
                    NdefFormatable format = NdefFormatable.get(tag);
                    if (format == null) {
                        throw new NFCTagIncompatibleException();
                    } else {
                        format.connect();
                        format.format(message);
                        format.close();
                    }
                } else {
                    ndef.connect();
                    if (ndef.isWritable()) {
                        int size = message.toByteArray().length;
                        if (ndef.getMaxSize() < size) {
                            throw new NFCNotEnoughSpaceException();
                        }

                        ndef.writeNdefMessage(message);
                        ndef.close();
                    } else {
                        throw new NFCReadOnlyException();
                    }
                }

                written = true;
                break;
            } catch (IllegalStateException e) {
                Logger.debug(e.toString());
                nfc.close();
            } catch (Exception e) {
                //workaround - sometimes its closing too slow
                Logger.debug(e.toString());
                attempts++;
            }
        } while (attempts < MAX_WRITE_ATTEMPTS);

        return written;
    }

    private void configPassword(MifareUltralight nfc, NFCChipType version, byte[] pwd, byte[] pack, boolean enable) throws IOException {
        nfc.writePage(version.getAddressPWValue(), pwd);
        nfc.writePage(version.getAddressPackValue(), pack);
        byte[] response = nfc.transceive(new byte[]{
                NTAG21X_CMD_READ,
                version.getAddressAuth0Value()
        });
        if (response != null && response.length >= 16) {
            int auth0 = enable ? NTAG21X_PAGE_PW_ENABLE : NTAG21X_PAGE_PW_DISABLE;
            nfc.transceive(new byte[]{
                    NTAG21X_CMD_WRITE,
                    version.getAddressAuth0Value(),
                    response[0],
                    response[1],
                    response[2],
                    (byte) (auth0 & 0x0ff)
            });
        }
    }

    private boolean isPasswordEnabled(MifareUltralight nfc, NFCChipType version) throws IOException {
        byte[] response = nfc.transceive(new byte[]{
                NTAG21X_CMD_READ, // READ
                version.getAddressAuth0Value()  // page address
        });
        return String.format("%02x", response[3]).equals("00");
    }
}
