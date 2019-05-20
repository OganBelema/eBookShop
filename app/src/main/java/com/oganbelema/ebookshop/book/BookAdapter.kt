package com.oganbelema.ebookshop.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.oganbelema.ebookshop.R
import com.oganbelema.ebookshop.databinding.BookListItemBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(book: Book)
    }

    var onItemClickListener: OnItemClickListener? = null

    private val books = ArrayList<Book>(0)

    fun addBooks(books: List<Book>){
        this.books.addAll(books)
        notifyDataSetChanged()
    }

    fun clearBooks(){
        books.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.book_list_item, parent, false))
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindBookData(books[position])
    }

    inner class BookViewHolder(private val bookListItemBinding: BookListItemBinding):
        RecyclerView.ViewHolder(bookListItemBinding.root){

        init {
            bookListItemBinding.root.setOnClickListener {

                if (adapterPosition != RecyclerView.NO_POSITION && onItemClickListener != null){
                    val book = books[adapterPosition]
                    onItemClickListener?.onItemClick(book)
                }
            }
        }

        fun bindBookData(book: Book){
            bookListItemBinding.book = book
        }
    }
}