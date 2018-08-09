package com.avanade.tech1.database

import android.arch.persistence.room.*
import com.avanade.tech1.model.Author
import com.avanade.tech1.model.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM Book")
    fun all(): List<Book>

    @Query("SELECT * FROM Book WHERE id = :id ")
    fun loadSingleBook(id: Int): Book

    @Query("SELECT * FROM Book WHERE authorId = :authorId ")
    fun loadBooksByAuthor(authorId: Int): List<Book>


//todo this should work but doesnt, wonder why
    @Query("SELECT * FROM Book ORDER BY :field ASC")
    fun loadBooksByOrder(field: String): List<Book>

    @Query("SELECT * FROM Book ORDER BY name ASC")
    fun loadBooksByName(): List<Book>

    @Query("SELECT * FROM Book ORDER BY yearPublished ASC")
    fun loadBooksByPublishedYear(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Query("DELETE FROM Book")
    fun deleteAll()
}

@Dao
interface AuthorDao {

    @Query("SELECT * FROM Author")
    fun all(): List<Author>

    @Query("SELECT * FROM Author WHERE id = :id ")
    fun loadSingleAuthor(id: Int): Author

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(author: Author)

    @Query("DELETE FROM Author")
    fun deleteAll()

}