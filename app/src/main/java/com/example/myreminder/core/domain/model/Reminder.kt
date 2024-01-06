package com.example.myreminder.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reminder(
    var id: Int,
    var title: String,
    var description: String,
    var dateTime: String,
) : Parcelable
