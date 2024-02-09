package com.example.myreminder.reminder.presentation.pages.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myreminder.design_system.components.atom.AppToast
import com.example.myreminder.design_system.components.atom.PrimaryButton
import com.example.myreminder.design_system.components.molecule.PrimaryTextField
import com.example.myreminder.design_system.components.molecule.PrimaryTopBar
import com.example.myreminder.design_system.ui.theme.Typography
import com.example.myreminder.design_system.ui.theme.primaryBlue
import com.example.myreminder.reminder.R
import com.example.myreminder.reminder.data.receiver.AlarmReceiver
import com.example.myreminder.reminder.presentation.navigation.Page
import com.example.myreminder.reminder.presentation.pages.add.components.ShowDatePickerDialog
import com.example.myreminder.reminder.presentation.pages.add.components.ShowTimePickerDialog
import java.util.Date


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
        topBar = {
            PrimaryTopBar(title = "Tambah Pengingat")
        },
        content = {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
                    .padding(top = 16.dp)
            ) {
                val context = LocalContext.current

                if (openTime.value) {
                    ShowTimePickerDialog(onCancel = {
                        openTime.value = false
                    }, onConfirm = { value ->
                        time = value
                        openTime.value = false
                    })
                }

                if (openDate.value) {
                    ShowDatePickerDialog(onCancel = {
                        openDate.value = false
                    }) { selectedDate ->
                        date = selectedDate
                        openDate.value = false
                    }
                }

                AddReminderBody(
                    title = title,
                    onTitleChange = {
                        title = it
                    },
                    description = description,
                    onDescriptionChange = {
                        description = it
                    },
                    date = date,
                    onOpenDate = {
                        openDate.value = true
                    },
                    time = time,
                    onOpenTime = { openTime.value = true },
                    onSave = {
                        val now = Date()
                        val isSuccess = viewModel.addReminder(
                            now.time.toInt(),
                            title,
                            description,
                            date,
                            time
                        )
                        if (isSuccess) {
                            val alarmReceiverService = AlarmReceiver()
                            alarmReceiverService.setOneTimeAlarm(
                                context,
                                date,
                                time,
                                description,
                                title
                            )
                            AppToast.showDefaultToast("Pengingat berhasil ditambahkan", context)
                            navController.navigate(Page.Home.route)
                        } else {
                            AppToast.showDefaultToast("Lengkapi Data Terlebih Dahulu!", context)
                        }
                    },
                )
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderBody(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    date: String,
    onOpenDate: () -> Unit,
    time: String,
    onOpenTime: () -> Unit,
    onSave: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column {
            Text(
                text = "Judul Pengingat",
                style = Typography.titleMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            PrimaryTextField(
                value = title,
                onValueChange = onTitleChange,
                placeholder = "Masukkan judul..."
            )
            Text(
                text = "Deskripsi",
                style = Typography.titleMedium,
                modifier = Modifier.padding(bottom = 4.dp, top = 8.dp)
            )
            PrimaryTextField(
                value = description,
                onValueChange = onDescriptionChange,
                placeholder = "Masukkan Deskripsi..."
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clickable {
                        onOpenDate()
                    }
            ) {
                Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null, tint = primaryBlue)
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
                        onOpenTime()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_access_alarm_24),
                    tint = primaryBlue,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = time,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        PrimaryButton(onClick = onSave, text = "Tambahkan")
    }
}

@Preview
@Composable
fun AddReminderPreview() {
    Scaffold(
        topBar = {
            PrimaryTopBar(title = "Tambah Pengingat")
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
                    .padding(top = 16.dp)
            ) {
                AddReminderBody(
                    title = "",
                    onTitleChange = {},
                    description = "",
                    onDescriptionChange = {},
                    date = "17-08-2023",
                    onOpenDate = {},
                    time = "21:55",
                    onOpenTime = { },
                    onSave = {
                    },
                )
            }
        }
    )

}
