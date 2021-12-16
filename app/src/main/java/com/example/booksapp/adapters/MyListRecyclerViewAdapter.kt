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
import com.example.booksapp.models.MyListModel
import com.squareup.picasso.Picasso

class MyListRecyclerViewAdapter:
    RecyclerView.Adapter<MyListRecyclerViewAdapter.MyListViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MyListModel>() {
        override fun areItemsTheSame(oldItem: MyListModel, newItem: MyListModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyListModel, newItem: MyListModel): Boolean {
            return oldItem == newItem
        }

    }

    private val differ =  AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<MyListModel>){
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
        fun bind(item: MyListModel){
            binding.titleTextview.text = item.name
            Picasso.get().load(item.image).into(binding.bookImageView)

        }
    }
}