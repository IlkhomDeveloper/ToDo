package com.example.alarmclockdemo.di.modules

import com.example.alarmclockdemo.presenter.activities.MainActivity
import com.example.alarmclockdemo.presenter.fragments.AllPageFragment
import com.example.alarmclockdemo.presenter.fragments.DonePageFragment
import com.example.alarmclockdemo.presenter.fragments.InProgressFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActFragModule {

    @ContributesAndroidInjector
    fun mainActivity() : MainActivity

    @ContributesAndroidInjector
    fun mainPageFragment() : InProgressFragment

    @ContributesAndroidInjector
    fun donePageFragment() : DonePageFragment

    @ContributesAndroidInjector
    fun allPageFragment() : AllPageFragment
}