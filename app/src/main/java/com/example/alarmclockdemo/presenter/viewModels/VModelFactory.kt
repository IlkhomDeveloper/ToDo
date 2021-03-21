package com.example.alarmclockdemo.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class VModelFactory @Inject constructor(private val viewModelMap: Map<Class<out ViewModel>, ViewModel>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelMap[modelClass] as T
    }
}