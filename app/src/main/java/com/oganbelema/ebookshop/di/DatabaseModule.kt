package com.oganbelema.ebookshop.di

import android.app.Application
import com.oganbelema.ebookshop.database.BookDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Belema Ogan on 2019-08-08.
 */
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideBookDatabase(application: Application): BookDatabase? {
        return BookDatabase.getInstance(application)
    }
}