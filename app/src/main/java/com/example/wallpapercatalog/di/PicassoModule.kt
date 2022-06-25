package com.example.wallpapercatalog.di

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class PicassoModule {

    @Provides
    fun providePicassoInstance(context: Context): Picasso = with(Picasso.Builder(context)) {
        //TODO Images with picasso or glide cannot be cached permanently
        build()
    }

}