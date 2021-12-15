package com.jydev.booksearchapplication.viewmodel

import com.jydev.booksearchapplication.data.repository.BookRepositoryImpl
import com.jydev.booksearchapplication.domain.usecase.SearchBooksUseCase
import com.jydev.booksearchapplication.fake.FakeBookDataSource
import com.jydev.booksearchapplication.ui.searchbooks.SearchBooksViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchBooksViewModelTest : BaseViewModelTest() {
    private val bookRepository = BookRepositoryImpl(FakeBookDataSource())
    private val searchBooksUseCase = SearchBooksUseCase(bookRepository)
    private val searchBooksViewModel = SearchBooksViewModel(searchBooksUseCase)

    @Test
    fun `query 에 operator가 2개이상 있을때 Don't input more than one operator in query message를 errorMessage에 반환해야한다`() =
        runBlocking {
            searchBooksViewModel.searchBooks("android-kotlin|data")
            val message = searchBooksViewModel.errorMessage.getOrAwaitValue()
            assert(message == "Don't input more than one operator in query")
        }
}