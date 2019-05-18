package com.oganbelema.ebookshop.category

import androidx.lifecycle.LiveData
import com.oganbelema.ebookshop.database.BookDatabase
import org.jetbrains.anko.doAsync

class CategoryRepository(bookDatabase: BookDatabase?) {

    private val categoryDAO = bookDatabase?.categoryDoa()

    private var categories: LiveData<List<Category>>? = null

    fun getAllCategories(): LiveData<List<Category>>? {
        if (categoryDAO != null){
            categories = categoryDAO.getCategories()
        }

        return categories
    }

    fun insertCategory(category: Category){
        doAsync {
            categoryDAO?.insert(category)
        }
    }

    fun updateCategory(category: Category){
        doAsync {
            categoryDAO?.update(category)
        }
    }

    fun deleteCategory(category: Category){
        doAsync {
            categoryDAO?.delete(category)
        }
    }
}