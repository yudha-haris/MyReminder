package com.example.myreminder.reminder.presentation.pages.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myreminder.core.utils.DateUtils
import com.example.myreminder.design_system.ui.theme.Typography
import com.example.myreminder.design_system.ui.theme.blue400
import com.example.myreminder.design_system.ui.theme.blue600
import com.example.myreminder.reminder.domain.model.Reminder

@Composable
fun ReminderSummaryCard(reminderList: List<Reminder>, modifier: Modifier = Modifier) {
    val count = reminderList.size
    val finished = reminderList.filter {
        DateUtils.isPast(it.dateTime)
    }.size
    val milestone: Int = (finished.div(count.toFloat()) * 100).toInt()
    Box(
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            blue600,
                            blue400,
                            blue600,
                        )
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "Reminder Summary",
                    style = Typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "$count reminders",
                    style = Typography.bodyMedium,
                    color = Color.LightGray,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.width(IntrinsicSize.Min)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = "Milestone",
                            color = Color.White,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.weight(2f))
                        Text(text = "${milestone}%", color = Color.White)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if(finished != 0) {
                            Box(
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .weight(if(finished == count) 1f else finished.toFloat())
                                    .padding(2.dp)
                            )
                        }
                        if(finished != count) {
                            Box(
                                modifier = Modifier
                                    .background(color = Color.White.copy(alpha = 0.3f))
                                    .weight((count - finished).toFloat())
                                    .padding(2.dp)
                            )
                        }


                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReminderSummaryCardPreview() {
    val reminders = listOf<Reminder>(
        Reminder(0, "Olahraga", "", "2024-06-12 20:50"),
        Reminder(1, "Jogging", "", "2020-01-12 20:50"),
        Reminder(2, "Ngoding", "", "2024-06-15 20:50"),
    )
    ReminderSummaryCard(reminderList = reminders)
}