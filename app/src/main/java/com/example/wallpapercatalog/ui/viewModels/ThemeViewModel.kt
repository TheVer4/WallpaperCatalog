package com.example.wallpapercatalog.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercatalog.domain.data.ThemeContents
import com.example.wallpapercatalog.domain.usecases.getThemeContents.GetThemeContents
import com.example.wallpapercatalog.ui.model.GridItem
import com.example.wallpapercatalog.ui.model.UiState
import com.example.wallpapercatalog.ui.model.toGridItems
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThemeViewModel @Inject constructor(
    private val getThemeContents: GetThemeContents
) : ViewModel() {

    val liveData = MutableLiveData<UiState<List<GridItem>>>(UiState.Loading)

    fun loadValues(themeId: Int) {
        viewModelScope.launch {
            val contents: ThemeContents? = getThemeContents(themeId)
            val result = contents?.toGridItems()
            result?.let {
                liveData.postValue(UiState.Success(result))
            } ?: liveData.postValue(UiState.Error("Unreachable data"))
        }
    }
}