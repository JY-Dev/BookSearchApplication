package com.jydev.domain.usecase

import com.jydev.domain.model.Book
import com.jydev.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class SearchBooksUseCase(private val bookRepository: BookRepository) {
    suspend operator fun <T> invoke(query : String,withoutPredicate : ((Book) -> Boolean)? = null) : Flow<T> {
        return bookRepository.searchBooks(query,withoutPredicate)
    }
}