package com.example.alarmclockdemo.data.room.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.alarmclockdemo.data.room.daos.AlarmDao
import com.example.alarmclockdemo.data.room.entities.AlarmEntity

@Database(entities = [AlarmEntity::class],version = 1,exportSchema = false)

abstract class AlarmDatabase : androidx.room.RoomDatabase(){
    abstract fun getAlarmDao() : AlarmDao
    companion object{
        @Volatile
        var INSTANCE: AlarmDatabase ?= null
        fun getDatabase(context: Context) : AlarmDatabase{
            var temInstance = INSTANCE
            if (temInstance != null){
                return temInstance
            }
            synchronized(this){
                var instance = Room.databaseBuilder(context,AlarmDatabase::class.java,"database").allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}