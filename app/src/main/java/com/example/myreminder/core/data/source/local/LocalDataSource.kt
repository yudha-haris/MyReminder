package com.example.myreminder.core.data.source.local

import com.example.myreminder.core.data.source.local.entity.ReminderEntity
import com.example.myreminder.core.data.source.local.room.ReminderDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val reminderDao: ReminderDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(reminderDao: ReminderDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(reminderDao)
            }
    }

    fun getAllReminder(): Flow<List<ReminderEntity>> = reminderDao.getAllReminder()

    suspend fun insertAllReminder(reminderList: List<ReminderEntity>) =
        reminderDao.insertAllReminder(reminderList)

    suspend fun insertReminder(reminderEntity: ReminderEntity) = reminderDao.insertReminder(reminderEntity)

    fun deleteReminder(reminderEntity: ReminderEntity) = reminderDao.deleteReminder(reminderEntity)
}