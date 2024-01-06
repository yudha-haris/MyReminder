package com.example.myreminder.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.repository.ReminderRepository
import kotlinx.coroutines.launch

class ReminderViewModel(private val reminderRepository: ReminderRepository) : ViewModel() {
    fun getReminder() = reminderRepository.getAllReminder()

    fun fetchNewReminder() = viewModelScope.launch {
        reminderRepository.fetchReminder()
    }

    fun insertReminder(reminder: Reminder) = reminderRepository.insertReminder(reminder)
}