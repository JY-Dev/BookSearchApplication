package com.jydev.booksearchapplication.domain.model

sealed class NetworkResult<out T>{
    data class Success<T>(val data : T) : NetworkResult<T>()
    data class Error(val message : String) : NetworkResult<Nothing>()
}
