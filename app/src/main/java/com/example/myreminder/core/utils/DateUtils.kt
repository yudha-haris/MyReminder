package com.example.myreminder.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertToDateTime(dateTime: String): LocalDateTime {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return LocalDateTime.parse(dateTime, inputFormatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isPast(dateTime: String) : Boolean {
        val locale = convertToDateTime(dateTime)
        val now = LocalDateTime.now()
        return now > locale;
    }
}