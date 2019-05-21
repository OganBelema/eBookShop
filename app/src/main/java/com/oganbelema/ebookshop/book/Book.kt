package com.oganbelema.ebookshop.book

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.*
import com.oganbelema.ebookshop.BR
import com.oganbelema.ebookshop.category.Category
import java.util.*

@Entity(tableName = "books_table",  foreignKeys = [ForeignKey(entity = Category::class, parentColumns = ["id"],
    childColumns = ["category_id"], onDelete = ForeignKey.CASCADE)])
class Book(bookName: String, unitPrice: String, categoryId: Int) : BaseObservable() {

    @Bindable
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    var bookId: Int = 0
    set(value) {
        field = value
        notifyPropertyChanged(BR.bookId)
    }

    @Bindable
    @ColumnInfo(name = "book_name")
    var bookName: String = bookName
    set(value) {
        field = value
        notifyPropertyChanged(BR.bookName)
    }

    @Bindable
    @ColumnInfo(name = "unit_price")
    var unitPrice: String = unitPrice
    set(value) {
        field = value
        notifyPropertyChanged(BR.unitPrice)
    }

    @Bindable
    @ColumnInfo(name = "category_id")
    var categoryId: Int = categoryId
    set(value) {
        field = value
        notifyPropertyChanged(BR.categoryId)
    }

    @Ignore
    override fun hashCode(): Int {
        return Objects.hash(bookId, bookName, unitPrice, categoryId)
    }

    @Ignore
    override fun equals(other: Any?): Boolean {
        if (other !is Book) return false
        return bookId == other.bookId && categoryId == other.categoryId
                && bookName == other.bookName && unitPrice == other.unitPrice
    }

}