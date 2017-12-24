package de.bitb.astroskop.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import de.bitb.astroskop.helper.Logger;

import static de.bitb.astroskop.Constants.FILE_PATH_JSONS;


public class FileUtils {

    public static final String FILE_TYPE_JSON = ".json";

    public void copy(File source, File destiny) throws IOException {
        File dir = destiny.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (destiny.exists()) {
            destiny.delete();
        }

        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(destiny);
            FileChannel inChannel = inStream.getChannel();
            FileChannel outChannel = outStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (FileNotFoundException e) {
            Logger.debug(e.getMessage());
        } finally {
            inStream.close();
            outStream.close();
        }
    }

    public void move(File source, File destiny) throws IOException {
        copy(source, destiny);
        source.delete();
    }

    public void writeJsonToFile(String json, String filename) {
        writeToFile(json, FILE_PATH_JSONS, filename, FILE_TYPE_JSON);
    }

    @SuppressWarnings("PMD")
    public void writeToFile(String data, String dirPath, String filename, String type) {
        File fileDir = new File(dirPath);
        File myFile = new File(fileDir.getPath() + filename + type);

        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        if (myFile.exists()) {
            myFile.delete();
        }

        try {
            FileOutputStream fOut = new FileOutputStream(myFile, false);
            fOut.write(data.getBytes(StandardCharsets.UTF_8));
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            Logger.error("Write to JSON File failed for file " + filename);
        }
    }

}
