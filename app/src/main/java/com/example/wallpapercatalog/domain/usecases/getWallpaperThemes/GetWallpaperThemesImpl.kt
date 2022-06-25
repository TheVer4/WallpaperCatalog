package com.example.wallpapercatalog.domain.usecases.getWallpaperThemes

import com.example.wallpapercatalog.domain.data.WallpaperTheme
import com.example.wallpapercatalog.domain.repository.RemoteRepository
import javax.inject.Inject

class GetWallpaperThemesImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
) : GetWallpaperThemes {
    override suspend fun invoke(): List<WallpaperTheme> = remoteRepository.getAvailableThemes()
}