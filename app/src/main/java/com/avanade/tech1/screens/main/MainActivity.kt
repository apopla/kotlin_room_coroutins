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
import android.view.View
import com.avanade.tech1.screens.details.DetailsActivity
import android.widget.ArrayAdapter
import com.avanade.tech1.database.BookDao
import com.avanade.tech1.model.Book
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener




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

        initSortSpinner()
    }

    fun startActivityWithBookId (bookId: Int){
        val mIntent = Intent(this, DetailsActivity::class.java)
        mIntent.putExtra(BOOK_ID_KEY, bookId)
        startActivity(mIntent)
    }

    fun initSortSpinner(){
        val list = ArrayList<String>()
        list.add("name")
        list.add("yearPublished")
        list.add("lastName")

        val dataAdapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, list)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sort_by_spinner.adapter = dataAdapter

        sort_by_spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                viewModel.sortBy(sort_by_spinner.getItemAtPosition(position).toString())
            }
            override fun onNothingSelected(parentView: AdapterView<*>) {

            }
        }

    }
}