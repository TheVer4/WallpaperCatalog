package com.example.wallpapercatalog.domain.api

import com.example.wallpapercatalog.domain.data.ThemeContents
import com.example.wallpapercatalog.domain.data.WallpaperTheme
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("theme")
    suspend fun getAvailableThemes(): List<WallpaperTheme>

    @GET("theme/{id}")
    suspend fun getThemeContents(@Query("id") id: Int): ThemeContents

}