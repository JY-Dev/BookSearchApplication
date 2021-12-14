package com.jydev.booksearchapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.domain.pagingsource.SearchBooksPagingSource
import com.jydev.booksearchapplication.domain.usecase.SearchBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBooksViewModel @Inject constructor(private val searchBooksUseCase: SearchBooksUseCase) : ViewModel(){
    private var _books : MutableLiveData<PagingData<Book>> = MutableLiveData()
    val books : LiveData<PagingData<Book>> = _books
    fun searchBooks(query: String) {
        viewModelScope.launch {
            Pager(
                PagingConfig(pageSize = 10)
            ) {
                SearchBooksPagingSource(searchBooksUseCase, query)
            }.flow.cachedIn(viewModelScope).collect {
                _books.value = it
            }
        }
    }
}