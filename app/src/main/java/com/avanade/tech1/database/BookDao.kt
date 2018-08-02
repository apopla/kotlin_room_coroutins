package com.avanade.tech1.database

import android.arch.persistence.room.*
import com.avanade.tech1.model.Author
import com.avanade.tech1.model.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM Book")
    fun all(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Query("DELETE FROM Book")
    fun deleteAll()
}

@Dao
interface AuthorDao {

    @Query("SELECT * FROM Author")
    fun all(): List<Author>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(author: Author)

    @Query("DELETE FROM Author")
    fun deleteAll()

}