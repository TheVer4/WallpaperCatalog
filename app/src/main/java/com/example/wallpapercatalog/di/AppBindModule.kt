package com.example.wallpapercatalog.di

import com.example.wallpapercatalog.domain.repository.RemoteRepository
import com.example.wallpapercatalog.domain.repository.RemoteRepositoryImpl
import com.example.wallpapercatalog.domain.usecases.getThemeContents.GetThemeContents
import com.example.wallpapercatalog.domain.usecases.getThemeContents.GetThemeContentsImpl
import com.example.wallpapercatalog.domain.usecases.getWallpaperThemes.GetWallpaperThemes
import com.example.wallpapercatalog.domain.usecases.getWallpaperThemes.GetWallpaperThemesImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindRemoteRepository(repository: RemoteRepositoryImpl): RemoteRepository

    @Binds
    fun bindGetThemeContentsUseCase(getThemeContents: GetThemeContentsImpl): GetThemeContents

    @Binds
    fun bindGetWallpaperThemesUseCase(getWallpaperThemes: GetWallpaperThemesImpl): GetWallpaperThemes

}