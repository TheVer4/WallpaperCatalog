package com.example.wallpapercatalog.ui.model

import com.example.wallpapercatalog.domain.data.ThemeContents
import com.example.wallpapercatalog.domain.data.WallpaperTheme
import com.example.wallpapercatalog.ui.adapters.WallpaperGridAdapter


fun WallpaperTheme.toGridItem(): GridItem
    = GridItem(this.id, this.theme, this.cover, WallpaperGridAdapter.THEME_PREVIEW_VIEW_TYPE)

fun ThemeContents.toGridItems(): List<GridItem>
    = this.wallparers.map { GridItem(null, null, it, WallpaperGridAdapter.WALLPAPER_DETAIL_VIEW_TYPE) }
