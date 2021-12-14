package com.jydev.booksearchapplication.di

import com.jydev.booksearchapplication.data.BookRepositoryImpl
import com.jydev.booksearchapplication.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindBookRepository(bookRepositoryImpl: BookRepositoryImpl) : BookRepository
}