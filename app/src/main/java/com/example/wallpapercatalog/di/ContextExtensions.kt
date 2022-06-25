package com.example.wallpapercatalog.di

import android.content.Context
import com.example.wallpapercatalog.MainApplication

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApplication -> appComponent
        else -> applicationContext.appComponent
    }
