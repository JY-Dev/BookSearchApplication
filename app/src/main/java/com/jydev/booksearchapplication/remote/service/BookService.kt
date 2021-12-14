package com.jydev.booksearchapplication.remote.service

import com.jydev.booksearchapplication.remote.model.SearchBooksResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {
    @GET("1.0/search/{query}/{page}")
    suspend fun searchBooks(
        @Path("query") query: String,
        @Path("page") page: Int
    ): SearchBooksResponse
}