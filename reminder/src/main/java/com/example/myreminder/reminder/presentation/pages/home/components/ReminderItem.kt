package com.example.myreminder.reminder.presentation.pages.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myreminder.core.utils.DateUtils
import com.example.myreminder.design_system.ui.theme.Typography
import com.example.myreminder.design_system.ui.theme.blue400


@Composable
fun ReminderItem(
    id: Int, name: String, description: String, dateTime: String, modifier: Modifier = Modifier
) {
    val locale = DateUtils.convertToDateTime(dateTime)

    val hour = dateTime.substring(11)
    val day = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        locale.dayOfWeek.name.substring(0, 3)
    } else {
        "00"
    }
    val date = dateTime.substring(5, 10)

    val isPast = DateUtils.isPast(dateTime)
    Box(
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Box(
            modifier = modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    clip = true
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                    )
                    .height(IntrinsicSize.Min)
            ) {
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                            .background(
                                color = if (isPast) Color.DarkGray else blue400,
                                shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                            )
                            .width(80.dp)
                            .padding(vertical = 12.dp)
                    ) {
                        Text(
                            text = day,
                            fontWeight = FontWeight.Medium,
                            style = Typography.titleLarge,
                            color = Color.White
                        )
                        Text(
                            text = date,
                            style = Typography.bodyMedium,
                            color = Color.LightGray
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = modifier
                            .fillMaxHeight()
                            .padding(start = 16.dp),
                    ) {
                        Text(
                            text = name,
                            style = Typography.titleMedium.copy(
                                textDecoration = if (isPast) TextDecoration.LineThrough else null
                            ),
                        )
                        Text(
                            text = hour,
                            style = Typography.labelLarge,
                            color = Color.DarkGray,
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
        }
    }


}

@Preview(showBackground = true, backgroundColor = 0xFFFAFAFA)
@Composable
fun ReminderItemPreview() {
    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        ReminderItem(
            id = 1,
            name = "Olahraga",
            description = "Jogging bareng",
            dateTime = "2024-02-12 20:50",
        )
        ReminderItem(
            id = 0,
            name = "Olahraga",
            description = "Jogging bareng",
            dateTime = "2020-01-12 20:50",
        )
    }
}