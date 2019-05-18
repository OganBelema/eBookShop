package com.oganbelema.ebookshop.book

import androidx.lifecycle.LiveData
import com.oganbelema.ebookshop.database.BookDatabase
import org.jetbrains.anko.doAsync

class BookRepository(bookDatabase: BookDatabase) {

    private val bookDAO = bookDatabase.bookDoa()

    lateinit var books: LiveData<List<Book>>


    fun getAllBooks(){
        books = bookDAO.getBooks()
    }

    fun getBooksInCategory(categoryId: Int){
        books = bookDAO.getBooksByCategory(categoryId)
    }

    fun insertBook(book: Book){
        doAsync {
            bookDAO.insert(book)
        }
    }

    fun updateBook(book: Book){
        doAsync {
            bookDAO.update(book)
        }
    }

    fun deleteBook(book: Book){
        doAsync {
            bookDAO.delete(book)
        }
    }

}