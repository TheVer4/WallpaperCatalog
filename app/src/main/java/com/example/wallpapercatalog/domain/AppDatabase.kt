package com.example.wallpapercatalog.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wallpapercatalog.domain.data.ThemeContentsDbEntity
import com.example.wallpapercatalog.domain.data.WallpaperThemeDbEntity
import com.example.wallpapercatalog.domain.room.ServiceDao

@Database(
    version = 1,
    entities = [
        ThemeContentsDbEntity::class,
        WallpaperThemeDbEntity::class,
    ]
)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getServiceDao(): ServiceDao

}