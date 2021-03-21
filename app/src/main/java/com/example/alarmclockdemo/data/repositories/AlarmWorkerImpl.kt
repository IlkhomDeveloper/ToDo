package com.example.alarmclockdemo.data.repositories

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.alarmclockdemo.app.App
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.domain.repositories.AlarmWorker
import com.example.alarmclockdemo.presenter.receivers.AlarmClockReceiver
import java.util.*
import javax.inject.Inject

class AlarmWorkerImpl @Inject constructor(private val context:Context) : AlarmWorker {
    private val alarmManager: AlarmManager? = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

    override fun create(alarmVo: AlarmVo) {
        val intent = Intent(context, AlarmClockReceiver::class.java)
        intent.putExtra("title", alarmVo.name)

        val pIntent = PendingIntent.getBroadcast(context, alarmVo.id.toInt(), intent, 0)

        val currentCalendar = GregorianCalendar.getInstance()

        currentCalendar.timeInMillis = alarmVo.date.time
        currentCalendar.set(GregorianCalendar.SECOND, 0)
        currentCalendar.set(GregorianCalendar.MILLISECOND, 0)
        alarmManager?.set(AlarmManager.RTC_WAKEUP, currentCalendar.timeInMillis, pIntent)
    }

    override fun cancel(alarmVo: AlarmVo) {
        val intent = Intent(App.instance, AlarmClockReceiver::class.java)
        intent.putExtra("title", alarmVo.name)
        val pIntent = PendingIntent.getBroadcast(App.instance, 0, intent, 0)
        alarmManager?.cancel(pIntent)
    }

    override fun update(alarmVo: AlarmVo) {
        cancel(alarmVo)
        create(alarmVo)
    }
}