//package de.bitb.astroskop.tasks;
//
//import android.os.AsyncTask;
//
//import java.io.File;
//import java.io.IOException;
//
//import de.bitb.astroskop.helper.Logger;
//import de.bitb.astroskop.utils.CameraUtils;
//import de.bitb.astroskop.utils.FileUtils;
//import lombok.Getter;
//
//public class CompressImage extends AsyncTask<Void, Void, Boolean> {
//
//    private final TaskCallback taskCallback;
//    private final CameraUtils cameraUtils;
//    private final FileUtils fileUtils;
//    private final CameraUtils.ImageInfo info;
//
//    @Getter
//    private String path;
//
//    public CompressImage(TaskCallback taskCallback, CameraUtils cameraUtils, FileUtils fileUtils, CameraUtils.ImageInfo info) {
//        super();
//        this.taskCallback = taskCallback;
//        this.cameraUtils = cameraUtils;
//        this.fileUtils = fileUtils;
//        this.info = info;
//    }
//
//    @Override
//    protected Boolean doInBackground(Void... voids) {
//        path = cameraUtils.getPicturePath(info);
//        if (path != null) {
//            File source = new File(path);
//            File destiny = new File(info.getImageDirectory() + path.substring(path.lastIndexOf('/')));
//            try {
//                fileUtils.move(source, destiny);
//            } catch (IOException e) {
//                Logger.debug(e.getMessage());
//            }
//            path = destiny.getPath();
//        }
//        return path != null;
//    }
//
//    @Override
//    protected void onPostExecute(Boolean success) {
//        super.onPostExecute(success);
//        taskCallback.onTaskCompleted(success);
//    }
//
//    public void start() {
//        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//    }
//}