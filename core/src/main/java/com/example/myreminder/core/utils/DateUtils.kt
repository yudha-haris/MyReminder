package com.example.myreminder.core.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

object DateUtils {

//    
    fun convertToDateTime(dateTime: String): LocalDateTime {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return LocalDateTime.parse(dateTime, inputFormatter)
    }

    
    fun isPast(dateTime: String) : Boolean {
        val locale = convertToDateTime(dateTime)
        val now = LocalDateTime.now()
        return now > locale
    }

    fun parseCalender(date: String, time: String): Calendar {
        val dateArray = date.split("-").toTypedArray()
        val timeArray = time.split(":").toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, Integer.parseInt(dateArray[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]) - 1)
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[2]))
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        return calendar
    }
}