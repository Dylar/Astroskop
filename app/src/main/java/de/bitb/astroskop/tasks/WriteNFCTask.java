package de.bitb.astroskop.tasks;


import android.content.Intent;
import android.os.AsyncTask;

import java.io.IOException;

import de.bitb.astroskop.exceptions.NFCNotEnoughSpaceException;
import de.bitb.astroskop.exceptions.NFCReadOnlyException;
import de.bitb.astroskop.exceptions.NFCTagIncompatibleException;
import de.bitb.astroskop.utils.NFCUtils;


public class WriteNFCTask extends AsyncTask<Void, Integer, Throwable> {

    private final Intent intent;
    private final NFCUtils nfcUtils;
    private final TaskCallback presenter;

    public WriteNFCTask(TaskCallback presenter, NFCUtils nfcUtils, Intent intent) {
        super();
        this.presenter = presenter;
        this.intent = intent;
        this.nfcUtils = nfcUtils;
    }

    @Override
    protected Throwable doInBackground(Void... voids) {
        try {
            boolean written = nfcUtils.handleWriteIntent(intent, this);
            return written ? new Throwable() : new Exception();
        } catch (NFCNotEnoughSpaceException | InterruptedException | IOException | NFCReadOnlyException | NFCTagIncompatibleException e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Throwable exception) {
        super.onPostExecute(exception);
        boolean success = false;
//        if (exception instanceof NFCNotEnoughSpaceException) {
//            presenter.getView().showNFCNotEnoughSpaceError();
//        } else if (exception instanceof NFCTagIncompatibleException) {
//            presenter.getView().showNFCTagIncompatibleError();
//        } else if (exception instanceof NFCReadOnlyException) {
//            presenter.getView().showNFCReadOnlyError();
//        } else if (exception instanceof FormatException || exception instanceof IOException) {
//            presenter.getView().showNFCIOError();
//        } else {
//            success = true;
//        }
        presenter.onTaskCompleted(success);
    }

    public void postProgress(int progress) {
        publishProgress(progress);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        presenter.postProgress(values[0]);
    }

    public void start() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
