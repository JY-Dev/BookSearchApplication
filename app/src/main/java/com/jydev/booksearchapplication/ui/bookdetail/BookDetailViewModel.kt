package com.jydev.booksearchapplication.ui.bookdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jydev.domain.model.BookDetail
import com.jydev.domain.model.NetworkResult
import com.jydev.domain.usecase.BookDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(private val bookDetailUseCase: BookDetailUseCase) : ViewModel() {
    private val _bookDetail = MutableLiveData<BookDetail>()
    val bookDetail : LiveData<BookDetail> = _bookDetail

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    private var bookId = ""

    fun getBookDetail(){
        viewModelScope.launch {
            if(bookId.isNotEmpty()){
                when(val networkResult = bookDetailUseCase(bookId)){
                    is NetworkResult.Success -> _bookDetail.value = networkResult.data
                    is NetworkResult.Error -> {
                        _errorMessage.value = networkResult.message
                    }
                }
            }
        }
    }

    fun setBookId(bookId : String){
        this.bookId = bookId
    }
}