package com.avanade.tech1.screens.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avanade.tech1.R
import com.avanade.tech1.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(private val clickListener: (Int) -> Unit): RecyclerView.Adapter<BookViewHolder>() {
    private val books = mutableListOf<Book>()

    override fun getItemCount() = books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)

        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.update(books[position], clickListener)
    }

    fun update(newBooks: List<Book>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

}

class BookViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    fun update(book: Book, clickListener: (Int) -> Unit) {
        view.book_title.text = book.name

        view.setOnClickListener { clickListener(book.id) }
    }

}