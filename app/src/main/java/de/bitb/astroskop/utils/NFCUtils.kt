package de.bitb.astroskop.utils

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.nfc.tech.MifareClassic
import android.nfc.tech.MifareUltralight
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.nfc.tech.NfcA
import android.nfc.tech.NfcB
import android.nfc.tech.NfcF
import android.nfc.tech.NfcV
import android.os.Parcelable
import android.text.TextUtils

import java.io.IOException
import java.nio.charset.StandardCharsets

import de.bitb.astroskop.enums.NFCChipType
import de.bitb.astroskop.exceptions.NFCNotEnoughSpaceException
import de.bitb.astroskop.exceptions.NFCReadOnlyException
import de.bitb.astroskop.exceptions.NFCTagIncompatibleException
import de.bitb.astroskop.exceptions.TechnologyDisabledException
import de.bitb.astroskop.exceptions.TechnologyNotExistingException
import de.bitb.astroskop.helper.Logger
import de.bitb.astroskop.model.ScanResult
import de.bitb.astroskop.tasks.WriteNFCTask

class NFCUtils {

    @Throws(TechnologyDisabledException::class, TechnologyNotExistingException::class)
    fun checkForNFC(context: Context) {
        val nfcAdapter = NfcAdapter.getDefaultAdapter(context)

        if (nfcAdapter == null) {
            throw TechnologyNotExistingException()
        } else if (!nfcAdapter.isEnabled) {
            throw TechnologyDisabledException()
        }
    }

    fun stopForegroundDispatch(activity: Activity) {
        val adapter = NfcAdapter.getDefaultAdapter(activity)
        if (adapter != null && !activity.isDestroyed && !activity.isFinishing) {
            adapter.disableForegroundDispatch(activity)
        }
    }

    fun setupForegroundReadDispatch(activity: Activity) {
        val adapter = NfcAdapter.getDefaultAdapter(activity)
        if (adapter != null) {
            val intent = Intent(activity, activity.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            val pendingIntent = PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            val tagDetected = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
            val ndefDetected = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
            val techDetected = IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED)

            val filters = arrayOf(techDetected, tagDetected, ndefDetected)

            adapter.enableForegroundDispatch(activity, pendingIntent, filters, TECH_LIST)
        }
    }

    @Throws(NFCTagIncompatibleException::class)
    fun handleReadIntent(intent: Intent?): ScanResult? {
        if (intent != null && (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action
                        || NfcAdapter.ACTION_TAG_DISCOVERED == intent.action
                        || NfcAdapter.ACTION_TECH_DISCOVERED == intent.action)) {
            val rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            if (rawMessages != null) {
                val message = rawMessages[0] as NdefMessage

                if (message.records != null) {
                    val records = message.records

                    if (records.size != RECORDS_COUNT) {
                        throw NFCTagIncompatibleException()
                    }
                    val deviceInfo = String(records[0].payload, StandardCharsets.UTF_8)
                    var deviceType = String(records[1].payload, StandardCharsets.UTF_8)

                    deviceType = deviceType.trim { it <= ' ' }.substring(2)
                    return ScanResult(deviceInfo, deviceType)
                }
            }
        }
        return null
    }

    fun setupForegroundWriteDispatch(activity: Activity, string: String) {
        val adapter = NfcAdapter.getDefaultAdapter(activity)
        if (adapter != null) {
            val nfcIntent = Intent(activity, activity.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            nfcIntent.putExtra(KEY_NFC_STRING, string)
            val pendingIntent = PendingIntent.getActivity(activity, 0, nfcIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val tagDetected = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)

            adapter.enableForegroundDispatch(activity, pendingIntent, arrayOf(tagDetected), null)
        }
    }

    @Throws(NFCReadOnlyException::class, NFCNotEnoughSpaceException::class, NFCTagIncompatibleException::class, IOException::class, InterruptedException::class)
    fun handleWriteIntent(intent: Intent, task: WriteNFCTask): Boolean {
        val gcp = intent.getStringExtra(KEY_NFC_STRING)
        val thingId = intent.getStringExtra(KEY_THING_ID)
        val thingSide = intent.getStringExtra(KEY_THING_LOCATION_ID)

        if (TextUtils.isEmpty(gcp) || TextUtils.isEmpty(thingId) || TextUtils.isEmpty(thingSide)) {
            return false
        }

        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        val nfc = MifareUltralight.get(tag)
        nfc.connect()

        val version = checkVersion(nfc)
        Logger.debug("NFC CHIP VERSION: " + version.toString())
        task.postProgress(PROGRESS_VALUE)
        Thread.sleep(PROGRESS_POST_DELAY.toLong())

        if (version == NFCChipType.UNKNOWN) {
            nfc.close()
            return false
        } else {
            val pwd = LOCK_PASSWORD.toByteArray(StandardCharsets.UTF_8)
            val packRaw = LOCK_PACK.toByteArray(StandardCharsets.UTF_8)
            val pack = byteArrayOf(packRaw[0], packRaw[1], 0, 0)

            if (isPasswordEnabled(nfc, version)) {
                task.postProgress(PROGRESS_VALUE)
                Thread.sleep(PROGRESS_POST_DELAY.toLong())

                if (!unlockNFC(nfc, pwd, packRaw)) {
                    throw IOException()
                }
                task.postProgress(PROGRESS_VALUE)
                Thread.sleep(PROGRESS_POST_DELAY.toLong())

                configPassword(nfc, version, pwd, pack, false)
                task.postProgress(PROGRESS_VALUE)
                Thread.sleep(PROGRESS_POST_DELAY.toLong())
            } else {
                task.postProgress(PROGRESS_VALUE * 3)
                Thread.sleep(PROGRESS_POST_DELAY.toLong())
            }

            nfc.close()

            if (writeNFCMessage(nfc, tag, createNdefMessage(gcp, thingId, thingSide))) {
                task.postProgress(PROGRESS_VALUE)
                Thread.sleep(PROGRESS_POST_DELAY.toLong())

                nfc.connect()
                configPassword(nfc, version, pwd, pack, true)
                Thread.sleep(PROGRESS_POST_DELAY.toLong())
                task.postProgress(PROGRESS_VALUE)
            } else {
                return false
            }
        }
        nfc.close()
        return true
    }

    @Throws(IOException::class)
    private fun checkVersion(nfc: MifareUltralight): NFCChipType {
        val response = nfc.transceive(byteArrayOf(NTAG21X_CMD_VERSION))
        return NFCChipType.getNTAG21xChipType(response[6])
    }

    @Throws(IOException::class)
    private fun unlockNFC(nfc: MifareUltralight, pwd: ByteArray, pack: ByteArray): Boolean {
        val response = nfc.transceive(byteArrayOf(NTAG21X_CMD_AUTH, // PWD_AUTH
                pwd[0], pwd[1], pwd[2], pwd[3]))
        return (response != null
                && response.size >= 2
                && pack[0] == response[0]
                && pack[1] == response[1])
    }

    private fun createNdefMessage(gcp: String, thingId: String, thingSide: String): NdefMessage {
        val data = gcp + "." + thingSide + thingId
        val data2 = "giai:" + data

        val record1 = NdefRecord.createUri(IDENTIFIER_CODE_URL + data + "/")
        var record2 = NdefRecord.createUri(data2)
        val payload = record2.payload
        payload[0] = java.lang.Byte.parseByte(PROTOCOL_URN_EPC_ID, 16)
        record2 = NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_URI, null,
                payload)
        return NdefMessage(arrayOf(record1, record2))
    }

