package com.example.alarmclockdemo.presenter.utils

import java.text.SimpleDateFormat
import java.util.*

fun fromDateToString(date: Date) : String{
    return SimpleDateFormat(Constants.yyyy_MM_dd, Locale.getDefault()).format(date)
}

fun fromTimeToString(date: Date) : String{
    return SimpleDateFormat(Constants.HH_mm, Locale.getDefault()).format(date)
}

fun fromStringToDate(date:String) : Date?{
    return SimpleDateFormat(Constants.yyyy_MM_dd__HH_mm, Locale.getDefault()).parse(date)
}