package com.avanade.tech1

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.avanade.tech1.database.AuthorDao
import com.avanade.tech1.database.BookDao
import com.avanade.tech1.model.Author
import com.avanade.tech1.model.Book
import com.avanade.tech1.screens.main.MainViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.newSingleThreadContext
import javax.inject.Singleton

@Database(entities = [Book::class, Author::class], version = 1)
abstract class TechDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun authorDao(): AuthorDao
}

@Module
class ServiceModule(val app: Application) {

    @Provides @Singleton
    fun database() = Room.databaseBuilder(app, TechDatabase::class.java, "db").build()

    @Provides @Singleton
    fun bookDao(database: TechDatabase) = database.bookDao()

    @Provides @Singleton
    fun authorDao(database: TechDatabase) = database.authorDao()
}

@Component(modules = [ServiceModule::class])
interface AppComponent {
    fun inject(viewModel: MainViewModel)

    fun bookDao(): BookDao
    fun authorDao(): AuthorDao
}

class Tech1App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
        val databaseCoroutineContext = newSingleThreadContext("db")
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().serviceModule(ServiceModule(this)).build()

        launch(databaseCoroutineContext) {
            val bookDao = appComponent.bookDao()
            val authorDao = appComponent.authorDao()

            bookDao.deleteAll()
            authorDao.deleteAll()

            generateAuthors(authorDao)
            generateBooks(authorDao, bookDao)
        }
    }
}

private fun generateAuthors(authorDao: AuthorDao) {
    val author1 = Author("Henryk", "Sienkiewicz", 1846)

    val author2 = Author("Andrzej", "Sapkowski", 1948)

    listOf(author1, author2).forEach { authorDao.insert(it) }
}

private fun generateBooks(authorDao: AuthorDao, bookDao: BookDao) {
    val authors = authorDao.all()

    val book1 = Book("Ogniem i Mieczem",
            authors.first().id,
            """With Fire and Sword (Polish: Ogniem i mieczem) is a historical novel by the Polish author Henryk Sienkiewicz, published in 1884. It is the first volume of a series known to Poles as The Trilogy, followed by The Deluge (Potop, 1886) and Fire in the Steppe (originally published under the Polish title Pan Wołodyjowski, which translates to Colonel Wolodyjowski). The novel has been adapted as a film several times, most recently in 1999. With Fire and Sword is a historical fiction novel, set in the 17th century in the Polish–Lithuanian Commonwealth during the Khmelnytsky Uprising. It was initially serialized in several Polish newspapers, chapters appearing in weekly installments. It gained enormous popularity in Poland, and by the turn of the 20th century had become one of the most popular Polish books ever. It became obligatory reading in Polish schools, and has been translated into English and most European languages.""",
            1884)

    val book2 = Book("Potop",
            authors.first().id,
            """The Deluge (Polish: Potop) is a historical novel by the Polish author Henryk Sienkiewicz, published in 1886. It is the second volume of a three-volume series known to Poles as "The Trilogy," having been preceded by With Fire and Sword (Ogniem i mieczem, 1884) and followed by Fire in the Steppe (Pan Wołodyjowski, 1888). The novel tells a story of a fictional Polish-Lithuanian Commonwealth soldier and noble Andrzej Kmicic and shows a panorama of the Commonwealth during its historical period of the Deluge, which was a part of the Northern Wars.""",
            1886)

    val book3 = Book("Sezon Burz",
            authors.last().id,
            """Season of Storms (Polish original title: Sezon burz) is the sixth novel and eighth overall book in the Witcher series written by Polish fantasy writer Andrzej Sapkowski, first published in Poland in 2013. It is not a sequel to the original Witcher Saga, but rather is set between the short stories in the first book in the series, The Last Wish. Fox Children, the second installment of The Witcher comic book series published by Dark Horse Comics, is based on one of the chapters of Season of Storms. The book, with translations by David French, was published in May 2018 in hardcover format in the US and paperback format by UK Orbit (US) and Gollancz (UK).[1] It is also available in both territories as an e-book and audiobook.""",
            2013)

    val book4 = Book("Miecz Przeznaczenia",
            authors.last().id,
            """Sword of Destiny (Polish original title: Miecz przeznaczenia), is the second (in its fictional chronology; first in Polish print) of the two collections of short stories (the other being The Last Wish), both preceding the main Witcher Saga. The stories were written by Polish fantasy writer Andrzej Sapkowski. The first Polish edition was published in 1992;[1] the English edition was published in the UK by Gollancz in 2015.""",
            1992)

    listOf(book1, book2, book3, book4).forEach { bookDao.insert(it) }
}