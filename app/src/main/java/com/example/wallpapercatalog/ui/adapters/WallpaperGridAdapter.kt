package com.example.wallpapercatalog.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpapercatalog.R
import com.example.wallpapercatalog.databinding.GridItemBinding
import com.example.wallpapercatalog.di.appComponent
import com.example.wallpapercatalog.ui.model.GridItem
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import javax.inject.Inject

class WallpaperGridAdapter() :
    ListAdapter<GridItem, WallpaperGridAdapter.ViewHolder>(GridItemDiffCallBack()) {

    var itemClickListener: ItemClickListener? = null

    abstract class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        var item: GridItem? = null
            protected set

        fun bind(item: GridItem) {
            this.item = item
            onBindItem(item)
        }

        abstract fun onBindItem(item: GridItem)
    }

    class ThemePreviewViewHolder(
        private val binding: GridItemBinding,
        context: Context,
    ) : ViewHolder(binding.root) {

        @Inject
        lateinit var picasso: Picasso

        init {
            context.appComponent.inject(this)
        }

        override fun onBindItem(item: GridItem) {
            loadImage(item.imageUrl)
            binding.text.text = item.title
        }

        private fun loadImage(url: String) {
            picasso
                .load(url)
                .into(binding.image)
        }
    }

    class WallpaperDetailViewHolder(
        private val view: ImageView,
        context: Context,
    ) : ViewHolder(view) {

        @Inject
        lateinit var picasso: Picasso

        init {
            context.appComponent.inject(this)
            with(view) {
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    resources.getDimensionPixelSize(
                        R.dimen.wallpaper_detail_height))
                scaleType = ImageView.ScaleType.CENTER_CROP
                val margin = resources.getDimensionPixelSize(R.dimen.detail_view_margin)
                layoutParams = ViewGroup.MarginLayoutParams(layoutParams)
                (layoutParams as ViewGroup.MarginLayoutParams).setMargins(margin, margin, margin, margin)
            }
        }

        override fun onBindItem(item: GridItem) {
            loadImage(item.imageUrl)
        }

        private fun loadImage(url: String) {
            picasso
                .load(url)
                .into(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            THEME_PREVIEW_VIEW_TYPE -> {
                val binding =
                    GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ThemePreviewViewHolder(binding, parent.context)
            }
            WALLPAPER_DETAIL_VIEW_TYPE -> {
                val view = ImageView(parent.context)
                WallpaperDetailViewHolder(view, parent.context)
            }
            else -> throw Exception("Unexpected viewType at ${this::class.simpleName}")
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gridItem: GridItem = currentList[position]
        holder.bind(gridItem)
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(it, holder)
        }
    }

    override fun getItemViewType(position: Int): Int =
        currentList[position].viewType

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.itemClickListener = listener
    }

    companion object {
        const val THEME_PREVIEW_VIEW_TYPE = 1
        const val WALLPAPER_DETAIL_VIEW_TYPE = 2
    }

}