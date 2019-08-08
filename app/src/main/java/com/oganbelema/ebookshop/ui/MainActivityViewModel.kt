package com.oganbelema.ebookshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oganbelema.ebookshop.book.Book
import com.oganbelema.ebookshop.book.BookRepository
import com.oganbelema.ebookshop.category.Category
import com.oganbelema.ebookshop.category.CategoryRepository
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(private val bookRepository: BookRepository,
                            private val categoryRepository: CategoryRepository): ViewModel() {



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