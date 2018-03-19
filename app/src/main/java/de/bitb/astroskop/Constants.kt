package de.bitb.astroskop

import android.os.Environment

class Constants private constructor() {
    companion object {

        val FLAVOR_DEV = "dev"
        val BUILD_TYPE_DEBUG = "Debug"
        val BUILD_TYPE_RELEASE = "Release"

        val NULL_INTEGER = -1
        val NO_RESULT_STRING = "-"

        val BASE_FILE_PATH = Environment.getExternalStorageDirectory().path + "/Astroskop/"
        val FILE_PATH_JSONS = BASE_FILE_PATH + "jsons/"
        val FILE_PATH_IMAGES = BASE_FILE_PATH + "images/"
    }

}
