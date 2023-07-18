package com.pradyotprakash.notes.core.worker

import android.content.Context
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pradyotprakash.notes.core.utils.launchPeriodicAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.TimeUnit

class ToastWorker(private val appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {
    private val toastJob = CoroutineScope(Dispatchers.Main).launchPeriodicAsync(TimeUnit.SECONDS.toMillis(5)) {
        Toast.makeText(appContext, "ToastWorker", Toast.LENGTH_SHORT).show()
    }

    override fun doWork(): Result {
        toastJob.start()

        return Result.success()
    }
}