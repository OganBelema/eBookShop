package com.oganbelema.ebookshop.category

import androidx.lifecycle.LiveData
import com.oganbelema.ebookshop.database.BookDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CategoryRepository(bookDatabase: BookDatabase) {

    private val categoryDAO = bookDatabase.categoryDoa()

    lateinit var categories: LiveData<List<Category>>

    fun getAllCategories(){
        doAsync {
            val allCategories = categoryDAO.getCategories()

            uiThread {
                categories = allCategories
            }
        }
    }

    fun insertCategory(category: Category){
        doAsync {
            categoryDAO.insert(category)
        }
    }

    fun updateCategory(category: Category){
        doAsync {
            categoryDAO.update(category)
        }
    }

    fun deleteCategory(category: Category){
        doAsync {
            categoryDAO.delete(category)
        }
    }
}