package com.jydev.booksearchapplication.viewmodel

import com.jydev.booksearchapplication.data.mapper.toDomainModel
import com.jydev.booksearchapplication.data.repository.BookRepositoryImpl
import com.jydev.booksearchapplication.domain.usecase.BookDetailUseCase
import com.jydev.booksearchapplication.fake.BookFactory
import com.jydev.booksearchapplication.fake.FakeBookDataSource
import com.jydev.booksearchapplication.fake.FakeBookErrorDataSource
import com.jydev.booksearchapplication.ui.bookdetail.BookDetailViewModel
import org.junit.Test

class BookDetailViewModelTest : BaseViewModelTest(){
    private val bookRepository = BookRepositoryImpl(FakeBookDataSource())
    private val bookDetailUseCase = BookDetailUseCase(bookRepository)
    private val bookDetailViewModel = BookDetailViewModel(bookDetailUseCase)
    private val bookErrorRepository = BookRepositoryImpl(FakeBookErrorDataSource())
    private val bookDetailErrorUseCase = BookDetailUseCase(bookErrorRepository)
    private val bookDetailErrorViewModel = BookDetailViewModel(bookDetailErrorUseCase)
    private val bookFactory = BookFactory()
    @Test
    fun `_bookId가 주어지고 bookDetailVIewModel getBookDetail 호출시 bookDetail 에 정상적으로 값이 갱신 되는지 테스트`(){
        val bookId = "1"
        val bookTitle = bookFactory.bookIdAndTitleHashMap[bookId]!!
        bookDetailViewModel.setBookId(bookId)
        bookDetailViewModel.getBookDetail()
        val result = bookDetailViewModel.bookDetail.getOrAwaitValue()
        assert(result == bookFactory.makeBookDetailResponse(bookId,bookTitle).toDomainModel())
    }

    @Test
    fun `bookDetailVIewModel getBookDetail 에서 Error 발생시 errorMessage 에 정상적으로 값이 갱신 되는지 테스트`(){
        val bookId = "1"
        bookDetailErrorViewModel.setBookId(bookId)
        bookDetailErrorViewModel.getBookDetail()
        val result = bookDetailErrorViewModel.errorMessage.getOrAwaitValue()
        assert(result == FakeBookErrorDataSource.NETWORK_ERROR_MESSAGE)
    }
}