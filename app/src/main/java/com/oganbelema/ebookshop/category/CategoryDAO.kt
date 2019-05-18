package com.oganbelema.ebookshop.category

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoryDAO {

    @Insert
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Delete
    fun delete(category: Category)

    @Query("SELECT * FROM category_table")
    fun getCategories(): LiveData<List<Category>>


}