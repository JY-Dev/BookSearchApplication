package com.jydev.booksearchapplication.domain.repository

import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.domain.model.BookDetail

interface BookRepository {
    suspend fun searchBooks(query : String, page : Int) : List<Book>
    suspend fun getBookDetail(bookId : String) : BookDetail
}