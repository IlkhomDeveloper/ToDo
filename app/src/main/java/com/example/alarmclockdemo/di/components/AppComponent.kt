package com.example.alarmclockdemo.di.components

import android.content.Context
import com.example.alarmclockdemo.app.App
import com.example.alarmclockdemo.di.modules.ActFragModule
import com.example.alarmclockdemo.di.modules.RepositoryBindModule
import com.example.alarmclockdemo.di.modules.RepositoryModule
import com.example.alarmclockdemo.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        RepositoryBindModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActFragModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Builder {
        fun create(@BindsInstance context: Context): AppComponent
    }
}