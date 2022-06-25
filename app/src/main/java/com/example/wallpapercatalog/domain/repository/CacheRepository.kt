package com.example.wallpapercatalog.domain.repository

import com.example.wallpapercatalog.domain.data.ThemeContentsDbEntity
import com.example.wallpapercatalog.domain.data.WallpaperThemeDbEntity

interface CacheRepository {

    suspend fun getAvailableThemes(): List<WallpaperThemeDbEntity>?

    suspend fun getThemeContents(id: Int): ThemeContentsDbEntity?

    suspend fun storeAvailableThemes(wallpaperThemes: List<WallpaperThemeDbEntity>)

    suspend fun storeThemeContents(themeContents: ThemeContentsDbEntity)

}