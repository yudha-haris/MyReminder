package com.example.myreminder.core.data.receiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.myreminder.R
import com.example.myreminder.core.utils.DateUtils

class AlarmReceiver : BroadcastReceiver() {

    fun setOneTimeAlarm(
        context: Context,
        date: String,
        time: String,
        message: String,
        title: String,
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TITLE, title)

        try {
            val calendar = DateUtils.parseCalender(date, time)
            val pendingIntent =
                PendingIntent.getBroadcast(context, ID_ONETIME, intent, PendingIntent.FLAG_IMMUTABLE)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        } catch (e: Exception) {
            Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val title = intent.getStringExtra(EXTRA_TITLE)
        showNotification(context, title ?: "", message ?: "", ID_ONETIME)
    }

    private fun showNotification(
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
        private const val EXTRA_TITLE = "extra_title"
        private const val EXTRA_MESSAGE = "extra_message"
        private const val ID_ONETIME = 100
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "Notification channel"
    }
}