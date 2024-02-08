package com.example.myreminder.reminder.presentation.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myreminder.core.data.Resource
import com.example.myreminder.design_system.ui.theme.Typography
import com.example.myreminder.reminder.domain.model.Reminder
import com.example.myreminder.reminder.presentation.navigation.Page
import com.example.myreminder.reminder.presentation.pages.home.components.ReminderCard
import com.example.myreminder.reminder.presentation.pages.home.components.ReminderSummaryCard


@Composable
fun HomePage(
    viewModel: HomeViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val data by viewModel.getReminder().observeAsState(initial = Resource.Loading(emptyList()))

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Reminder",
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                when (data) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {
                        item {
                            Box(
                                modifier = modifier
                                    .fillMaxSize()
                            ) {
                                Column(
                                    modifier = Modifier.align(alignment = Alignment.Center)
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }

                    is Resource.Success -> {
                        val reminders = data.data ?: emptyList()

                        item {
                            Column {
                                ReminderSummaryCard(reminders, modifier = Modifier.padding(bottom = 16.dp))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 12.dp)
                                ) {
                                    Text(text = "Recent Todos", style = Typography.titleMedium)
                                    Text(
                                        text = "See All",
                                        style = Typography.bodyMedium,
                                        color = Color.DarkGray
                                    )

                                }
                            }
                        }
                        items(reminders, key = { it.id }) {
                            ReminderCard(
                                id = it.id,
                                name = it.title,
                                description = it.description,
                                dateTime = it.dateTime,
                            )
                        }
                    }
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

@Preview
@Composable
fun ReminderListItemPreview() {
    val reminders = listOf<Reminder>(
        Reminder(0, "Olahraga", "", "2024-02-12 20:50"),
        Reminder(1, "Jogging", "", "2020-01-12 20:50")
    )

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Reminder",
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(24.dp)
            ) {
                item {
                    Column {
                        ReminderSummaryCard(reminders, modifier = Modifier.padding(bottom = 16.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        ) {
                            Text(text = "Recent Todos", style = Typography.titleMedium)
                            Text(
                                text = "See All",
                                style = Typography.bodyMedium,
                                color = Color.DarkGray
                            )

                        }
                    }
                }
                items(reminders, key = { it.id }) {
                    ReminderCard(
                        id = it.id,
                        name = it.title,
                        description = it.description,
                        dateTime = it.dateTime,
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}, modifier = Modifier.padding(16.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Reminder")
            }
        },
    )
}