package com.jydev.booksearchapplication.di

import com.jydev.booksearchapplication.domain.repository.BookRepository
import com.jydev.booksearchapplication.domain.usecase.SearchBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideSearchBooksUseCase(booksRepository: BookRepository) : SearchBooksUseCase =
        SearchBooksUseCase(booksRepository)

}