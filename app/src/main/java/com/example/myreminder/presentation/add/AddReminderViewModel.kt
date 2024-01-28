package com.example.myreminder.presentation.add

import androidx.lifecycle.ViewModel
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.usecase.ReminderUseCase

class AddReminderViewModel(private val reminderUseCase: ReminderUseCase) : ViewModel() {
    fun addReminder(id: Int, title: String, description: String, date: String, time: String) : Boolean {
        if(title.isEmpty() || description.isEmpty() || time == "Pilih Waktu" || date == "Pilih Tanggal") {
            return false
        }
        val reminder = Reminder(
            id = id,
            title = title,
            description = description,
            dateTime = "$date $time"
        )
        reminderUseCase.insertReminder(reminder)
        return true
    }
}