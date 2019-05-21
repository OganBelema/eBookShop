package com.oganbelema.ebookshop

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oganbelema.ebookshop.book.Book
import com.oganbelema.ebookshop.book.BookAdapter
import com.oganbelema.ebookshop.category.Category
import com.oganbelema.ebookshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    val bookAdapter = BookAdapter()

    private lateinit var mainActivityClickHandlers: MainActivityClickHandlers

    private lateinit var selectedCategory: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityMainBinding = DataBindingUtil
            .setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        bookAdapter.onItemClickListener = object: BookAdapter.OnItemClickListener {
            override fun onItemClick(book: Book) {
                val intent = Intent(this@MainActivity, AddAndEditActivity::class.java)
                intent.putExtra(BOOK_ID, book.bookId)
                intent.putExtra(BOOK_NAME, book.bookName)
                intent.putExtra(UNIT_PRICE, book.unitPrice)
                startActivityForResult(intent, EDIT_BOOK_REQUEST_CODE)
            }

        }

        mainActivityClickHandlers = MainActivityClickHandlers(this, mainActivityViewModel, this, bookAdapter)

        activityMainBinding.clickHandlers = mainActivityClickHandlers

        val spinnerAdapter = ArrayAdapter<Category>(this, R.layout.spinner_item)

        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item)

        activityMainBinding.spinnerAdapter = spinnerAdapter

        setupBookRecyclerView(activityMainBinding)

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

    private fun setupBookRecyclerView(activityMainBinding: ActivityMainBinding) {
        val bookRecyclerView = activityMainBinding.secondaryLayout.booksRecyclerView

        bookRecyclerView.layoutManager = LinearLayoutManager(this)

        bookRecyclerView.setHasFixedSize(true)

        val bookRecyclerViewItemTouchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    if (direction == ItemTouchHelper.LEFT) {
                        val bookToDelete = bookAdapter.books[viewHolder.adapterPosition]
                        mainActivityViewModel.deleteBook(bookToDelete)
                    }
                }

            })

        bookRecyclerViewItemTouchHelper.attachToRecyclerView(bookRecyclerView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        selectedCategory = mainActivityClickHandlers.selectedCategory

        var categoryId: Int? = null

        if (::selectedCategory.isInitialized) {
            categoryId = selectedCategory.id
        }

        if (requestCode == ADD_BOOK_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val bookName = data?.getStringExtra(BOOK_NAME)
            val unitPrice = data?.getStringExtra(UNIT_PRICE)
            if (categoryId != null && bookName != null && unitPrice != null){
                val book = Book(bookName, unitPrice, categoryId)
                mainActivityViewModel.addNewBook(book)
            }

        } else if (requestCode == EDIT_BOOK_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val bookId = data?.getIntExtra(BOOK_ID, -1)
            val bookName = data?.getStringExtra(BOOK_NAME)
            val unitPrice = data?.getStringExtra(UNIT_PRICE)
            if (bookId != null && bookId != -1 && categoryId != null && bookName != null && unitPrice != null){
                val book = Book(bookName, unitPrice, categoryId)
                book.bookId = bookId
                mainActivityViewModel.updateBook(book)
            }

        }
    }

}
