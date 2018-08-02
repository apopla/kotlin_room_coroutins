package com.avanade.tech1.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Book(val name: String,
                val authorId: Int,
                val description: String,
                val yearPublished: Int,
                @PrimaryKey(autoGenerate = true) val id: Int = 0)

@Entity
data class Author(val firstName: String,
                  val lastName: String,
                  val yearOfBirth: Int,
                  @PrimaryKey(autoGenerate = true) val id: Int = 0)