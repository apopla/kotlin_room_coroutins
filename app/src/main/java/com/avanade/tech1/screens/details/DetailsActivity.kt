package com.avanade.tech1.screens.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avanade.tech1.R
import kotlinx.android.synthetic.main.activity_details.*
import android.R.attr.defaultValue
import android.arch.lifecycle.Observer
import com.avanade.tech1.model.Author
import com.avanade.tech1.model.Book
import com.avanade.tech1.screens.main.MainActivity


/**
 * Created by Paulina on 2018-08-09.
 */
class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val bookId = intent.getIntExtra(MainActivity.BOOK_ID_KEY, 0)

        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        viewModel.getBook(bookId)

        viewModel.book.observe(this, Observer {
            it?.let {
                viewModel.getAuthor(it.authorId)
                loadUiWithBookData(it)
            }
        })

        viewModel.author.observe(this, Observer {
            it?.let {
                loadUiWithAuthorData(it)
                author_name.setOnClickListener{_ -> displayDialog(it)}
            }
        })



    }

    private fun loadUiWithBookData(book: Book){
        book_title.text = book.name
        description.text = book.description
        year_published.text = book.yearPublished.toString()
    }

    private fun loadUiWithAuthorData(author: Author){
        author_name.text = author.firstName + " " + author.lastName
    }

    fun displayDialog (author: Author){

    }
}