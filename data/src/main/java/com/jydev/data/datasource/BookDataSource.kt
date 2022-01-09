package com.jydev.data.datasource

import com.jydev.data.remote.model.BookDetailResponse
import com.jydev.data.remote.model.SearchBooksResponse

interface BookDataSource {
    suspend fun searchBooks(query : String, page : Int) : SearchBooksResponse
    suspend fun getBookDetail(bookId : String) : BookDetailResponse
}