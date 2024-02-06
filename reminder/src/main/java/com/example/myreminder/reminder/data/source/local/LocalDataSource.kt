package com.example.myreminder.reminder.data.source.local

import com.example.myreminder.reminder.data.source.local.entity.ReminderEntity
import com.example.myreminder.reminder.data.source.local.room.ReminderDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val reminderDao: ReminderDao) {

    fun getAllReminder(): Flow<List<ReminderEntity>> = reminderDao.getAllReminder()

    suspend fun insertAllReminder(reminderList: List<ReminderEntity>) =
        reminderDao.insertAllReminder(reminderList)

    suspend fun insertReminder(reminderEntity: ReminderEntity) = reminderDao.insertReminder(reminderEntity)

    fun deleteReminder(reminderEntity: ReminderEntity) = reminderDao.deleteReminder(reminderEntity)
}