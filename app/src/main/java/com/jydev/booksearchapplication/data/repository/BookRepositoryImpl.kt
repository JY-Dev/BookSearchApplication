package com.jydev.booksearchapplication.data.repository

import com.jydev.booksearchapplication.data.datasource.BookDataSource
import com.jydev.booksearchapplication.data.mapper.toDomainModel
import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.domain.model.BookDetail
import com.jydev.booksearchapplication.domain.repository.BookRepository
import com.jydev.booksearchapplication.remote.service.BookService
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookDataSource: BookDataSource) : BookRepository {
    override suspend fun searchBooks(query: String, page: Int): List<Book> {
        return bookDataSource.searchBooks(query, page).toDomainModel()
    }

    override suspend fun getBookDetail(bookId: String): BookDetail {
        return bookDataSource.getBookDetail(bookId).toDomainModel()
    }
}