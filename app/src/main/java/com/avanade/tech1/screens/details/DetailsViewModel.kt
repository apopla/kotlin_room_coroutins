package com.avanade.tech1.screens.details

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.avanade.tech1.Tech1App
import com.avanade.tech1.database.AuthorDao
import com.avanade.tech1.database.BookDao
import com.avanade.tech1.model.Author
import com.avanade.tech1.model.Book
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

/**
 * Created by Paulina on 2018-08-09.
 */
class DetailsViewModel: ViewModel() {

    @Inject
    lateinit var authorDao: AuthorDao
    @Inject
    lateinit var bookDao: BookDao

    val book: LiveData<Book> = MutableLiveData()
    val booksByAuthor: LiveData<List<Book>> = MutableLiveData()
    val author: LiveData<Author> = MutableLiveData()

    init {
        Tech1App.appComponent.inject(this)

    }

    fun getBook (bookId: Int) {
        launch(Tech1App.databaseCoroutineContext) {
            (book as MutableLiveData).postValue(bookDao.loadSingleBook(bookId)) }
    }

    fun getAuthor (authorId: Int){
        launch(Tech1App.databaseCoroutineContext) {
            (author as MutableLiveData).postValue(authorDao.loadSingleAuthor(authorId)) }
    }

    fun authorClicked(authorId: Int){
        launch(Tech1App.databaseCoroutineContext) {
            (booksByAuthor as MutableLiveData).postValue(bookDao.loadBooksByAuthor(authorId)) }
    }



}