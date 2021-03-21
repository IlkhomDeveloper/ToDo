package com.example.alarmclockdemo.data.mappers

import com.example.alarmclockdemo.data.room.entities.AlarmEntity
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.presenter.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

object Mapper {

    fun fromVoToEntity(alarmVo: AlarmVo) = AlarmEntity(
        alarmVo.id,
        alarmVo.name,
        SimpleDateFormat(Constants.yyyy_MM_dd__HH_mm, Locale.getDefault()).format(alarmVo.date).toString(),
        alarmVo.status
    )

    fun fromEntityToVo(entity: AlarmEntity) = AlarmVo(
        entity.id,
        entity.title,
        SimpleDateFormat(Constants.yyyy_MM_dd__HH_mm).parse(entity.date),
        entity.status
    )
}