package de.bitb.astroskop.tasks;

public interface TaskCallback {

    void postProgress(int progress);

    void onTaskCompleted(boolean success);
}
