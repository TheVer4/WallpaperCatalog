package com.example.wallpapercatalog.di

import android.content.Context
import android.widget.Toast
import com.example.wallpapercatalog.MainApplication

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApplication -> appComponent
        else -> applicationContext.appComponent
    }

fun Context.showLongToast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()

fun Context.showShortToast(text: String) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

