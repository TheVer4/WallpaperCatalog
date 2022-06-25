package com.example.wallpapercatalog.domain.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallpaperTheme")
data class WallpaperThemeDbEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val theme: String,
    val cover: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
) {

    fun toWallpaperTheme(): WallpaperTheme = WallpaperTheme(
        id = id,
        theme = theme,
        cover = cover,
    )

    companion object {

        fun fromWallpaperTheme(wallpaperTheme: WallpaperTheme) = WallpaperThemeDbEntity(
            id = wallpaperTheme.id,
            theme = wallpaperTheme.theme,
            cover = wallpaperTheme.cover,
            createdAt = System.currentTimeMillis(),
        )

    }

}