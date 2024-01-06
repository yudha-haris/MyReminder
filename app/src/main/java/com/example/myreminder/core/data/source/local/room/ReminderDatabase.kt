package com.example.myreminder.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myreminder.core.data.source.local.entity.ReminderEntity

@Database(entities = [ReminderEntity::class], version = 1, exportSchema = false)
abstract class ReminderDatabase : RoomDatabase() {

    abstract fun reminderDao(): ReminderDao

    companion object {
        @Volatile
        private var INSTANCE: ReminderDatabase? = null

        fun getInstance(context: Context): ReminderDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReminderDatabase::class.java,
                    "Reminder.db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }


}