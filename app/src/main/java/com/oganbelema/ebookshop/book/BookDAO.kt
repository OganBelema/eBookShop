package com.oganbelema.ebookshop.book

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDAO {

    @Insert
    fun insert(book: Book)

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)

    @Query("SELECT * FROM books_table")
    fun getBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books_table WHERE category_id ==:categoryId ")
    fun getBooksByCategory(categoryId: Int): LiveData<List<Book>>
}