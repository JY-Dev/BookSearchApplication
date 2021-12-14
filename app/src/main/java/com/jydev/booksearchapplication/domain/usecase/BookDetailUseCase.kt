package com.jydev.booksearchapplication.domain.usecase

import com.jydev.booksearchapplication.domain.model.BookDetail
import com.jydev.booksearchapplication.domain.repository.BookRepository

class BookDetailUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(bookId : String) : BookDetail =
        bookRepository.getBookDetail(bookId)
}