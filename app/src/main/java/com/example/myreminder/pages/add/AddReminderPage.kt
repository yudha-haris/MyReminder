package com.example.myreminder.pages.add

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myreminder.R
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.navigation.Page
import com.example.myreminder.pages.add.components.showDatePickerDialog
import com.example.myreminder.pages.add.components.showTimePickerDialog
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderPage(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: AddReminderViewModel
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
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

    Scaffold(
        content = {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {
                val context = LocalContext.current

                if (openTime.value) {
                    showTimePickerDialog(onCancel = {
                        openTime.value = false
                    }, onConfirm = { value ->
                        time = value
                        openTime.value = false
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
                        onValueChange = { value ->
                            title = value
                        },
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
                        onValueChange = { value ->
                            description = value
                        },
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

                    Button(
                        onClick = {

                            if (title.isEmpty() || description.isEmpty() || time == "Pilih Waktu" || date == "Pilih Tanggal") {
                                Toast.makeText(context, "Lengkapi Data Terlebih Dahulu!", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                val now = Date()
                                val reminder = Reminder(
                                    id = now.time.toInt(),
                                    title = title,
                                    description = description,
                                    dateTime = "$date $time"
                                )
                                viewModel.addReminder(reminder)
                                Toast.makeText(context, "Pengingat berhasil ditambahkan", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(Page.Home.route)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Text(
                            text = "Tambahkan",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        },
    )
}
