package com.example.myreminder.reminder.domain.usecase

import com.example.myreminder.core.data.Resource
import com.example.myreminder.reminder.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderUseCase {
    fun getAllReminder(): Flow<Resource<List<Reminder>>>
    fun insertReminder(reminder: Reminder)
}