package com.jydev.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jydev.data.datasource.BookDataSource
import com.jydev.data.datasource.SearchBooksPagingSource
import com.jydev.data.mapper.toDomainModel
import com.jydev.domain.model.Book
import com.jydev.domain.model.BookDetail
import com.jydev.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookDataSource: BookDataSource) :
    BookRepository {
    override suspend fun <T> searchBooks(query: String,withoutPredicate : ((Book) -> Boolean)?): Flow<T> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            SearchBooksPagingSource(bookDataSource, query, withoutPredicate)
        }.flow as Flow<T>
    }

    override suspend fun getBookDetail(bookId: String): BookDetail {
        return bookDataSource.getBookDetail(bookId).toDomainModel()
    }
}