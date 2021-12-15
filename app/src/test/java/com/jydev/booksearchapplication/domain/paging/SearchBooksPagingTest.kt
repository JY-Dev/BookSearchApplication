package com.jydev.booksearchapplication.domain.paging

import androidx.paging.PagingSource
import com.jydev.booksearchapplication.data.mapper.toDomainModel
import com.jydev.booksearchapplication.data.repository.BookRepositoryImpl
import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.domain.pagingsource.SearchBooksPagingSource
import com.jydev.booksearchapplication.domain.usecase.SearchBooksUseCase
import com.jydev.booksearchapplication.fake.BookFactory
import com.jydev.booksearchapplication.fake.FakeBookDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchBooksPagingTest {
    private val bookRepository = BookRepositoryImpl(FakeBookDataSource())
    private val searchBooksUseCase = SearchBooksUseCase(bookRepository)
    private val bookFactory = BookFactory()
    private lateinit var searchBooksPagingSource : SearchBooksPagingSource

    @Test
    fun `searchBooksPagingSource 에서 정상적으로 Paging 되는지 테스트`() = runBlocking {
        searchBooksPagingSource = SearchBooksPagingSource(searchBooksUseCase,"")
        val result = searchBooksPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        val expectResult = PagingSource.LoadResult.Page(
            data = bookFactory.makeSearchBookResponse().toDomainModel(),
            prevKey = null,
            nextKey = 2
        )
        assert(result == expectResult)
    }

    @Test
    fun `Android-Kotlin 가 Query 로 주어졌을때 searchBooksPagingSource 에서 정상적으로 필터링 되는지 테스트`() = runBlocking {
        val query = "Android-Kotlin"
        val withoutQueryPredicate = getWithoutQueryPredicate(query)
        searchBooksPagingSource = SearchBooksPagingSource(searchBooksUseCase,query,withoutQueryPredicate)
        val result = searchBooksPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 1,
                placeholdersEnabled = false
            )
        )
        val expectResult = PagingSource.LoadResult.Page(
            data = bookFactory.makeSearchBookResponse().toDomainModel().filter(withoutQueryPredicate),
            prevKey = null,
            nextKey = 2
        )
        assert(result == expectResult)
    }

    private fun getWithoutQueryPredicate(query: String): (Book) -> Boolean {
        return {
            it.title.contains(query.split("-")[1]).not()
        }
    }
}