package com.jydev.booksearchapplication.data

import com.jydev.booksearchapplication.data.mapper.toDomainModel
import com.jydev.booksearchapplication.data.repository.BookRepositoryImpl
import com.jydev.booksearchapplication.fake.BookFactory
import com.jydev.booksearchapplication.fake.FakeBookDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BookRepositoryTest {
    private val bookRepository = BookRepositoryImpl(FakeBookDataSource())
    private val bookFactory = BookFactory()
    @Test
    fun `bookId 가 1로 주어졌을 때 getBookDetail 에서 BookDetailResponse 가 BookDetail 객체로 변환되는지 테스트`() = runBlocking {
        val bookId = "1"
        val expectedResult = bookFactory.makeBookDetailResponse(bookId,bookFactory.bookIdAndTitleHashMap[bookId]!!).toDomainModel()
        val result = bookRepository.getBookDetail(bookId)
        assert(expectedResult == result)
    }

    @Test
    fun `searchBooks 에서 SearchBooksResponse 가 List Book 객체로 변환되는지 테스트`() = runBlocking {
        val result = bookRepository.searchBooks("",1)
        val expectResult = bookFactory.makeSearchBookResponse().toDomainModel()
        val size = result.size
        for(i in 0 until size){
            assert(result[i] == expectResult[i])
        }
    }

}