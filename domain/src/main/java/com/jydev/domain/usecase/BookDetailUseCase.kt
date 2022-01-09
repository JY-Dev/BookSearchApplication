package com.jydev.domain.usecase

import com.jydev.domain.model.BookDetail
import com.jydev.domain.model.NetworkResult
import com.jydev.domain.repository.BookRepository
import java.lang.Exception

class BookDetailUseCase(private val bookRepository: BookRepository) {
    suspend operator fun invoke(bookId : String) : NetworkResult<BookDetail> {
        return try {
            val data = bookRepository.getBookDetail(bookId)
            NetworkResult.Success(data)
        } catch (e : Exception){
            NetworkResult.Error(e.message?:"NetWork Error")
        }
    }
}