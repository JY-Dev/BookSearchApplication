package com.jydev.booksearchapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.jydev.booksearchapplication.databinding.ActivityMainBinding
import com.jydev.booksearchapplication.domain.model.Book
import com.jydev.booksearchapplication.ui.adapter.SearchBooksAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val searchBooksViewModel : SearchBooksViewModel by viewModels()
    private val searchBooksAdapter : SearchBooksAdapter by lazy {
        SearchBooksAdapter()
    }
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initListener()
        observeData()
    }

    private fun initView() =
        with(binding){
            with(searchBooksRecyclerview){
                adapter = searchBooksAdapter
                addItemDecoration(DividerItemDecoration(this@MainActivity, VERTICAL))
            }
        }

    private fun initListener() =
        with(binding){
            searchButton.setOnClickListener {
                val query = searchEditText.text.toString()
                clearPagingAdapter()
                searchBooksViewModel.searchBooks(query)
            }
        }

    private fun observeData(){
        searchBooksViewModel.books.observe(this){
            it.searchBooksAdapterSubmitData()
        }
    }

    private fun clearPagingAdapter(){
        PagingData.empty<Book>().searchBooksAdapterSubmitData()
    }

    private fun PagingData<Book>.searchBooksAdapterSubmitData(){
        CoroutineScope(Dispatchers.Main).launch {
            searchBooksAdapter.submitData(this@searchBooksAdapterSubmitData)
        }
    }

}