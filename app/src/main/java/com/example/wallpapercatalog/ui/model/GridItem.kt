package com.example.wallpapercatalog.ui.model

import java.io.Serializable

data class GridItem(
    val id: Int?,
    val title: String?,
    val imageUrl: String,
    val viewType: Int,
    var selected: Boolean = false,
) : Serializable

class GridItems: ArrayList<GridItem>(), Serializable

