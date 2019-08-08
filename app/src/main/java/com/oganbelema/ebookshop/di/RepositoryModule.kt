package com.oganbelema.ebookshop.di

import com.oganbelema.ebookshop.book.BookRepository
import com.oganbelema.ebookshop.category.CategoryRepository
import com.oganbelema.ebookshop.database.BookDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Belema Ogan on 2019-08-08.
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(bookDatabase: BookDatabase?): BookRepository {
        return BookRepository(bookDatabase)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(bookDatabase: BookDatabase?): CategoryRepository {
        return CategoryRepository(bookDatabase)
    }
}