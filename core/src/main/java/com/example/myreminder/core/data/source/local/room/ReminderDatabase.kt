package com.example.myreminder.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myreminder.core.data.source.local.entity.ReminderEntity

@Database(entities = [ReminderEntity::class], version = 1, exportSchema = false)
abstract class ReminderDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}