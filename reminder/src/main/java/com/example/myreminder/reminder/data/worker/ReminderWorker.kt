package com.example.myreminder.reminder.data.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReminderWorker(
    context: Context,
    workerParameters: WorkerParameters,
) : Worker(context, workerParameters) {

    private var resultStatus: Result? = null

    override fun doWork(): Result {
        resultStatus = Result.success()
        return resultStatus as Result
    }

    companion object {
        const val WORKER_NAME = "Reminder Worker"
    }

}