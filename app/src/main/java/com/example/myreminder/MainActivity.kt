package com.example.myreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.myreminder.core.domain.ui.ViewModelFactory
import com.example.myreminder.main.MyReminderApp
import com.example.myreminder.main.ReminderViewModel
import com.example.myreminder.ui.theme.MyReminderTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ReminderViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyReminderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyReminderApp(viewModel)
                }
            }
        }
    }
}