package com.jydev.booksearchapplication.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jydev.booksearchapplication.databinding.ItemSearchBooksBinding
import com.jydev.booksearchapplication.domain.model.Book

class SearchBooksViewHolder(private val binding : ItemSearchBooksBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(book : Book?){
        with(binding){
            Glide.with(binding.root.context)
                .load(book?.image)
                .into(bookImageView)
            titleTextView.text = book?.title
            subTitleTextView.text = book?.subtitle
        }
    }
}