package com.example.alarmclockdemo.presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alarmclockdemo.data.vo.AlarmVo
import com.example.alarmclockdemo.presenter.utils.Constants
import com.example.alarmclockdemo.presenter.utils.Messages
import java.text.SimpleDateFormat
import java.util.*

class ViewModelAddDialog : ViewModel() {
    var listener : ((AlarmVo) -> Unit) ?= null
    private var _liveDateError = MutableLiveData<String>()
    var liveDataError: LiveData<String> = _liveDateError

    fun checkDate(title: String, date: String,time:String): Boolean {
        when {
            title.isEmpty() -> {
                _liveDateError.value = Messages.TITLE
                return false
            }
            date.length != 10 -> {
                _liveDateError.value = Messages.DATE
                return false
            }
            time.length != 5 -> {
                _liveDateError.value = Messages.TIME
                return false
            }
            SimpleDateFormat(Constants.yyyy_MM_dd__HH_mm).parse("$date $time") <= Date() -> {
                _liveDateError.value = Messages.LESS_TIME
                return false
            }
        }
        return true
    }
}