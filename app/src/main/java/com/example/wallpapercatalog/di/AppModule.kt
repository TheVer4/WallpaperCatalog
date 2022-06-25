package com.example.wallpapercatalog.di

import dagger.Module

@Module(includes = [NetworkModule::class, AppBindModule::class, ViewModelModule::class])
class AppModule