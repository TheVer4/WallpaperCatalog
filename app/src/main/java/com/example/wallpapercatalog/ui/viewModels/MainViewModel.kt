package com.example.wallpapercatalog.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercatalog.domain.data.WallpaperTheme
import com.example.wallpapercatalog.domain.usecases.getWallpaperThemes.GetWallpaperThemes
import com.example.wallpapercatalog.ui.adapters.WallpaperGridAdapter
import com.example.wallpapercatalog.ui.model.GridItem
import com.example.wallpapercatalog.ui.model.UiState
import com.example.wallpapercatalog.ui.model.toGridItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getWallpaperThemes: GetWallpaperThemes
) : ViewModel() {

    val liveData = MutableLiveData<UiState<List<GridItem>>>(UiState.Loading)

    init {
        viewModelScope.launch {
            val list: List<WallpaperTheme> = getWallpaperThemes()
            val result = mutableListOf<GridItem>(/*TODO here will be passed local storage theme*/)
            result.addAll(list.map { it.toGridItem() })
            liveData.postValue(UiState.Success(result))
        }
    }


}