package com.jydev.booksearchapplication.domain.usecase

import com.jydev.booksearchapplication.fake.BookFactory
import com.jydev.booksearchapplication.fake.FakeBookDataSource
import com.jydev.booksearchapplication.fake.FakeBookErrorDataSource
import com.jydev.booksearchapplication.data.mapper.toDomainModel
import com.jydev.booksearchapplication.data.repository.BookRepositoryImpl
import com.jydev.booksearchapplication.domain.model.NetworkResult
import com.jydev.booksearchapplication.domain.usecase.BookDetailUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BookDetailUseCaseTest {
    private val bookRepository = BookRepositoryImpl(FakeBookDataSource())
    private val bookErrorRepository = BookRepositoryImpl(FakeBookErrorDataSource())
    private val bookDetailUseCase = BookDetailUseCase(bookRepository)
    private val bookDetailErrorUseCase = BookDetailUseCase(bookErrorRepository)
    private val bookFactory = BookFactory()
    @Test
    fun `bookDetailUseCase invoke 시에 NetworkResult Success 객체로 변환되는지 테스트`() = runBlocking {
        val bookId = "1"
        val bookDetail = bookFactory.makeBookDetailResponse(bookId,bookFactory.bookIdAndTitleHashMap[bookId]!!).toDomainModel()
        val expectedResult = NetworkResult.Success(data = bookDetail)
        val result = bookDetailUseCase(bookId)
        assert(expectedResult == result)
    }

    @Test
    fun `bookDetailUseCase invoke 시에 Network Error 발생시 NetworkResult Error 객체로 변환되는지 테스트`() = runBlocking {
        val bookId = "1"
        val expectedResult = NetworkResult.Error(message = FakeBookErrorDataSource.NETWORK_ERROR_MESSAGE)
        val result = bookDetailErrorUseCase(bookId)
        assert(expectedResult == result)
    }
}