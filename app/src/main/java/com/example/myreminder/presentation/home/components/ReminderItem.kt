package com.example.myreminder.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.utils.DateUtils


@Composable
fun ReminderItem(
    id: Int, name: String, description: String, dateTime: String, modifier: Modifier = Modifier
) {
    val locale = DateUtils.convertToDateTime(dateTime)

    val hour = dateTime.substring(11)
    val day = locale.dayOfWeek.name.substring(0, 3)
    val date = dateTime.substring(5, 10)

    val isPast = DateUtils.isPast(dateTime)


    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .clickable {
                var reminder =
                    Reminder(id = id, title = name, description = description, dateTime = dateTime)
            }
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = day,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleLarge.copy(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )
            Text(
                text = date,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.titleMedium.copy(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            )
        }
        Card(
            modifier = Modifier.weight(3f)
        ) {

            Box(
                modifier = modifier.background(color = if (isPast) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary)
            ) {
                Column(
                    Modifier.padding(16.dp)
                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    )
                    Text(
                        text = hour,
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    )
                }

            }
        }
    }
}