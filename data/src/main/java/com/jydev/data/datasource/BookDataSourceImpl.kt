package com.jydev.data.datasource

import com.jydev.data.remote.model.BookDetailResponse
import com.jydev.data.remote.model.SearchBooksResponse
import com.jydev.data.remote.service.BookService
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(private val bookApi: BookService) : BookDataSource {
    override suspend fun searchBooks(query: String, page: Int): SearchBooksResponse =
        bookApi.searchBooks(query, page)

    override suspend fun getBookDetail(bookId: String): BookDetailResponse =
        bookApi.getBookDetail(bookId)
}