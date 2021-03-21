package com.example.alarmclockdemo.di.modules

import com.example.alarmclockdemo.data.repositories.AlarmRepositoryImpl
import com.example.alarmclockdemo.data.repositories.AlarmWorkerImpl
import com.example.alarmclockdemo.domain.repositories.AlarmRepository
import com.example.alarmclockdemo.domain.repositories.AlarmWorker
import dagger.Binds
import dagger.Module

@Module
interface RepositoryBindModule {
    @Binds
    fun getAlarmRepositoryBind(alarmRepositoryImpl: AlarmRepositoryImpl) : AlarmRepository

    @Binds
    fun getAlarmWorkerBind(alarmWorkerImpl: AlarmWorkerImpl) : AlarmWorker
}