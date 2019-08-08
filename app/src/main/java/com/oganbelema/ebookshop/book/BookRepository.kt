package com.oganbelema.ebookshop.book

import androidx.lifecycle.LiveData
import com.oganbelema.ebookshop.database.BookDatabase
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(bookDatabase: BookDatabase?) {

    private val bookDAO = bookDatabase?.bookDoa()

    private var books: LiveData<List<Book>>? = null


    fun getAllBooks(): LiveData<List<Book>>? {
        if (bookDAO !=  null){
            books = bookDAO.getBooks()
        }

        return books
    }

    fun getBooksInCategory(categoryId: Int): LiveData<List<Book>>?{
        if (bookDAO != null){
            books = bookDAO.getBooksByCategory(categoryId)
        }

        return books
    }

    fun insertBook(book: Book){
        doAsync {
            bookDAO?.insert(book)
        }
    }

    fun updateBook(book: Book){
        doAsync {
            bookDAO?.update(book)
        }
    }

    fun deleteBook(book: Book){
        doAsync {
            bookDAO?.delete(book)
        }
    }

}