package com.oganbelema.ebookshop

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.oganbelema.ebookshop.book.Book
import com.oganbelema.ebookshop.book.BookRepository
import com.oganbelema.ebookshop.category.Category
import com.oganbelema.ebookshop.category.CategoryRepository
import com.oganbelema.ebookshop.database.BookDatabase


class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val bookDatabase = BookDatabase.getInstance(application)

    private val categoryRepository = CategoryRepository(bookDatabase)

    private val bookRepository = BookRepository(bookDatabase)



    //region Categories
    fun getAllCategories(): LiveData<List<Category>>? {
        return categoryRepository.getAllCategories()
    }

    fun addNewCategory(category: Category){
        categoryRepository.insertCategory(category)
    }

    fun updateCategory(category: Category){
        categoryRepository.updateCategory(category)
    }

    fun deleteCategory(category: Category){
        categoryRepository.deleteCategory(category)
    }
    //endregion

    //region Books
    fun getAllBooks(): LiveData<List<Book>>? {
        return bookRepository.getAllBooks()
    }

    fun getBooksInCategory(categoryId: Int): LiveData<List<Book>>? {
        return bookRepository.getBooksInCategory(categoryId)
    }

    fun addNewBook(book: Book){
        bookRepository.insertBook(book)
    }

    fun updateBook(book: Book){
        bookRepository.updateBook(book)
    }

    fun deleteBook(book: Book){
        bookRepository.deleteBook(book)
    }
    //endregion


}