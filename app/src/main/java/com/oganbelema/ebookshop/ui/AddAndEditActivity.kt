package com.oganbelema.ebookshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.oganbelema.ebookshop.R
import com.oganbelema.ebookshop.book.Book
import com.oganbelema.ebookshop.databinding.ActivityAddAndEditBinding

const val BOOK_ID = "bookId"
const val BOOK_NAME = "bookName"
const val UNIT_PRICE = "unitPrice"
const val ADD_BOOK_REQUEST_CODE = 1
const val EDIT_BOOK_REQUEST_CODE = 2

class AddAndEditActivity : AppCompatActivity() {

    private val book: Book by lazy {
        Book("", "", 1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_and_edit)

        val activityAddAndEditBinding = DataBindingUtil
            .setContentView<ActivityAddAndEditBinding>(this, R.layout.activity_add_and_edit)

        val addAndEditActivityClickHandlers =
            AddAndEditActivityClickHandlers(this, book, this)

        activityAddAndEditBinding.book = book

        activityAddAndEditBinding.clickHandler = addAndEditActivityClickHandlers

        if (intent.hasExtra(BOOK_ID)){
            title = "Edit Book"
            book.bookId = intent.getIntExtra(BOOK_ID, -1)
            book.bookName = intent.getStringExtra(BOOK_NAME)
            book.unitPrice = intent.getStringExtra(UNIT_PRICE)
        } else {
            title = "Add New Book"
        }

    }
}
