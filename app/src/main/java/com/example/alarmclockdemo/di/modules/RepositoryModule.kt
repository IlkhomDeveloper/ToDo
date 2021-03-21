package com.example.alarmclockdemo.di.modules

import android.content.Context
import com.example.alarmclockdemo.data.repositories.AlarmRepositoryImpl
import com.example.alarmclockdemo.data.repositories.AlarmWorkerImpl
import com.example.alarmclockdemo.data.room.daos.AlarmDao
import com.example.alarmclockdemo.data.room.databases.AlarmDatabase
import com.example.alarmclockdemo.presenter.adapters.AllAlarmsAdapter
import com.example.alarmclockdemo.presenter.adapters.InProgressAdapter
import com.example.alarmclockdemo.presenter.adapters.DoneAlarmsAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun getDatabase(context: Context) : AlarmDatabase = AlarmDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun getAlarmDao(alarmDatabase: AlarmDatabase) : AlarmDao = alarmDatabase.getAlarmDao()

    @Provides
    @Singleton
    fun getAlarmRepository(alarmDao: AlarmDao) = AlarmRepositoryImpl(alarmDao)

    @Provides
    @Singleton
    fun getAlarmWorker(context: Context) = AlarmWorkerImpl(context)

    @Provides
    @Singleton
    fun getInProgressAlarm() = InProgressAdapter()

    @Provides
    @Singleton
    fun getDoneAdapter() = DoneAlarmsAdapter()

    @Provides
    @Singleton
    fun getAllAlarmsAdapter() = AllAlarmsAdapter()
}