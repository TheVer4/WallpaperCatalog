package com.example.wallpapercatalog.domain.repository

import com.example.wallpapercatalog.domain.data.ThemeContents
import com.example.wallpapercatalog.domain.data.WallpaperTheme

interface RemoteRepository {
    suspend fun getAvailableThemes(): List<WallpaperTheme>

    suspend fun getThemeContents(id: Int): ThemeContents
}