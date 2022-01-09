package com.jydev.booksearchapplication.ui.searchbooks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import com.jydev.booksearchapplication.databinding.ActivityMainBinding
import com.jydev.booksearchapplication.ui.bookdetail.BookDetailActivity
import com.jydev.booksearchapplication.ui.searchbooks.adapter.SearchBooksAdapter
import com.jydev.domain.model.Book
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val searchBooksViewModel : SearchBooksViewModel by viewModels()
    private val gotoBookDetail : (String) -> Unit = { bookId ->
        val intent = Intent(this,BookDetailActivity::class.java).apply {
            putExtra(BOOK_ID,bookId)
        }
        startActivity(intent)
    }
    private val searchBooksAdapter : SearchBooksAdapter by lazy {
        SearchBooksAdapter(gotoBookDetail)
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
        searchBooksViewModel.errorMessage.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
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


    companion object{
        const val BOOK_ID = "book_id"
    }
}