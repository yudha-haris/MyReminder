package com.example.myreminder.pages.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.repository.ReminderRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeViewModel(private val reminderRepository: ReminderRepository) : ViewModel() {

    init {
        fetchNewReminder()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getReminder() = reminderRepository.getAllReminder().map {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val now = LocalDateTime.now()
        val sorted = it.sortedBy {item ->
            LocalDateTime.parse(item.dateTime, formatter)
        }
        val result = ArrayList<Reminder>()

        val active = ArrayList<Reminder>()
        val inactive = ArrayList<Reminder>()
        sorted.forEach {item ->
            val locale = LocalDateTime.parse(item.dateTime, formatter)
            val isPast = now > locale;
            if(isPast) {
                inactive.add(item)
            } else {
                active.add(item)
            }
        }

        result.addAll(active)
        result.addAll(inactive)

        return@map result
    }

    fun fetchNewReminder() = viewModelScope.launch {
        reminderRepository.fetchReminder()
    }
}