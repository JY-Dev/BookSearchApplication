package com.jydev.booksearchapplication.data

import com.jydev.booksearchapplication.data.mapper.toDomainModel
import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.domain.repository.BookRepository
import com.jydev.booksearchapplication.remote.service.BookService
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val bookApi : BookService) : BookRepository {
    override suspend fun searchBooks(query: String, page: Int): List<Book> {
        return bookApi.searchBooks(query, page).toDomainModel()
    }
}