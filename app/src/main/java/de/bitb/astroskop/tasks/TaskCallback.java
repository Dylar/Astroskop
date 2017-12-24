package de.bornholdtlee.dbsystel.tasks;

public interface TaskCallback {

    void postProgress(int progress);

    void onTaskCompleted(boolean success);
}
