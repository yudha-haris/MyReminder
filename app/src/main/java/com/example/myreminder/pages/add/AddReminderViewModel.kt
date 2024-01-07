package com.example.myreminder.pages.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.repository.ReminderRepository

class AddReminderViewModel(private val reminderRepository: ReminderRepository) : ViewModel() {
    fun addReminder(reminder: Reminder) = reminderRepository.insertReminder(reminder)
}