package com.jydev.booksearchapplication.data.datasource

import com.jydev.booksearchapplication.remote.model.BookDetailResponse
import com.jydev.booksearchapplication.remote.model.SearchBooksResponse
import com.jydev.booksearchapplication.remote.service.BookService
import javax.inject.Inject

class BookDataSourceImpl @Inject constructor(private val bookApi: BookService) : BookDataSource {
    override suspend fun searchBooks(query: String, page: Int): SearchBooksResponse =
        bookApi.searchBooks(query, page)

    override suspend fun getBookDetail(bookId: String): BookDetailResponse =
        bookApi.getBookDetail(bookId)
}