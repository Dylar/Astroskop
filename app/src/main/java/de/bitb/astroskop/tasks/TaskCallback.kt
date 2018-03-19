package de.bitb.astroskop.tasks

interface TaskCallback {

    fun postProgress(progress: Int)

    fun onTaskCompleted(success: Boolean)
}
