package com.oganbelema.ebookshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.oganbelema.ebookshop.book.BookAdapter
import com.oganbelema.ebookshop.category.Category
import com.oganbelema.ebookshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityMainBinding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val bookAdapter = BookAdapter()

        val mainActivityClickHandlers = MainActivityClickHandlers(mainActivityViewModel, this, bookAdapter)

        activityMainBinding.clickHandlers = mainActivityClickHandlers

        val spinnerAdapter = ArrayAdapter<Category>(this, R.layout.spinner_item)

        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item)

        activityMainBinding.spinnerAdapter = spinnerAdapter

        val bookRecyclerView = activityMainBinding.secondaryLayout.booksRecyclerView

        bookRecyclerView.layoutManager = LinearLayoutManager(this)

        bookRecyclerView.setHasFixedSize(true)

        activityMainBinding.bookAdapter = bookAdapter


        mainActivityViewModel.getAllCategories()?.observe(this, Observer { categories ->
            if (categories  != null){
                spinnerAdapter.addAll(categories)
                for(category in categories){
                    Log.i("MainActivity", category.categoryName)
                }
            }
        })

        mainActivityViewModel.getAllBooks()?.observe(this, Observer { books ->

            if (books != null){
                bookAdapter.addBooks(books)

                for (book in books){
                    Log.i("MainActivity", book.bookName)
                }
            }
        })


    }

}
