package com.jydev.booksearchapplication.domain.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.domain.usecase.SearchBooksUseCase
import java.lang.Exception

class SearchBooksPagingSource(
    private val searchBooksUseCase: SearchBooksUseCase,
    private val query: String
) : PagingSource<Int, Book>() {
    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val page = params.key ?: 1
            val books =
                searchBooksUseCase(query = query, page)
            LoadResult.Page(
                data = books,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (books.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}