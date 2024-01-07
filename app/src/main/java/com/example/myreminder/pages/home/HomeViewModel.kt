package com.example.myreminder.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myreminder.core.domain.repository.ReminderRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val reminderRepository: ReminderRepository) : ViewModel() {

    init {
        fetchNewReminder()
    }
    fun getReminder() = reminderRepository.getAllReminder()

    private fun fetchNewReminder() = viewModelScope.launch {
        reminderRepository.fetchReminder()
    }
}