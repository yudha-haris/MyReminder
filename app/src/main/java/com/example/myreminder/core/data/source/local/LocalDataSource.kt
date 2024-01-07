package com.example.myreminder.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.myreminder.core.data.source.local.entity.ReminderEntity
import com.example.myreminder.core.data.source.local.room.ReminderDao

class LocalDataSource private constructor(private val reminderDao: ReminderDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(reminderDao: ReminderDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(reminderDao)
            }
    }

    fun getAllReminder(): LiveData<List<ReminderEntity>> = reminderDao.getAllReminder()

    fun insertAllReminder(reminderList: List<ReminderEntity>) =
        reminderDao.insertAllReminder(reminderList)

    fun insertReminder(reminderEntity: ReminderEntity) = reminderDao.insertReminder(reminderEntity)

    fun deleteReminder(reminderEntity: ReminderEntity) = reminderDao.deleteReminder(reminderEntity)
}