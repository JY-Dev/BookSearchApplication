package com.jydev.booksearchapplication.remote.model

data class BookDetailResponse(
    val authors: String,
    val desc: String,
    val error: String,
    val image: String,
    val isbn10: String,
    val isbn13: String,
    val language: String,
    val pages: String,
    val price: String,
    val publisher: String,
    val rating: String,
    val subtitle: String,
    val title: String,
    val url: String,
    val year: String
)