package com.example.alarmclockdemo.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlarmEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var title:String,
    var date:String,
    var status:Int = 1
)