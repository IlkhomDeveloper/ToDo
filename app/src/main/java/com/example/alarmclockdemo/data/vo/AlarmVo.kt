package com.example.alarmclockdemo.data.vo

import java.io.Serializable
import java.util.*

data class AlarmVo(var id: Long = 0, var name: String, var date: Date, var status:Int) : Serializable