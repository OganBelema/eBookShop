package com.oganbelema.ebookshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getAllCategories()?.observe(this, Observer { categories ->
            if (categories  != null){
                for(category in categories){
                    Log.i("MainActivity", category.categoryName)
                }
            }
        })

        mainActivityViewModel.getAllBooks()?.observe(this, Observer { books ->

            if (books != null){
                for (book in books){
                    Log.i("MainActivity", book.bookName)
                }
            }
        })

        mainActivityViewModel.getBooksInCategory(3)?.observe(this, Observer { books ->

            if (books != null){
                for (book in books){
                    Log.i("MainActivity", book.bookName)
                }
            }
        })
    }
}
