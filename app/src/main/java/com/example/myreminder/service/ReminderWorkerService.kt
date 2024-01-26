package com.example.myreminder.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReminderWorkerService(
    context: Context,
    workerParameters: WorkerParameters,
) : Worker(context, workerParameters) {

    private var resultStatus: Result? = null

    override fun doWork(): Result {
        resultStatus = Result.success()
        return resultStatus as Result
    }

}