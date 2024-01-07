package com.example.myreminder.pages.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myreminder.R
import com.example.myreminder.pages.add.components.showDatePickerDialog
import com.example.myreminder.pages.add.components.showTimePickerDialog
import com.example.myreminder.ui.theme.MyReminderTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderPage(navController: NavHostController, modifier: Modifier = Modifier) {
    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {
                AddReminderForm()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Handle save button click
                },
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddReminderForm() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf("") }
    var date by remember {
        mutableStateOf("Pilih Tanggal")
    }
    var time by remember {
        mutableStateOf("Pilih Waktu")
    }

    val openDate = remember { mutableStateOf(false) }
    val openTime = remember {
        mutableStateOf(false)
    }

    if (openTime.value) {
        showTimePickerDialog(onCancel = {
            openTime.value = false;
        }, onConfirm = {
            time = it
            openTime.value = false;
        })
    }

    if (openDate.value) {
        showDatePickerDialog(onCancel = {
            openDate.value = false
        }) { selectedDate ->
            date = selectedDate
            openDate.value = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "Judul") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Deskripsi") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable {
                    openDate.value = true
                }
        ) {
            Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = date,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable {
                    openTime.value = true
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_access_alarm_24),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = time,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddReminderPagePreview() {
    MyReminderTheme {
        AddReminderPage(navController = rememberNavController())
    }
}