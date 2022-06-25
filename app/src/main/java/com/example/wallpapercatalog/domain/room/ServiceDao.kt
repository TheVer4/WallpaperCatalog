package com.example.wallpapercatalog.domain.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wallpapercatalog.domain.data.ThemeContentsDbEntity
import com.example.wallpapercatalog.domain.data.WallpaperThemeDbEntity

@Dao
interface ServiceDao {

    @Query("SELECT * FROM themeContents WHERE db_id=:id")
    suspend fun getAllThemeContents(id: Int): ThemeContentsDbEntity?

    @Query("DELETE FROM themeContents WHERE db_id=:id")
    suspend fun nukeThemeContentsCache(id: Int)

    @Insert
    suspend fun saveThemeContents(themeContents: ThemeContentsDbEntity)


    @Query("SELECT * FROM wallpaperTheme WHERE 1=1")
    suspend fun getAllWallpaperThemes(): List<WallpaperThemeDbEntity>?

    @Query("DELETE FROM wallpaperTheme WHERE 1=1")
    suspend fun nukeWallpaperThemesCache()

    @Insert
    suspend fun saveWallpaperTheme(wallpaperTheme: WallpaperThemeDbEntity)

}