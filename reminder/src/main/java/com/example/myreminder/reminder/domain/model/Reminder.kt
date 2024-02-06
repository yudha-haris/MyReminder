package com.example.myreminder.reminder.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reminder(
    var id: Int,
    var title: String,
    var description: String,
    var dateTime: String,
) : Parcelable
