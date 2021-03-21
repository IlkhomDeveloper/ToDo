package com.example.alarmclockdemo.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.domain.usecases.AlarmUseCase
import javax.inject.Inject

class DoneViewModel @Inject constructor(private val useCase: AlarmUseCase) : ViewModel() {
    private var _liveData = MutableLiveData<List<AlarmVo>>()

    fun getDoneAlarms() : LiveData<List<AlarmVo>> {
        _liveData.value = useCase.getDoneAlarms()
        return _liveData
    }
}