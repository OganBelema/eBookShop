package com.oganbelema.ebookshop.di

import com.oganbelema.ebookshop.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Belema Ogan on 2019-08-08.
 */

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(target: MainActivity)

}