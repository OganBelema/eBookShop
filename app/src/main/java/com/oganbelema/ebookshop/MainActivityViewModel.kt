package com.oganbelema.ebookshop

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oganbelema.ebookshop.book.Book
import com.oganbelema.ebookshop.book.BookRepository
import com.oganbelema.ebookshop.category.Category
import com.oganbelema.ebookshop.category.CategoryRepository
import com.oganbelema.ebookshop.database.BookDatabase

class MainActivityViewModelFactory(context: Context): ViewModelProvider.Factory {

    private val bookDatabase = BookDatabase.getInstance(context)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(CategoryRepository(bookDatabase), BookRepository(bookDatabase)) as T
    }

}

class MainActivityViewModel(private val categoryRepository: CategoryRepository,
                            private val bookRepository: BookRepository): ViewModel() {

    val categories = categoryRepository.categories

    val books = bookRepository.books


    //region Categories
    fun getAllCategories(){
        categoryRepository.getAllCategories()
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
    fun getAllBooks(){
        bookRepository.getAllBooks()
    }

    fun getBooksInCategory(categoryId: Int){
        bookRepository.getBooksInCategory(categoryId)
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