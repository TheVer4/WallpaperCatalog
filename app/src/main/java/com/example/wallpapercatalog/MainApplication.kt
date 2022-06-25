package com.example.wallpapercatalog

import android.app.Application
import com.example.wallpapercatalog.di.AppComponent
import com.example.wallpapercatalog.di.ContextModule
import com.example.wallpapercatalog.di.DaggerAppComponent

class MainApplication: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }

}