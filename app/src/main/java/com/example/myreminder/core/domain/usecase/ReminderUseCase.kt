package com.example.myreminder.core.domain.usecase

import com.example.myreminder.core.data.source.Resource
import com.example.myreminder.core.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface ReminderUseCase {
    fun getAllReminder(): Flow<Resource<List<Reminder>>>
    fun insertReminder(reminder: Reminder)
}