package com.example.myreminder.pages.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.repository.ReminderRepository
import com.example.myreminder.core.utils.DateUtils
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeViewModel(private val reminderRepository: ReminderRepository) : ViewModel() {

    init {
        fetchNewReminder()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getReminder() = reminderRepository.getAllReminder().map {
        val sorted = it.sortedBy {item ->
            DateUtils.convertToDateTime(item.dateTime)
        }
        val result = ArrayList<Reminder>()

        val active = ArrayList<Reminder>()
        val inactive = ArrayList<Reminder>()
        sorted.forEach {item ->
            val isPast = DateUtils.isPast(item.dateTime);
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