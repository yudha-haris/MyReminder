package com.example.myreminder

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.myreminder.core.data.worker.ReminderWorker
import com.example.myreminder.core.ui.ViewModelFactory
import com.example.myreminder.presentation.add.AddReminderViewModel
import com.example.myreminder.presentation.home.HomeViewModel
import com.example.myreminder.ui.theme.MyReminderTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    private val addReminderViewModel: AddReminderViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(applicationContext, "Notification Granted", Toast.LENGTH_SHORT).show()
        }
    }
    private lateinit var workManager: WorkManager
    private lateinit var periodicWorkRequest: PeriodicWorkRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyReminderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyReminderApp(
                        homeViewModel = homeViewModel,
                        addReminderViewModel = addReminderViewModel
                    )
                }
            }
        }
        askPermission()
        initWorkManager()
    }

    private fun askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun initWorkManager() {
        workManager = WorkManager.getInstance(applicationContext)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        periodicWorkRequest =
            PeriodicWorkRequest.Builder(ReminderWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        workManager.enqueueUniquePeriodicWork(
            ReminderWorker.WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id).observe(this@MainActivity) {
            if (it.state == WorkInfo.State.ENQUEUED) {
                homeViewModel.getReminder()
            }
        }
    }
}