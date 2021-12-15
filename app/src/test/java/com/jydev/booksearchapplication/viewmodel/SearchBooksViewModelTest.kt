package com.jydev.booksearchapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.map
import com.jydev.booksearchapplication.data.repository.BookRepositoryImpl
import com.jydev.booksearchapplication.domain.usecase.SearchBooksUseCase
import com.jydev.booksearchapplication.fake.FakeBookDataSource
import com.jydev.booksearchapplication.ui.searchbooks.SearchBooksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchBooksViewModelTest {
    private val bookRepository = BookRepositoryImpl(FakeBookDataSource())
    private val searchBooksUseCase = SearchBooksUseCase(bookRepository)
    private val searchBooksViewModel = SearchBooksViewModel(searchBooksUseCase)
    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `query 에 operator가 2개이상 있을때 Don't input more than one operator in query message를 errorMessage에 반환해야한다`() = runBlocking {
        searchBooksViewModel.searchBooks("android-kotlin|data")
        val message = searchBooksViewModel.errorMessage.getOrAwaitValue()
        assert(message == "Don't input more than one operator in query")
    }
}