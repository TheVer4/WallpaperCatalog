package com.example.wallpapercatalog.domain.usecases.getThemeContents

import com.example.wallpapercatalog.domain.data.ThemeContents

interface GetThemeContents {
    suspend operator fun invoke(id: Int): ThemeContents?
}