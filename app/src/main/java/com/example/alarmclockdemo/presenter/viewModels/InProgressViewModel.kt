package com.example.alarmclockdemo.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.domain.usecases.AlarmUseCase
import javax.inject.Inject


class InProgressViewModel @Inject constructor(private val useCase: AlarmUseCase) : ViewModel() {
    private var _liveDataCheck = MutableLiveData<String>()
    private var _liveDataAlarms = MutableLiveData<List<AlarmVo>>()
    private var _liveDataInsert = MutableLiveData<AlarmVo>()
    private var _liveDataUpdate = MutableLiveData<AlarmVo>()
    private var _liveDataDelete = MutableLiveData<AlarmVo>()

    var insertLiveDate: LiveData<AlarmVo> = _liveDataInsert
    var liveDataUpdate: LiveData<AlarmVo> = _liveDataUpdate
    var liveDataDelete: LiveData<AlarmVo> = _liveDataDelete
    var liveDataCheck : LiveData<String> = _liveDataCheck

    fun getAlarms(): LiveData<List<AlarmVo>> {
        _liveDataAlarms.value = useCase.getInProgressAlarms()
        return _liveDataAlarms
    }

    fun insertAlarm(alarmVo: AlarmVo) {
        val id = useCase.insert(alarmVo)
        if (id > 0){
            alarmVo.id = id
            _liveDataInsert.value = alarmVo
        }
        else{
            _liveDataCheck.value = "The alarm is exist"
        }
    }

    fun updateAlarm(alarmVo: AlarmVo) {
        if (useCase.update(alarmVo)){
            _liveDataUpdate.value = alarmVo
        }
        else{
            _liveDataCheck.value = "The alarm is exist"
        }
    }

    fun deleteAlarm(alarmVo: AlarmVo) {
        useCase.delete(alarmVo)
        _liveDataDelete.value = alarmVo
    }
}