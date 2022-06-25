package com.example.wallpapercatalog.ui.viewModels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ActivityViewModel @Inject constructor(

) : ViewModel() {

    val progressBarVisibility = MutableLiveData<Int>()

    fun showProgressBar(isRequired: Boolean) {
        progressBarVisibility.postValue(if(isRequired) View.VISIBLE else View.GONE)
    }

}