package com.jydev.booksearchapplication.data.datasource

import com.jydev.booksearchapplication.remote.model.BookDetailResponse
import com.jydev.booksearchapplication.remote.model.SearchBooksResponse

interface BookDataSource {
    suspend fun searchBooks(query : String, page : Int) : SearchBooksResponse
    suspend fun getBookDetail(bookId : String) : BookDetailResponse
}