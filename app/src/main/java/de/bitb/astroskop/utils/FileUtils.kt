package de.bitb.astroskop.utils


import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel
import java.nio.charset.StandardCharsets

import de.bitb.astroskop.helper.Logger

import de.bitb.astroskop.Constants.FILE_PATH_JSONS


class FileUtils {

    @Throws(IOException::class)
    fun copy(source: File, destiny: File) {
        val dir = destiny.parentFile
        if (!dir.exists()) {
            dir.mkdirs()
        }
        if (destiny.exists()) {
            destiny.delete()
        }

        var inStream: FileInputStream? = null
        var outStream: FileOutputStream? = null
        try {
            inStream = FileInputStream(source)
            outStream = FileOutputStream(destiny)
            val inChannel = inStream.channel
            val outChannel = outStream.channel
            inChannel.transferTo(0, inChannel.size(), outChannel)
        } catch (e: FileNotFoundException) {
            Logger.debug(e.message)
        } finally {
            inStream!!.close()
            outStream!!.close()
        }
    }

    @Throws(IOException::class)
    fun move(source: File, destiny: File) {
        copy(source, destiny)
        source.delete()
    }

    fun writeJsonToFile(json: String, filename: String) {
        writeToFile(json, FILE_PATH_JSONS, filename, FILE_TYPE_JSON)
    }

    fun writeToFile(data: String, dirPath: String, filename: String, type: String) {
        val fileDir = File(dirPath)
        val myFile = File(fileDir.path + filename + type)

        if (!fileDir.exists()) {
            fileDir.mkdirs()
        }
        if (myFile.exists()) {
            myFile.delete()
        }

        try {
            val fOut = FileOutputStream(myFile, false)
            fOut.write(data.toByteArray(StandardCharsets.UTF_8))
            fOut.flush()
            fOut.close()
        } catch (e: IOException) {
            Logger.error("Write to JSON File failed for file " + filename)
        }

    }

    companion object {

        val FILE_TYPE_JSON = ".json"
    }

}
