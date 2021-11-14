package com.littleBig.littlebignotepad.dependencyinjection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.littleBig.littlebignotepad.ViewModels.MainPageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainPageViewModel::class)
    internal abstract fun bindMainPageViewModel(mainPageViewModel: MainPageViewModel)
            : ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory)
              : ViewModelProvider.Factory
}