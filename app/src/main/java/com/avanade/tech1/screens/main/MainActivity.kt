package com.avanade.tech1.screens.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.avanade.tech1.R
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.key
import android.content.Intent
import com.avanade.tech1.screens.details.DetailsActivity


class MainActivity : AppCompatActivity() {

    companion object {
        val BOOK_ID_KEY = "bookId"
    }

    private lateinit var viewModel: MainViewModel

    private val booksAdapter = BooksAdapter { viewModel.bookClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        books_recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = booksAdapter
        }

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.books.observe(this, Observer {
            it?.let { booksAdapter.update(it) }
        })

        viewModel.pickedBookId.observe(this, Observer {
            it?.let { startActivityWithBookId(it)}
        })
    }

    fun startActivityWithBookId (bookId: Int){
        val mIntent = Intent(this, DetailsActivity::class.java)
        mIntent.putExtra(BOOK_ID_KEY, bookId)
        startActivity(mIntent)
    }
}