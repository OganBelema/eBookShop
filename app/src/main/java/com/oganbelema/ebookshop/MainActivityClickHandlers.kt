package com.oganbelema.ebookshop

import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.oganbelema.ebookshop.category.Category

class MainActivityClickHandlers {

    private var selectedCategory: Category? = null

    fun onFloatingActionButtonClicked(view: View){
        Log.d(MainActivityClickHandlers::class.simpleName, "Fab button clicked")
    }

    fun onSelectedItem(parent: AdapterView<*>, view: View, position: Int, id: Long){

        selectedCategory = parent.getItemAtPosition(position) as Category

        Log.d(MainActivityClickHandlers::class.simpleName, "Selected category is ${selectedCategory?.categoryName}")
    }
}