package com.example.myreminder.pages.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myreminder.core.navigation.Page
import com.example.myreminder.pages.home.components.ReminderItem
import com.example.myreminder.ui.theme.MyReminderTheme

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(
    viewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val reminders by viewModel.getReminder().observeAsState(initial = emptyList())

    Scaffold(
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                items(reminders, key = { it.id }) {
                    ReminderItem(
                        id = it.id,
                        name = it.title,
                        description = it.description,
                        dateTime = it.dateTime,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Page.Add.route)
            }, modifier = Modifier.padding(16.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Reminder")
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun ReminderListItemPreview() {
    MyReminderTheme {
        Scaffold(
            content = {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(16.dp)
                ) {
                    ReminderItem(
                        id = 0,
                        name = "Olahraga",
                        description = "Jogging bareng",
                        dateTime = "2024-01-12 20:50"
                    )
                    ReminderItem(
                        id = 0,
                        name = "Olahraga",
                        description = "Jogging bareng",
                        dateTime = "2024-01-12 20:50"
                    )
                    ReminderItem(
                        id = 0,
                        name = "Olahraga",
                        description = "Jogging bareng",
                        dateTime = "2024-01-12 20:50"
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {}, modifier = Modifier.padding(16.dp)) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Reminder")
                }

            },
        )
    }
}