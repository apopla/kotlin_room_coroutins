package com.avanade.tech1.screens.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.avanade.tech1.Tech1App
import com.avanade.tech1.database.BookDao
import com.avanade.tech1.model.Book
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class MainViewModel: ViewModel() {

    @Inject lateinit var bookDao: BookDao

    val books: LiveData<List<Book>> = MutableLiveData()

    init {
        Tech1App.appComponent.inject(this)

        launch(Tech1App.databaseCoroutineContext) { (books as MutableLiveData).postValue(bookDao.all()) }
    }

    fun bookClicked(id: Int) {

    }

}