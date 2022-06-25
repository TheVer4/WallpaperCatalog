package com.example.wallpapercatalog.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercatalog.domain.data.WallpaperTheme
import com.example.wallpapercatalog.domain.usecases.getWallpaperThemes.GetWallpaperThemes
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
            Log.d("MVM VMS", "Launched process of requesting data")
            val list: List<WallpaperTheme> = getWallpaperThemes()
            val result = list.map { it.toGridItem() }
            liveData.postValue(UiState.Success(result))
        }
    }


}