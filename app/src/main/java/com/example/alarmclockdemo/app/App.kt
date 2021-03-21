package com.example.alarmclockdemo.app

import com.example.alarmclockdemo.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    override fun applicationInjector(): AndroidInjector<App> {
        return DaggerAppComponent.factory().create(this)
    }

    companion object{
        lateinit var instance: App
    }
}