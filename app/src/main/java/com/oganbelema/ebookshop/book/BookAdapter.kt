package com.oganbelema.ebookshop.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.oganbelema.ebookshop.BooksDiffCallback
import com.oganbelema.ebookshop.R
import com.oganbelema.ebookshop.databinding.BookListItemBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(book: Book)
    }

    var onItemClickListener: OnItemClickListener? = null

    private var _books: List<Book> = ArrayList(0)

    val books: List<Book>
    get() = _books

    fun addBooks(books: List<Book>){
        //this._books.addAll(books)
        //notifyDataSetChanged()

        doAsync {
            val diffUtilResult = DiffUtil.calculateDiff(BooksDiffCallback(_books, books), false)
            _books = books
            diffUtilResult.dispatchUpdatesTo(this@BookAdapter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.book_list_item, parent, false))
    }

    override fun getItemCount(): Int = _books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindBookData(_books[position])
    }

    inner class BookViewHolder(private val bookListItemBinding: BookListItemBinding):
        RecyclerView.ViewHolder(bookListItemBinding.root){

        init {
            bookListItemBinding.root.setOnClickListener {

                if (adapterPosition != RecyclerView.NO_POSITION && onItemClickListener != null){
                    val book = _books[adapterPosition]
                    onItemClickListener?.onItemClick(book)
                }
            }
        }

        fun bindBookData(book: Book){
            bookListItemBinding.book = book
        }
    }
}