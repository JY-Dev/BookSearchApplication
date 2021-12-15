package com.jydev.booksearchapplication.fake

import com.jydev.booksearchapplication.remote.model.BookDetailResponse
import com.jydev.booksearchapplication.remote.model.SearchBooksResponse

class BookFactory {
    val bookIdAndTitleHashMap = hashMapOf(
        "1" to "Android Study1",
        "2" to "Android Study2",
        "3" to "Android Kotlin Study1",
        "4" to "Android Kotlin Study2"
    )
    private val books : List<SearchBooksResponse.BookResponse> = let {
        val books = mutableListOf<SearchBooksResponse.BookResponse>()
        bookIdAndTitleHashMap.forEach {
            val bookId = it.key
            val bookTitle = it.value
            books.add(makeBooksResponse(bookId,bookTitle))
        }
        books
    }
    fun makeSearchBookResponse() : SearchBooksResponse =
        SearchBooksResponse(books,"0","1", books.size.toString())

    fun makeBookDetailResponse(bookId: String, title: String): BookDetailResponse =
        BookDetailResponse(
            "저자",
            "설명",
            "0",
            "image",
            "0",
            bookId,
            "English",
            "350",
            "13000",
            "출판사",
            "3.5",
            "Sub Title",
            title,
            "url",
            ""
        )

    private fun makeBooksResponse(bookId : String, title :String): SearchBooksResponse.BookResponse =
        SearchBooksResponse.BookResponse(
            "image",
            bookId,
            "13000",
            "",
            title,
            "url"
        )
}