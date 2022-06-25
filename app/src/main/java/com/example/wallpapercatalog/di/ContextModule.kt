package com.example.wallpapercatalog.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private var context: Context) {

    @Provides
    fun provideContext() = context

}