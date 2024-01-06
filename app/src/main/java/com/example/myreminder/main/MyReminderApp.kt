package com.example.myreminder.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myreminder.ui.theme.MyReminderTheme

@Composable
fun MyReminderApp(
    reminderViewModel: ReminderViewModel,
    modifier: Modifier = Modifier,
) {

    LaunchedEffect(true) {
        reminderViewModel.fetchNewReminder()
    }

    val reminders by reminderViewModel.getReminder().observeAsState(initial = emptyList())

    Box(modifier = modifier) {
        LazyColumn {
            items(reminders, key = { it.id }) {
                ReminderListItem(
                    name = it.title,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ReminderListItem(
    name: String, modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier.clickable { }) {
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}

@Preview
@Composable
fun ReminderListItemPreview() {
    MyReminderTheme {
        ReminderListItem(name = "H.O.S Cokroaminoto", )
    }
}