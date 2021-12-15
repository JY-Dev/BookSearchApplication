package com.jydev.booksearchapplication.di

import com.jydev.booksearchapplication.data.datasource.BookDataSource
import com.jydev.booksearchapplication.data.datasource.BookDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindBookDataSource(bookDataSourceImpl: BookDataSourceImpl): BookDataSource
}