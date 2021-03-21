package com.example.alarmclockdemo.di.modules

import androidx.lifecycle.ViewModel
import com.example.alarmclockdemo.domain.usecases.AlarmUseCase
import com.example.alarmclockdemo.presenter.utils.ViewModelKey
import com.example.alarmclockdemo.presenter.viewModels.AllAlarmViewModel
import com.example.alarmclockdemo.presenter.viewModels.DoneViewModel
import com.example.alarmclockdemo.presenter.viewModels.InProgressViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(InProgressViewModel::class)
    fun getAlarmViewModel(useCase: AlarmUseCase) : ViewModel = InProgressViewModel(useCase)

    @Provides
    @IntoMap
    @ViewModelKey(DoneViewModel::class)
    fun getDoneViewModel(useCase: AlarmUseCase) : ViewModel = DoneViewModel(useCase)

    @Provides
    @IntoMap
    @ViewModelKey(AllAlarmViewModel::class)
    fun getAllAlarmViewModel(useCase: AlarmUseCase) : ViewModel = AllAlarmViewModel(useCase)
}