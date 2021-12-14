package com.jydev.booksearchapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jydev.booksearchapplication.databinding.ActivityMainBinding
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
    }

    private fun initView() =
        with(binding){
            searchBooksRecyclerview.adapter = searchBooksAdapter
        }

    private fun initListener() =
        with(binding){
            searchButton.setOnClickListener {
                val query = searchEditText.text.toString()
                searchBooksViewModel.searchBooks(query).observe(this@MainActivity){
                    CoroutineScope(Dispatchers.Main).launch {
                        searchBooksAdapter.submitData(it)
                    }
                }
            }
        }

}