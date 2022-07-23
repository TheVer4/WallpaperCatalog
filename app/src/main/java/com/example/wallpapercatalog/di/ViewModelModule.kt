package com.example.wallpapercatalog.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallpapercatalog.ui.viewModels.MainViewModel
import com.example.wallpapercatalog.ui.viewModels.ThemeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ThemeViewModel::class)
    abstract fun themeViewModel(viewModel: ThemeViewModel): ViewModel
}
