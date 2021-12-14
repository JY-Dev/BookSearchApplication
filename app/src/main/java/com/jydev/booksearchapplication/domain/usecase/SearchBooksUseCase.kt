package com.jydev.booksearchapplication.domain.usecase

import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.domain.repository.BookRepository

class SearchBooksUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(query : String, page : Int) : List<Book>{
        return bookRepository.searchBooks(query, page)
    }
}