package com.example.wallpapercatalog.domain.repository

import android.util.Log
import com.example.wallpapercatalog.domain.api.ApiService
import com.example.wallpapercatalog.domain.data.ThemeContents
import com.example.wallpapercatalog.domain.data.WallpaperTheme
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteRepository {
    override suspend fun getAvailableThemes(): List<WallpaperTheme> {
        val res =  apiService.getAvailableThemes()
        Log.d("REPO", "Received data ${res}")
        return res
    }

    override suspend fun getThemeContents(id: Int): ThemeContents = apiService.getThemeContents(id)
}