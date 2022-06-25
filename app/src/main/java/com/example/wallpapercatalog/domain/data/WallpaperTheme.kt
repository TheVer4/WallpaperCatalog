package com.example.wallpapercatalog.domain.data

import com.google.gson.annotations.SerializedName

data class WallpaperTheme(
    @SerializedName("id")
    val id: Int,
    @SerializedName("theme")
    val theme: String,
    @SerializedName("cover")
    val cover: String,
)