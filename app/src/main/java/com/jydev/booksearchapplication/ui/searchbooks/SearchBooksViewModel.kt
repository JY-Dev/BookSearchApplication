package com.jydev.booksearchapplication.ui.searchbooks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jydev.booksearchapplication.util.SingleLiveEvent
import com.jydev.domain.model.Book
import com.jydev.domain.usecase.SearchBooksUseCase
import com.jydev.util.hasNotOperator
import com.jydev.util.hasOrOperator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBooksViewModel @Inject constructor(private val searchBooksUseCase: SearchBooksUseCase) :
    ViewModel() {
    private var _books: MutableLiveData<PagingData<Book>> = MutableLiveData()
    val books: LiveData<PagingData<Book>> = _books
    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: LiveData<String> = _errorMessage
    fun searchBooks(query: String) {
        val queryHasNotOperator = query.hasNotOperator()
        val queryHasOrOperator = query.hasOrOperator()
        if (searchBooksQueryValidation(queryHasNotOperator, queryHasOrOperator)) {
            _errorMessage.value = "Don't input more than one operator in query"
            return
        }
        viewModelScope.launch {
            val withoutQueryPredicate =
                if (queryHasNotOperator) getWithoutQueryPredicate(query) else null
            val resultQuery = query.getResultQuery(queryHasNotOperator, queryHasOrOperator)
            getSearchBooksPager(resultQuery, withoutQueryPredicate)
        }
    }

    private fun searchBooksQueryValidation(
        queryHasNotOperator: Boolean,
        queryHasOrOperator: Boolean
    ): Boolean =
        (queryHasNotOperator && queryHasOrOperator)

    private fun getWithoutQueryPredicate(query: String): (Book) -> Boolean {
        return {
            it.title.contains(query.split("-")[1]).not()
        }
    }

    private fun String.getResultQuery(
        queryHasNotOperator: Boolean,
        queryHasOrOperator: Boolean
    ): String {
        return when {
            queryHasNotOperator -> split("-")[0]
            queryHasOrOperator -> replace(
                "|",
                " "
            )
            else -> this
        }
    }

    private suspend fun getSearchBooksPager(
        query: String,
        withoutPredicate: ((Book) -> Boolean)? = null
    ) {
        searchBooksUseCase<PagingData<Book>>(query, withoutPredicate)
            .cachedIn(viewModelScope)
            .collect {
                _books.value = it
            }
    }

}