package com.oganbelema.ebookshop

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.oganbelema.ebookshop.book.Book

class AddAndEditActivityClickHandlers(private val context: Context,
                                      private val book: Book,
                                      private val activity: AddAndEditActivity) {

    fun onSubmitButtonClicked(view: View){
        if (book.bookName.isEmpty()){
            Toast.makeText(context, "Name field cannot be empty", Toast.LENGTH_LONG)
                .show()
        } else {
            val intent = Intent()
            intent.putExtra(BOOK_ID, book.bookId)
            intent.putExtra(BOOK_NAME, book.bookName)
            intent.putExtra(UNIT_PRICE, book.unitPrice)
            activity.setResult(AppCompatActivity.RESULT_OK, intent)
            activity.finish()
        }
    }
}