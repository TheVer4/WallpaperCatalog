package com.example.wallpapercatalog.domain.usecases.getWallpaperThemes

import com.example.wallpapercatalog.domain.data.WallpaperTheme

interface GetWallpaperThemes {
    suspend operator fun invoke(): List<WallpaperTheme>
}