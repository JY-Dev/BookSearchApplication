package com.jydev.data.mapper

import com.jydev.data.remote.model.BookDetailResponse
import com.jydev.data.remote.model.SearchBooksResponse
import com.jydev.domain.model.Book
import com.jydev.domain.model.BookDetail

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