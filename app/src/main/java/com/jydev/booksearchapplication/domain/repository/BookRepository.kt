package com.jydev.booksearchapplication.domain.repository

import com.jydev.booksearchapplication.domain.model.Book

interface BookRepository {
    suspend fun searchBooks(query : String, page : Int) : List<Book>
}