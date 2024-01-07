package com.example.myreminder.pages.add

import androidx.lifecycle.ViewModel
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.repository.ReminderRepository

class AddReminderViewModel(private val reminderRepository: ReminderRepository) : ViewModel() {
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
        reminderRepository.insertReminder(reminder)
        return true
    }
}