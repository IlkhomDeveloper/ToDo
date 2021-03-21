package com.example.alarmclockdemo.domain.usecases

import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.domain.repositories.AlarmRepository
import com.example.alarmclockdemo.domain.repositories.AlarmWorker
import com.example.alarmclockdemo.presenter.utils.Type
import javax.inject.Inject

class AlarmUseCase @Inject constructor(
    private val repository: AlarmRepository,
    private val alarmWorker: AlarmWorker
) {

    fun getAlarms(): List<AlarmVo> = repository.getAlarms()

    fun getDoneAlarms() : List<AlarmVo> = repository.getDoneAlarms()

    fun getInProgressAlarms() : List<AlarmVo> = repository.getInProgressAlarms()

    fun insert(data: AlarmVo): Long {
        alarmWorker.create(data)
        return repository.insert(data)
    }

    fun update(data: AlarmVo): Boolean {
        if (repository.update(data)) {
            return if (data.status == Type.DONE){
                alarmWorker.cancel(data)
                true
            } else{
                alarmWorker.update(data)
                true
            }

        }
        return false
    }

    fun delete(data: AlarmVo) {
        alarmWorker.cancel(data)
        repository.delete(data)
    }
}

