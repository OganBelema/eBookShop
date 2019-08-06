package com.oganbelema.ebookshop

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import com.oganbelema.ebookshop.book.BookAdapter
import com.oganbelema.ebookshop.category.Category

class MainActivityClickHandlers(private val activity: MainActivity,
                                private val mainActivityViewModel: MainActivityViewModel,
                                private val bookAdapter: BookAdapter) {

    lateinit var selectedCategory: Category

    fun onFloatingActionButtonClicked(view: View){

        val intent = Intent(activity, AddAndEditActivity::class.java)
        activity.startActivityForResult(intent, ADD_BOOK_REQUEST_CODE)

        Log.d(MainActivityClickHandlers::class.simpleName, "Fab button clicked")
    }

    fun onSelectedItem(parent: AdapterView<*>, view: View, position: Int, id: Long){

        if (position <= -1)
            return

        selectedCategory = parent.getItemAtPosition(position) as Category

        loadBooksInSelectedCategory(selectedCategory.id)

        Log.d(MainActivityClickHandlers::class.simpleName, "Selected category is ${selectedCategory.categoryName}")
    }

    private fun loadBooksInSelectedCategory(bookCategoryId: Int){
        mainActivityViewModel.getBooksInCategory(bookCategoryId)?.observe(activity, Observer { books ->

            if (books != null){
                bookAdapter.addBooks(books)
            }
        })
    }
}