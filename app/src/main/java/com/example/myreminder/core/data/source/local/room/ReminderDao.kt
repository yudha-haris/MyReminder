package com.example.myreminder.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myreminder.core.data.source.local.entity.ReminderEntity

@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminder")
    fun getAllReminder(): LiveData<List<ReminderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllReminder(reminderList: List<ReminderEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReminder(reminder: ReminderEntity)

    @Delete
    fun deleteReminder(reminder: ReminderEntity)
}