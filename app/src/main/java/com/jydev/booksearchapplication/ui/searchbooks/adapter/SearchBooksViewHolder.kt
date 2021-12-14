package com.jydev.booksearchapplication.ui.searchbooks.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jydev.booksearchapplication.databinding.ItemSearchBooksBinding
import com.jydev.booksearchapplication.domain.model.Book

class SearchBooksViewHolder(private val binding : ItemSearchBooksBinding,private val gotoBookDetail : (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(book : Book?){
        book?.let {
            with(binding){
                root.setOnClickListener {
                    gotoBookDetail(book.bookId)
                }
                Glide.with(binding.root.context)
                    .load(book.image)
                    .into(bookImageView)
                titleTextView.text = book.title
                subTitleTextView.text = book.subtitle
            }
        }
    }
}