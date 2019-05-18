package com.oganbelema.ebookshop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.oganbelema.ebookshop.book.Book
import com.oganbelema.ebookshop.book.BookDAO
import com.oganbelema.ebookshop.category.Category
import com.oganbelema.ebookshop.category.CategoryDAO
import org.jetbrains.anko.doAsync

@Database(entities = [Category::class, Book::class], version = 1)
abstract class BookDatabase: RoomDatabase() {

    abstract fun categoryDoa(): CategoryDAO

    abstract fun bookDoa(): BookDAO

    companion object {
        var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase? {
            if (INSTANCE == null){
                synchronized(BookDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, BookDatabase::class.java,
                        "book_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }

            return INSTANCE
        }

        private val roomCallback = object : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                if (INSTANCE != null){
                    addInitialDataAsync(INSTANCE)
                }
            }

        }

        private fun addInitialDataAsync(bookDatabase: BookDatabase?){
            val categoryDAO = bookDatabase?.categoryDoa()
            val bookDAO = bookDatabase?.bookDoa()

            doAsync {
                val category1 = Category("Text Books", "Student's text books")
                val category2 = Category("Novels", "Literature for students")
                val category3 = Category("Other Books", "General knowledge books")

                categoryDAO?.insert(category1)
                categoryDAO?.insert(category2)
                categoryDAO?.insert(category3)

                val book1 = Book("High school Java", "₦1500", 1)
                val book2 = Book("Mathematics for beginners", "₦5000", 1)
                val book3 = Book("Object Oriented Android App Design", "₦3000", 1)
                val book4 = Book("Astrology for beginners", "₦6500", 1)
                val book5 = Book("High school Magic Tricks", "₦3000", 1)
                val book6 = Book("Chemistry for Secondary school students", "₦1500", 1)
                val book7 = Book("A Game of Cats", "₦200", 2)
                val book8 = Book("The Hound of the New York", "₦700", 2)
                val book9 = Book("Adventures of Joe Finn", "₦950", 2)
                val book10 = Book("Arc of witches", "₦1000", 2)
                val book11 = Book("Can I run", "₦450", 2)
                val book12 = Book("Story of a joker", "₦1500", 2)
                val book13 = Book("Notes of a alien life cycle researcher", "₦5500", 3)
                val book14 = Book("Top 9 myths about UFOs", "₦20000", 3)
                val book15 = Book("How to become a million in 24hrs", "₦50000", 3)
                val book16 = Book("1 hour work month", "₦2000", 3)

                bookDAO?.insert(book1)
                bookDAO?.insert(book2)
                bookDAO?.insert(book3)
                bookDAO?.insert(book4)
                bookDAO?.insert(book5)
                bookDAO?.insert(book6)
                bookDAO?.insert(book7)
                bookDAO?.insert(book8)
                bookDAO?.insert(book9)
                bookDAO?.insert(book10)
                bookDAO?.insert(book11)
                bookDAO?.insert(book12)
                bookDAO?.insert(book13)
                bookDAO?.insert(book14)
                bookDAO?.insert(book15)
                bookDAO?.insert(book16)
            }
        }
    }


}