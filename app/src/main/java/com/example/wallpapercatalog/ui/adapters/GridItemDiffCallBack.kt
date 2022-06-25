package com.example.wallpapercatalog.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.wallpapercatalog.ui.model.GridItem

class GridItemDiffCallBack : DiffUtil.ItemCallback<GridItem>() {
    override fun areItemsTheSame(oldItem: GridItem, newItem: GridItem): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: GridItem, newItem: GridItem): Boolean =
        oldItem == newItem

}