package com.example.alarmclockdemo.domain.repositories

import com.example.alarmclockdemo.data.vo.AlarmVo

interface AlarmRepository {
    fun insert(data : AlarmVo) : Long
    fun update(data : AlarmVo) : Boolean
    fun delete(data : AlarmVo)
    fun getAlarms() : List<AlarmVo>
    fun getDoneAlarms() : List<AlarmVo>
    fun getInProgressAlarms() : List<AlarmVo>
}