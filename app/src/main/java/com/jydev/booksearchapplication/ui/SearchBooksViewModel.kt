package com.jydev.booksearchapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.domain.pagingsource.SearchBooksPagingSource
import com.jydev.booksearchapplication.domain.usecase.SearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchBooksViewModel @Inject constructor(private val searchBooksUseCase: SearchBooksUseCase) : ViewModel(){
    fun searchBooks(query: String): LiveData<PagingData<Book>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            SearchBooksPagingSource(searchBooksUseCase, query)
        }.liveData
    }
}