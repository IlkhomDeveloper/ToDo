package com.example.alarmclockdemo.domain.repositories

import com.example.alarmclockdemo.data.vo.AlarmVo

interface AlarmWorker {
    fun create(alarmVo: AlarmVo)
    fun cancel(alarmVo: AlarmVo)
    fun update(alarmVo: AlarmVo)
}