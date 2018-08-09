package com.avanade.tech1.screens.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avanade.tech1.R
import kotlinx.android.synthetic.main.activity_details.*
import android.arch.lifecycle.Observer
import com.avanade.tech1.model.Author
import com.avanade.tech1.model.Book
import com.avanade.tech1.screens.main.MainActivity
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter




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
                author_name.setOnClickListener{_ -> viewModel.authorClicked(it.id)}
            }
        })

        viewModel.booksByAuthor.observe(this, Observer {
            it?.let {
                displayDialog(it)
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

    fun displayDialog(bookList: List<Book>){
        val builderSingle = AlertDialog.Builder(this@DetailsActivity)
        builderSingle.setTitle("Books:")

        val arrayAdapter = ArrayAdapter<String>(this@DetailsActivity, android.R.layout.select_dialog_item)

        bookList.forEach{arrayAdapter.add(it.name)}

        builderSingle.setAdapter(arrayAdapter, null)
        builderSingle.show()
    }
}