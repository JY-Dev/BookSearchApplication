package com.jydev.booksearchapplication.fake

import android.accounts.NetworkErrorException
import com.jydev.booksearchapplication.data.datasource.BookDataSource
import com.jydev.booksearchapplication.remote.model.BookDetailResponse
import com.jydev.booksearchapplication.remote.model.SearchBooksResponse

class FakeBookErrorDataSource : BookDataSource {

    override suspend fun searchBooks(query: String, page: Int): SearchBooksResponse =
        throw NetworkErrorException(NETWORK_ERROR_MESSAGE)


    override suspend fun getBookDetail(bookId: String): BookDetailResponse =
        throw NetworkErrorException(NETWORK_ERROR_MESSAGE)

    companion object{
        const val NETWORK_ERROR_MESSAGE = "NetWork Error"
    }
}