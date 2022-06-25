package com.example.wallpapercatalog.domain.usecases.getWallpaperThemes

import com.example.wallpapercatalog.domain.data.WallpaperTheme
import com.example.wallpapercatalog.domain.data.WallpaperThemeDbEntity
import com.example.wallpapercatalog.domain.repository.CacheRepository
import com.example.wallpapercatalog.domain.repository.RemoteRepository
import javax.inject.Inject

class GetWallpaperThemesImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val cacheRepository: CacheRepository,
) : GetWallpaperThemes {
    override suspend fun invoke(): List<WallpaperTheme> {
        var response: List<WallpaperTheme>?
        try {
            response = remoteRepository.getAvailableThemes()
            cacheRepository.storeAvailableThemes(response.map {
                WallpaperThemeDbEntity.fromWallpaperTheme(it)
            })
        }
        catch (exception: Exception) {
            response = cacheRepository.getAvailableThemes()?.map(WallpaperThemeDbEntity::toWallpaperTheme)
        }
        return response ?: emptyList()
    }

}