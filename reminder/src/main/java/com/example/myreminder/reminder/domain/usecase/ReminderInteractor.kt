package com.example.myreminder.reminder.domain.usecase

import com.example.myreminder.core.data.Resource
import com.example.myreminder.reminder.domain.model.Reminder
import com.example.myreminder.reminder.domain.repository.IReminderRepository
import kotlinx.coroutines.flow.Flow

class ReminderInteractor(private val reminderRepository: IReminderRepository) : ReminderUseCase {
    override fun getAllReminder(): Flow<Resource<List<Reminder>>> = reminderRepository.getAllReminder()

    override fun insertReminder(reminder: Reminder) = reminderRepository.insertReminder(reminder)

}