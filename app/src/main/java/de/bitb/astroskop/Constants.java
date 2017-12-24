package de.bitb.astroskop;

import android.os.Environment;

public final class Constants {

    public static final String FLAVOR_DEV = "dev";
    public static final String BUILD_TYPE_DEBUG = "Debug";
    public static final String BUILD_TYPE_RELEASE = "Release";

    public static final int NULL_INTEGER = -1;
    public static final String NO_RESULT_STRING = "-";

    public static final String BASE_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/Astroskop/";
    public static final String FILE_PATH_JSONS = BASE_FILE_PATH + "jsons/";
    public static final String FILE_PATH_IMAGES = BASE_FILE_PATH + "images/";

    private Constants() {
        super();
    }

}
