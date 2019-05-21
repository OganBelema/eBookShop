package com.oganbelema.ebookshop

import androidx.recyclerview.widget.DiffUtil
import com.oganbelema.ebookshop.book.Book

class BooksDiffCallback(private val oldBookList: List<Book>, private val newBookList: List<Book>): DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBookList[oldItemPosition].bookId == newBookList[newItemPosition].bookId
    }

    override fun getOldListSize(): Int = oldBookList.size

    override fun getNewListSize(): Int = newBookList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldBookList[oldItemPosition].equals(newBookList[newItemPosition].bookId)
    }

}