package com.jydev.data.remote.model

data class SearchBooksResponse(
    val books: List<BookResponse>,
    val error: String,
    val page: String,
    val total: String
){
    data class BookResponse(
        val image: String,
        val isbn13: String,
        val price: String,
        val subtitle: String,
        val title: String,
        val url: String
    )
}