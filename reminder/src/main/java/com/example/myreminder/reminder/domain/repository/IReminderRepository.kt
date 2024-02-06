package com.example.myreminder.reminder.domain.repository

import com.example.myreminder.core.data.Resource
import com.example.myreminder.reminder.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface IReminderRepository {
    fun getAllReminder(): Flow<Resource<List<Reminder>>>
    fun insertReminder(reminder: Reminder)
}