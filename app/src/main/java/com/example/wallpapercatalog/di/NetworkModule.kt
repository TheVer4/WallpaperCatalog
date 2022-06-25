package com.example.wallpapercatalog.di

import com.example.wallpapercatalog.domain.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://62b4a8d8da3017eabb0f2320.mockapi.io/api/v1/")
            .build()
        return retrofit.create()
    }

}