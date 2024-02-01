package com.example.myreminder.core.domain.repository

import com.example.myreminder.core.data.source.Resource
import com.example.myreminder.core.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface IReminderRepository {
    fun getAllReminder(): Flow<Resource<List<Reminder>>>
    fun insertReminder(reminder: Reminder)
}