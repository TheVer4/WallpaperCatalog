package com.example.wallpapercatalog.domain.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "themeContents")
data class ThemeContentsDbEntity(
    @PrimaryKey(autoGenerate = false) val db_id: Long,
    val theme: String,
    val wallparers: List<String>,
    @ColumnInfo(name = "created_at") val createdAt: Long,
) {

    fun toThemeContents(): ThemeContents = ThemeContents(
        id = db_id.toInt(),
        theme = theme,
        wallparers = wallparers,
    )

    companion object {
        fun fromThemeContents(themeContents: ThemeContents) = ThemeContentsDbEntity(
            db_id = themeContents.id.toLong(),
            theme = themeContents.theme,
            wallparers = themeContents.wallparers,
            createdAt = System.currentTimeMillis(),
        )
    }
}

