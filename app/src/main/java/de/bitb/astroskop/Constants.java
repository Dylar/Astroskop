package de.bornholdtlee.dbsystel;

import android.os.Environment;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Constants {

    public static final String FLAVOR_DEV = "dev";
    public static final String BUILD_TYPE_DEBUG = "Debug";
    public static final String BUILD_TYPE_RELEASE = "Release";
    public static final String TEST_THING_TYPE_CARGO_WAGON = "cargo wagon";
    public static final String LOGGED_IN_USER = "loggedInUser";

    public static final String KEY_DEVICE_DB_ID = "deviceDbId";

    public static final int NULL_INTEGER = -1;
    public static final String NO_RESULT_STRING = "-";

    public static final int ID_GATEWAY = 0;
    public static final int ID_SENSOR = ID_GATEWAY + 1;
    public static final int ID_ACTOR = ID_SENSOR + 1;
    public static final int ID_DESCRIPTOR = ID_ACTOR + 1;
    public static final int ID_UNKNOWN = ID_DESCRIPTOR + 1;

    public static final String GATEWAY = "gateway";
    public static final String SENSOR = "sensor";
    public static final String ACTOR = "actor";
    public static final String DESCRIPTOR = "descriptor";
    public static final String UNKNOWN = "unknown";

    public static final String NFC = "NFC";
    public static final String RFID = "RFID";

    public static final String BASE_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + "/dbcargo/";
    public static final String FILE_PATH_JSONS = BASE_FILE_PATH + "jsons/";
    public static final String FILE_PATH_IMAGES = BASE_FILE_PATH + "images/";

    public static final Map<String, String> VALID_LOGIN_MAP;

    static {
        String password39 = "dbc039";
        String password57 = "dbc057";
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("team1", "dbcargo");
        map.put("team01", "dbc015");
        map.put("team02", "dbc030");
        map.put("team03", password39);
        map.put("team04", "dbc048");
        map.put("team05", "dbc061");
        map.put("team06", "dbc067");
        map.put("team07", "dbc083");
        map.put("team08", "dbc092");
        map.put("team09", "dbc109");
        map.put("team10", "dbc020");
        map.put("team11", "dbc026");
        map.put("team12", "dbc041");
        map.put("team13", "dbc054");
        map.put("team14", password57);
        map.put("team15", "dbc073");
        map.put("team16", "dbc076");
        map.put("team17", "dbc088");
        map.put("team18", "dbc107");
        map.put("team19", "dbc113");
        map.put("team20", "dbc033");
        map.put("team21", password39);
        map.put("team22", password57);
        map.put("team23", "dbc063");
        VALID_LOGIN_MAP = Collections.unmodifiableMap(map);
    }

    private Constants() {
        super();
    }

}
