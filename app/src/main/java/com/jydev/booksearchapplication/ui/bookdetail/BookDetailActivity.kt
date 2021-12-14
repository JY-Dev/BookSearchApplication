package com.jydev.booksearchapplication.ui.bookdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jydev.booksearchapplication.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBookDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}