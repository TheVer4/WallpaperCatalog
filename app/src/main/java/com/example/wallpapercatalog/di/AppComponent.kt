package com.example.wallpapercatalog.di

import com.example.wallpapercatalog.MainActivity
import com.example.wallpapercatalog.ui.adapters.WallpaperGridAdapter
import com.example.wallpapercatalog.ui.fragments.ImagePreviewFragment
import com.example.wallpapercatalog.ui.fragments.MainGridFragment
import com.example.wallpapercatalog.ui.fragments.ThemeGridFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: MainGridFragment)
    fun inject(fragment: ThemeGridFragment)
    fun inject(adapter: WallpaperGridAdapter)
    fun inject(fragment: ImagePreviewFragment)
    fun inject(viewHolder: WallpaperGridAdapter.ThemePreviewViewHolder)
    fun inject(viewHolder: WallpaperGridAdapter.WallpaperDetailViewHolder)

}


