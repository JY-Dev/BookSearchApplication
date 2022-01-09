package com.jydev.booksearchapplication.ui.searchbooks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jydev.booksearchapplication.databinding.ItemSearchBooksBinding
import com.jydev.domain.model.Book

class SearchBooksAdapter(private val gotoBookDetail : (String) -> Unit) : PagingDataAdapter<Book, SearchBooksViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: SearchBooksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBooksViewHolder =
        SearchBooksViewHolder(ItemSearchBooksBinding.inflate(LayoutInflater.from(parent.context),parent,false),gotoBookDetail)

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.bookId == newItem.bookId


            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem

        }
    }
}