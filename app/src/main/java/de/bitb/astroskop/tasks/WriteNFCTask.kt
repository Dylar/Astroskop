package de.bitb.astroskop.tasks


import android.content.Intent
import android.os.AsyncTask

import java.io.IOException

import de.bitb.astroskop.exceptions.NFCNotEnoughSpaceException
import de.bitb.astroskop.exceptions.NFCReadOnlyException
import de.bitb.astroskop.exceptions.NFCTagIncompatibleException
import de.bitb.astroskop.utils.NFCUtils


class WriteNFCTask(private val presenter: TaskCallback, private val nfcUtils: NFCUtils, private val intent: Intent) : AsyncTask<Void, Int, Throwable>() {

    override fun doInBackground(vararg voids: Void): Throwable {
        try {
            val written = nfcUtils.handleWriteIntent(intent, this)
            return if (written) Throwable() else Exception()
        } catch (e: NFCNotEnoughSpaceException) {
            return e
        } catch (e: InterruptedException) {
            return e
        } catch (e: IOException) {
            return e
        } catch (e: NFCReadOnlyException) {
            return e
        } catch (e: NFCTagIncompatibleException) {
            return e
        }

    }

    override fun onPostExecute(exception: Throwable) {
        super.onPostExecute(exception)
        val success = false
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
        presenter.onTaskCompleted(success)
    }

    fun postProgress(progress: Int) {
        publishProgress(progress)
    }

    protected override fun onProgressUpdate(vararg values: Int) {
        super.onProgressUpdate(*values)
        presenter.postProgress(values[0])
    }

    fun start() {
        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }
}
