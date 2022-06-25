package com.example.wallpapercatalog.ui.adapters

import android.view.View

fun interface ItemClickListener {
    fun onItemClick(view: View?, holder: WallpaperGridAdapter.ViewHolder)
}