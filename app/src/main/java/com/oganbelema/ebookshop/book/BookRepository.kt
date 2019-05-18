package com.oganbelema.ebookshop.book

import androidx.lifecycle.LiveData
import com.oganbelema.ebookshop.database.BookDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BookRepository(bookDatabase: BookDatabase) {

    private val bookDAO = bookDatabase.bookDoa()

    lateinit var books: LiveData<List<Book>>


    fun getAllBooks(){
        doAsync {
            val allBooks = bookDAO.getBooks()

            uiThread {
                books = allBooks
            }
        }
    }

    fun getBooksInCategory(categoryId: Int){
        doAsync {
            val booksInCategory = bookDAO.getBooksByCategory(categoryId)

            uiThread {
                books = booksInCategory
            }
        }
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