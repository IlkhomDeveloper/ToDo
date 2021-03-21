package com.example.alarmclockdemo.data.repositories

import com.example.alarmclockdemo.data.mappers.Mapper
import com.example.alarmclockdemo.data.room.daos.AlarmDao
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.domain.repositories.AlarmRepository
import com.example.alarmclockdemo.presenter.utils.Constants
import com.example.alarmclockdemo.presenter.utils.Type
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(private val alarmDao: AlarmDao) : AlarmRepository {

    override fun insert(data: AlarmVo) : Long {
        return if (alarmDao.isHasDate(SimpleDateFormat(Constants.yyyy_MM_dd__HH_mm, Locale.getDefault()).format(data.date))){
            0
        } else{
            alarmDao.insert(Mapper.fromVoToEntity(data))
        }
    }

    override fun update(data: AlarmVo) : Boolean {
        return if (data.status == Type.DONE){
            alarmDao.update(Mapper.fromVoToEntity(data))
            true
        } else{
            if (alarmDao.isHasDate(SimpleDateFormat(Constants.yyyy_MM_dd__HH_mm, Locale.getDefault()).format(data.date))){
                false
            } else{
                alarmDao.update(Mapper.fromVoToEntity(data))
                true
            }
        }
    }

    override fun delete(data: AlarmVo)  {
        alarmDao.delete(Mapper.fromVoToEntity(data))
    }

    override fun getAlarms(): List<AlarmVo> {
        return alarmDao.getAlarms().map { Mapper.fromEntityToVo(it) }
    }

    override fun getDoneAlarms(): List<AlarmVo> {
        return alarmDao.getDoneAlarms().map { Mapper.fromEntityToVo(it) }
    }

    override fun getInProgressAlarms(): List<AlarmVo> {
        return alarmDao.getInProgressAlarms().map { Mapper.fromEntityToVo(it) }
    }
}