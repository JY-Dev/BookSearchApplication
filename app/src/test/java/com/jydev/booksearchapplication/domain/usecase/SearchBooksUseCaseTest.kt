package com.jydev.booksearchapplication.domain.usecase

import com.jydev.booksearchapplication.data.mapper.toDomainModel
import com.jydev.booksearchapplication.fake.FakeBookDataSource
import com.jydev.booksearchapplication.data.repository.BookRepositoryImpl
import com.jydev.booksearchapplication.fake.BookFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchBooksUseCaseTest {
    private val bookRepository = BookRepositoryImpl(FakeBookDataSource())
    private val searchBooksUseCase = SearchBooksUseCase(bookRepository)
    private val bookFactory = BookFactory()
    @Test
    fun `SearchBooksUseCaseTest invoke 시에 List Book 객체로 변환되는지 테스트`() = runBlocking {
        val result = searchBooksUseCase("",1)
        val expectResult = bookFactory.makeSearchBookResponse().toDomainModel()
        val size = result.size
        for(i in 0 until size){
            assert(result[i] == expectResult[i])
        }
    }
}