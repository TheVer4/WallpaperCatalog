package com.example.wallpapercatalog.domain.usecases.getThemeContents

import com.example.wallpapercatalog.domain.data.ThemeContents
import com.example.wallpapercatalog.domain.data.ThemeContentsDbEntity
import com.example.wallpapercatalog.domain.repository.CacheRepository
import com.example.wallpapercatalog.domain.repository.RemoteRepository
import javax.inject.Inject

class GetThemeContentsImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val cacheRepository: CacheRepository,
) : GetThemeContents {
    override suspend fun invoke(id: Int): ThemeContents? {
        var response: ThemeContents?
        try {
            response = remoteRepository.getThemeContents(id)
            cacheRepository.storeThemeContents(ThemeContentsDbEntity.fromThemeContents(response))
        } catch (exception: Exception) {
            response = cacheRepository.getThemeContents(1)?.toThemeContents() //TODO Hardcoded parameter cause mockapi returns only this value
        }
        return response
    }
}