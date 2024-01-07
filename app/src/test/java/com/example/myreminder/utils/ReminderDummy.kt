package com.example.myreminder.utils

import com.example.myreminder.core.domain.model.Reminder

object ReminderDummy {
    fun generateReminderDummy(): List<Reminder> {
        val reminderList = ArrayList<Reminder>()
        for(i in 0..9) {
            val reminder = Reminder(
                i,
                "Judul $i",
                "Deskripsi $i",
                "2024-07-01 14:0$i",
            )
            reminderList.add(reminder)
        }
        return reminderList
    }
}