package com.jydev.booksearchapplication.ui.bookdetail

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.jydev.booksearchapplication.R
import com.jydev.booksearchapplication.databinding.ActivityBookDetailBinding
import com.jydev.booksearchapplication.domain.model.BookDetail
import com.jydev.booksearchapplication.ui.searchbooks.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailActivity : AppCompatActivity() {
    private val bookDetailViewModel: BookDetailViewModel by viewModels()
    private lateinit var binding: ActivityBookDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBookId()
        observeData()
        getBookDetail()
    }

    private fun setBookId() {
        val bookId = intent.getStringExtra(MainActivity.BOOK_ID)
        bookId?.let {
            bookDetailViewModel.setBookId(bookId)
        } ?: kotlin.run {
            Toast.makeText(this, R.string.not_found_book_id, Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun getBookDetail() {
        bookDetailViewModel.getBookDetail()
    }

    private fun observeData() {
        bookDetailViewModel.bookDetail.observe(this) { bookDetail ->
            bookDetail.setView()
        }
        bookDetailViewModel.errorMessage.observe(this) { errorMessage ->
            showErrorDialog(errorMessage)
        }
    }

    private fun BookDetail.setView() =
        with(binding) {

        }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.error_popup)
            .setMessage(message)
            .setPositiveButton(R.string.retry,positiveOnClick)
            .setNegativeButton(R.string.exit,negativeOnClick)
            .show()
    }

    private val positiveOnClick = DialogInterface.OnClickListener { _, _ ->
        getBookDetail()
    }
    private val negativeOnClick = DialogInterface.OnClickListener { _, _ ->
        finish()
    }
}