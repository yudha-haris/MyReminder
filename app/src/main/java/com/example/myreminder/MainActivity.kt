package com.example.myreminder

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myreminder.core.domain.ui.ViewModelFactory
import com.example.myreminder.pages.add.AddReminderViewModel
import com.example.myreminder.pages.home.HomeViewModel
import com.example.myreminder.ui.theme.MyReminderTheme

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    private val addReminderViewModel: AddReminderViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
    }
}