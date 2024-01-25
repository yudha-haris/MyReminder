package com.example.myreminder.core.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myreminder.R

class NotificationUtils {
    fun showNotification(
        context: Context,
        title: String,
        message: String,
        notificationId: Int,
    ) {
        val nManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.baseline_access_alarm_24)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            builder.setChannelId(CHANNEL_ID)
            nManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        nManagerCompat.notify(notificationId, notification)

    }

    companion object {
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "Notification channel"
    }
}