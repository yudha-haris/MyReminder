package com.example.myreminder.core.domain.usecase

import com.example.myreminder.core.data.source.Resource
import com.example.myreminder.core.domain.model.Reminder
import com.example.myreminder.core.domain.repository.IReminderRepository
import kotlinx.coroutines.flow.Flow

class ReminderInteractor(private val reminderRepository: IReminderRepository) : ReminderUseCase {
    override fun getAllReminder(): Flow<Resource<List<Reminder>>> = reminderRepository.getAllReminder()

    override fun insertReminder(reminder: Reminder) = reminderRepository.insertReminder(reminder)

}