    @Throws(IOException::class, NFCTagIncompatibleException::class, NFCNotEnoughSpaceException::class, NFCReadOnlyException::class)
    private fun writeNFCMessage(nfc: MifareUltralight, tag: Tag, message: NdefMessage): Boolean {
        var attempts = 0
        var written = false
        do {
            try {
                val ndef = Ndef.get(tag)
                if (null == ndef) {
                    val format = NdefFormatable.get(tag)
                    if (format == null) {
                        throw NFCTagIncompatibleException()
                    } else {
                        format.connect()
                        format.format(message)
                        format.close()
                    }
                } else {
                    ndef.connect()
                    if (ndef.isWritable) {
                        val size = message.toByteArray().size
                        if (ndef.maxSize < size) {
                            throw NFCNotEnoughSpaceException()
                        }

                        ndef.writeNdefMessage(message)
                        ndef.close()
                    } else {
                        throw NFCReadOnlyException()
                    }
                }

                written = true
                break
            } catch (e: IllegalStateException) {
                Logger.debug(e.toString())
                nfc.close()
            } catch (e: Exception) {
                //workaround - sometimes its closing too slow
                Logger.debug(e.toString())
                attempts++
            }

        } while (attempts < MAX_WRITE_ATTEMPTS)

        return written
    }

    @Throws(IOException::class)
    private fun configPassword(nfc: MifareUltralight, version: NFCChipType, pwd: ByteArray, pack: ByteArray, enable: Boolean) {
        nfc.writePage(version.addressPWValue, pwd)
        nfc.writePage(version.addressPackValue, pack)
        val response = nfc.transceive(byteArrayOf(NTAG21X_CMD_READ, version.addressAuth0Value))
        if (response != null && response.size >= 16) {
            val auth0 = if (enable) NTAG21X_PAGE_PW_ENABLE else NTAG21X_PAGE_PW_DISABLE
            nfc.transceive(byteArrayOf(NTAG21X_CMD_WRITE, version.addressAuth0Value, response[0], response[1], response[2], (auth0 and 0x0ff).toByte()))
        }
    }

    @Throws(IOException::class)
    private fun isPasswordEnabled(nfc: MifareUltralight, version: NFCChipType): Boolean {
        val response = nfc.transceive(byteArrayOf(NTAG21X_CMD_READ, // READ
                version.addressAuth0Value  // page address
        ))
        return String.format("%02x", response[3]) == "00"
    }

    companion object {

        private val RECORDS_COUNT = 2

        private val NTAG21X_CMD_WRITE = 0xA2.toByte()
        private val NTAG21X_CMD_READ = 0x30.toByte()
        private val NTAG21X_CMD_VERSION = 0x60.toByte()
        private val NTAG21X_CMD_AUTH = 0x1B.toByte()

        private val NTAG21X_PAGE_PW_ENABLE = 0
        private val NTAG21X_PAGE_PW_DISABLE = 255

        private val LOCK_PASSWORD = "0711"
        private val LOCK_PACK = "88"

        private val IDENTIFIER_CODE_URL = "https://ids-iot.noncd.db.de/deb7z4/"
        private val PROTOCOL_URN_EPC_ID = "1E"

        private val MAX_WRITE_ATTEMPTS = 10
        private val PROGRESS_POST_DELAY = 10
        private val PROGRESS_VALUE = 10

        private val KEY_NFC_STRING = "vendorGCP"
        private val KEY_THING_ID = "thingId"
        private val KEY_THING_LOCATION_ID = "thingLocationId"

        private val TECH_LIST = arrayOf(arrayOf(NfcF::class.java.name, NfcA::class.java.name, IsoDep::class.java.name, NfcB::class.java.name, NfcV::class.java.name, Ndef::class.java.name, NdefFormatable::class.java.name, MifareClassic::class.java.name, MifareUltralight::class.java.name))
    }
}
