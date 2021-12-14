package com.jydev.booksearchapplication.di

import com.jydev.booksearchapplication.domain.repository.BookRepository
import com.jydev.booksearchapplication.domain.usecase.SearchBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideSearchBooksUseCase(booksRepository: BookRepository) : SearchBooksUseCase =
        SearchBooksUseCase(booksRepository)

}