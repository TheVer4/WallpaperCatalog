package com.example.wallpapercatalog.domain.usecases.getThemeContents

import com.example.wallpapercatalog.domain.data.ThemeContents
import com.example.wallpapercatalog.domain.repository.RemoteRepository
import javax.inject.Inject

class GetThemeContentsImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
) : GetThemeContents {
    override suspend fun invoke(id: Int): ThemeContents = remoteRepository.getThemeContents(id)
}