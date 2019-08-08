package com.oganbelema.ebookshop.di

import androidx.lifecycle.ViewModel
import com.oganbelema.ebookshop.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Belema Ogan on 2019-08-08.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun providesMainViewModel(mainViewModel: MainActivityViewModel): ViewModel
}