package com.oganbelema.ebookshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.oganbelema.ebookshop.category.Category
import com.oganbelema.ebookshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        val activityMainBinding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val mainActivityClickHandlers = MainActivityClickHandlers()

        activityMainBinding.clickHandlers = mainActivityClickHandlers

        val spinnerAdapter = ArrayAdapter<Category>(this, R.layout.spinner_item)

        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item)

        activityMainBinding.spinnerAdapter = spinnerAdapter

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
