package com.jydev.domain.repository

import com.jydev.domain.model.Book
import com.jydev.domain.model.BookDetail
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun <T> searchBooks(query: String, withoutPredicate : ((Book) -> Boolean)? = null) : Flow<T>
    suspend fun getBookDetail(bookId : String) : BookDetail
}