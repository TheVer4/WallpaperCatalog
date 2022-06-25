package com.example.wallpapercatalog.domain.data

import com.google.gson.annotations.SerializedName

data class ThemeContents(
    @SerializedName("theme")
    val theme: String,
    @SerializedName("wallparers")
    val wallparers: List<String>,
)