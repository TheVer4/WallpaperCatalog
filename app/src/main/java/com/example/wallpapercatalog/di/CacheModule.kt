package com.example.wallpapercatalog.di

import android.content.Context
import androidx.room.Room
import com.example.wallpapercatalog.domain.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    @Provides
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "db").build()

    @Provides
    fun provideServiceDao(database: AppDatabase) = database.getServiceDao()

}