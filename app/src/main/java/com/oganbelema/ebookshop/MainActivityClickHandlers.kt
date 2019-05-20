package com.oganbelema.ebookshop

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.oganbelema.ebookshop.book.BookAdapter
import com.oganbelema.ebookshop.category.Category

class MainActivityClickHandlers(private val mainActivityViewModel: MainActivityViewModel,
                                private val lifecycleOwner: LifecycleOwner,
                                private val bookAdapter: BookAdapter) {

    fun onFloatingActionButtonClicked(view: View){
        Log.d(MainActivityClickHandlers::class.simpleName, "Fab button clicked")
    }

    fun onSelectedItem(parent: AdapterView<*>, view: View, position: Int, id: Long){

        if (position <= -1)
            return

        val selectedCategory = parent.getItemAtPosition(position) as Category

        loadBooksInSelectedCategory(selectedCategory.id)

        Log.d(MainActivityClickHandlers::class.simpleName, "Selected category is ${selectedCategory.categoryName}")
    }

    private fun loadBooksInSelectedCategory(bookCategoryId: Int){
        mainActivityViewModel.getBooksInCategory(bookCategoryId)?.observe(lifecycleOwner, Observer { books ->

            if (books != null){
                bookAdapter.clearBooks()
                bookAdapter.addBooks(books)
            }
        })
    }
}