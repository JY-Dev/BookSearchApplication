package com.jydev.booksearchapplication.fake

import com.jydev.booksearchapplication.data.datasource.BookDataSource
import com.jydev.booksearchapplication.remote.model.BookDetailResponse
import com.jydev.booksearchapplication.remote.model.SearchBooksResponse

class FakeBookDataSource : BookDataSource {
    private val bookFactory = BookFactory()
    override suspend fun searchBooks(query: String, page: Int): SearchBooksResponse {
        return bookFactory.makeSearchBookResponse()
    }

    override suspend fun getBookDetail(bookId: String): BookDetailResponse =
        bookDetailHashMap[bookId]!!


    private val bookDetailHashMap: HashMap<String, BookDetailResponse> = let {
        val bookDetailHashMap = hashMapOf<String, BookDetailResponse>()
        bookFactory.bookIdAndTitleHashMap.forEach {
            val bookId = it.key
            val bookTitle = it.value
            bookDetailHashMap[bookId] = bookFactory.makeBookDetailResponse(bookId,bookTitle)
        }
        bookDetailHashMap
    }
}