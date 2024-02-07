package com.example.myreminder.reminder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import com.example.myreminder.design_system.ui.theme.MyReminderTheme
import com.example.myreminder.reminder.data.worker.ReminderWorker
import com.example.myreminder.reminder.di.databaseModule
import com.example.myreminder.reminder.di.networkModule
import com.example.myreminder.reminder.di.repositoryModule
import com.example.myreminder.reminder.di.useCaseModule
import com.example.myreminder.reminder.di.viewModelModule
import com.example.myreminder.reminder.presentation.pages.add.AddReminderViewModel
import com.example.myreminder.reminder.presentation.pages.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import java.util.concurrent.TimeUnit

class ReminderActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModel()
    private val addReminderViewModel: AddReminderViewModel by viewModel()

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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReminderApp(
                        homeViewModel = homeViewModel,
                        addReminderViewModel = addReminderViewModel
                    )
                }
            }
        }

        loadKoinModules(listOf(
            databaseModule,
            networkModule,
            repositoryModule,
            useCaseModule,
            viewModelModule
        ))

        initWorkManager()
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
        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id).observe(this@ReminderActivity) {
            if (it.state == WorkInfo.State.ENQUEUED) {
                homeViewModel.getReminder()
            }
        }
    }
}