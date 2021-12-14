package com.jydev.booksearchapplication.data.mapper

import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.domain.model.BookDetail
import com.jydev.booksearchapplication.remote.model.BookDetailResponse
import com.jydev.booksearchapplication.remote.model.SearchBooksResponse

fun SearchBooksResponse.toDomainModel(): List<Book> {
    return books.map { bookResponse ->
        with(bookResponse) {
            Book(image, isbn13, price, subtitle, title, url)
        }
    }
}

fun BookDetailResponse.toDomainModel(): BookDetail {
    return BookDetail(authors, desc, error, image, price, publisher, rating, subtitle, title, url)
}