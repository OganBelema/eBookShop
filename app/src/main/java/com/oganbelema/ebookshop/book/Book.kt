package com.oganbelema.ebookshop.book

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.oganbelema.ebookshop.BR
import com.oganbelema.ebookshop.category.Category

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

}