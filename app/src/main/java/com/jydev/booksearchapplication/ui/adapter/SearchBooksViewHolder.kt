package com.jydev.booksearchapplication.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.jydev.booksearchapplication.databinding.ItemSearchBooksBinding
import com.jydev.booksearchapplication.domain.model.Book

class SearchBooksViewHolder(private val binding : ItemSearchBooksBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(book : Book?){
        with(binding){
            titleTextView.text = book?.title
        }
    }
}