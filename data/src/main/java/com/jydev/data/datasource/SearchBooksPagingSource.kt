package com.jydev.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jydev.data.mapper.toDomainModel
import com.jydev.domain.model.Book
import java.lang.Exception

class SearchBooksPagingSource(
    private val bookDataSource: BookDataSource,
    private val query: String,
    private val withoutPredicate : ((Book) -> Boolean)? = null
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
            var books =
                bookDataSource.searchBooks(query = query, page).toDomainModel()
            withoutPredicate?.let {
                books = books.filter(it)
            }
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