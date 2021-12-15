package com.example.booksapp.adaptersimport

import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.booksapp.databinding.MyListItemLayoutBinding
import com.example.booksapp.models.Book
import com.squareup.picasso.Picasso

class MyListRecyclerViewAdapter:
    RecyclerView.Adapter<MyListRecyclerViewAdapter.MyListViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

    }

    private val differ =  AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<Book>){
        differ.submitList(list)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyListRecyclerViewAdapter.MyListViewHolder {

       val binding = MyListItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
       parent,
       false)
        return MyListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        val item = differ.currentList[position]

       holder.bind(item)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class MyListViewHolder(val binding: MyListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Book){
            binding.titleTextview.text = item.authors
            Picasso.get().load(item.image).into(binding.bookImageView)

        }
    }
}