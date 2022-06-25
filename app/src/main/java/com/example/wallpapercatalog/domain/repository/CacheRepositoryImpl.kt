package com.example.wallpapercatalog.domain.repository

import com.example.wallpapercatalog.domain.room.ServiceDao
import com.example.wallpapercatalog.domain.data.ThemeContentsDbEntity
import com.example.wallpapercatalog.domain.data.WallpaperThemeDbEntity
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
    private val cacheService: ServiceDao
) : CacheRepository {
    override suspend fun getAvailableThemes(): List<WallpaperThemeDbEntity>? = cacheService.getAllWallpaperThemes()

    override suspend fun getThemeContents(id: Int): ThemeContentsDbEntity? = cacheService.getAllThemeContents(id)

    override suspend fun storeAvailableThemes(wallpaperThemes: List<WallpaperThemeDbEntity>) {
        cacheService.nukeWallpaperThemesCache()
        wallpaperThemes.forEach { theme ->
            cacheService.saveWallpaperTheme(theme)
        }
    }

    override suspend fun storeThemeContents(themeContents: ThemeContentsDbEntity) {
        cacheService.nukeThemeContentsCache(themeContents.db_id.toInt())
        cacheService.saveThemeContents(themeContents)
    }
}