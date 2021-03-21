package com.example.alarmclockdemo.presenter.receivers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.alarmclockdemo.R

class AlarmClockReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        var notifyManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notifyChannel = notifyManager.getNotificationChannel("571571")

            if (notifyChannel == null) {
                notifyChannel = NotificationChannel("571571", "s.a.v", NotificationManager.IMPORTANCE_HIGH)
                notifyChannel.enableVibration(true)
                notifyManager.createNotificationChannel(notifyChannel)
            }
        }

        var notification: NotificationCompat.Builder = NotificationCompat.Builder(context, "571571")
        notification.setAutoCancel(true)
            .setSmallIcon(R.drawable.unnamed)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentText(intent.extras?.getString("title"))
            .setContentTitle("Remember").priority = NotificationCompat.PRIORITY_HIGH

        notifyManager.notify(1,notification.build())
    }
}