package com.example.alarmclockdemo.data.room.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.alarmclockdemo.data.room.entities.AlarmEntity

@Dao
interface AlarmDao : BaseDao<AlarmEntity>{
    @Query("SELECT * FROM AlarmEntity")
    fun getAlarms() : List<AlarmEntity>

    @Query("SELECT * FROM AlarmEntity WHERE date = :date")
    fun isHasDate(date:String) : Boolean

    @Query("SELECT * FROM AlarmEntity WHERE status = 2")
    fun getDoneAlarms() : List<AlarmEntity>

    @Query("SELECT * FROM AlarmEntity WHERE status = 1")
    fun getInProgressAlarms() : List<AlarmEntity>
}