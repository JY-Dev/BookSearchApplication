package com.jydev.booksearchapplication.remote.model

data class BookSearchResponse(
    val books: List<Book>,
    val error: String,
    val page: String,
    val total: String
){
    data class Book(
        val image: String,
        val isbn13: String,
        val price: String,
        val subtitle: String,
        val title: String,
        val url: String
    )
}