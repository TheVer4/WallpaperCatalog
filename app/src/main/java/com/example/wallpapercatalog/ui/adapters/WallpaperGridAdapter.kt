package com.example.wallpapercatalog.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpapercatalog.databinding.GridItemBinding
import com.example.wallpapercatalog.databinding.SelectableImageViewBinding
import com.example.wallpapercatalog.di.appComponent
import com.example.wallpapercatalog.ui.model.GridItem
import com.squareup.picasso.Picasso
import javax.inject.Inject

class WallpaperGridAdapter :
    ListAdapter<GridItem, WallpaperGridAdapter.ViewHolder>(GridItemDiffCallBack()) {

    private var itemClickListener: ItemClickListener? = null
    private var itemLongClickListener: ItemClickListener? = null
    private var isMultiSelectModeEnabled: Boolean = false
    private val viewHolders: MutableList<ViewHolder> = mutableListOf()
    private var onMultiSelectionModeChangedListener: OnMultiSelectionModeChangedListener? = null

    abstract class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        var item: GridItem? = null
            private set

        fun bind(item: GridItem) {
            this.item = item
            onBindItem(item)
        }

        abstract fun onBindItem(item: GridItem)
    }

    class ThemePreviewViewHolder(
        val binding: GridItemBinding,
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
        val binding: SelectableImageViewBinding,
        context: Context,
    ) : ViewHolder(binding.root) {

        @Inject
        lateinit var picasso: Picasso

        init {
            context.appComponent.inject(this)
        }

        override fun onBindItem(item: GridItem) {
            loadImage(item.imageUrl)
            binding.checkbox.isChecked = item.selected
        }

        private fun loadImage(url: String) {
            picasso
                .load(url)
                .into(binding.detailImage)
        }

        fun showCheckbox(show: Boolean = true) {
            val viewVisibilityState = if(show) View.VISIBLE else View.GONE
            binding.checkbox.visibility = viewVisibilityState
        }

        fun switchItemSelection() {
            item?.let { it.selected = it.selected.not() }
            binding.checkbox.isChecked = item?.selected ?: false
        }

        fun getItemSelectionState(): Boolean =
            item?.selected ?: false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder: ViewHolder = when (viewType) {
            THEME_PREVIEW_VIEW_TYPE -> {
                val binding =
                    GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ThemePreviewViewHolder(binding, parent.context)
            }
            WALLPAPER_DETAIL_VIEW_TYPE -> {
                val binding =
                    SelectableImageViewBinding.inflate(LayoutInflater.from(parent.context),
                        parent,
                        false)
                WallpaperDetailViewHolder(binding, parent.context).also {
                    it.showCheckbox(isMultiSelectModeEnabled)
                }
            }
            else -> throw Exception("Unexpected viewType at ${this::class.simpleName}")
        }
        viewHolders.add(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gridItem: GridItem = currentList[position]
        holder.bind(gridItem)
        holder.itemView.setOnClickListener {
            if (isMultiSelectModeEnabled.not()) {
                itemClickListener?.onItemClick(it, holder)
            } else {
                when(getItemViewType(position)) {
                    WALLPAPER_DETAIL_VIEW_TYPE -> {
                        (holder as WallpaperDetailViewHolder).switchItemSelection()
                        if(isAnyTileSelected().not()) {
                            switchMultiSelectMode()
                        }
                    }
                }
            }
        }
        holder.itemView.setOnLongClickListener {
            when(getItemViewType(position)) {
                WALLPAPER_DETAIL_VIEW_TYPE -> {
                    switchMultiSelectMode()
                    (holder as WallpaperDetailViewHolder).switchItemSelection()
                }
            }
            itemLongClickListener?.onItemClick(it, holder)
            true
        }

    }

    private fun isAnyTileSelected(): Boolean =
        currentList.any { it.selected }

    private fun switchMultiSelectMode() {
        isMultiSelectModeEnabled = isMultiSelectModeEnabled.not()
        onMultiSelectionModeChangedListener?.onMultiSelectionModeChanged(isMultiSelectModeEnabled)
        viewHolders.forEach { viewHolder ->
            when(viewHolder.itemViewType) {
                WALLPAPER_DETAIL_VIEW_TYPE -> {
                    (viewHolder as WallpaperDetailViewHolder).showCheckbox(isMultiSelectModeEnabled)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        currentList[position].viewType

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.itemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: ItemClickListener) {
        this.itemLongClickListener = listener
    }

    fun setOnMultiSelectionModeChangedListener(listener: OnMultiSelectionModeChangedListener) {
        onMultiSelectionModeChangedListener = listener
    }

    companion object {
        const val THEME_PREVIEW_VIEW_TYPE = 1
        const val WALLPAPER_DETAIL_VIEW_TYPE = 2
    }

    fun interface ItemClickListener {
        fun onItemClick(view: View?, holder: ViewHolder)
    }

    fun interface OnMultiSelectionModeChangedListener {
        fun onMultiSelectionModeChanged(state: Boolean)
    }

}