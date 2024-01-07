package com.example.myreminder.pages.add.components

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDatePickerDialog(onCancel: () -> Unit, onDateSelected: (String) -> Unit) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onCancel,
        confirmButton = {
            TextButton(
                onClick = {
                    val selectedDateInMillis = datePickerState.selectedDateMillis;
                    val sdf = SimpleDateFormat("yyyy-MM-dd")
                    val dateString = sdf.format(selectedDateInMillis)
                    onDateSelected(dateString)
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onCancel
            ) {
                Text("CANCEL")
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showTimePickerDialog(
    title: String = "Select Time",
    onCancel: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    val state = rememberTimePickerState()

    Dialog(
        onDismissRequest = onCancel,
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                TimePicker(
                    state = state,
                    layoutType = TimePickerLayoutType.Vertical
                )
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        onClick = onCancel
                    ) { Text("Cancel") }
                    TextButton(
                        onClick = {
                            var hourRaw = state.hour;
                            val minRaw = state.minute;
                            var hour = hourRaw.toString();
                            if (hourRaw < 10) {
                                hour = "0${hourRaw}"
                            }
                            var min = minRaw.toString()
                            if (minRaw < 10) {
                                min = "0${minRaw}"
                            }
                            onConfirm("${hour}:${min}")
                        }
                    ) { Text("OK") }
                }
            }
        }
    }
}