package com.example.booksapp.adaptersimport

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.booksapp.fragments.BooksViewModel
import com.example.booksapp.models.Book
import com.example.booksapp.models.BookModel
import com.squareup.picasso.Picasso

private const val TAG = "HomeRecyclerViewAdapter"
class HomeRecyclerViewAdapter(val context: Context, val viewModel: BooksViewModel) :
    RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Book>(){
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecyclerViewAdapter.HomeViewHolder {

        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.bookTitleTextView.text = item.title

        Picasso.get().load(item.image).into(holder.bookImage)


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Book>){
        differ.submitList(list)
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val bookTitleTextView: TextView = itemView.findViewById(R.id.book_Title)
        val bookImage: ImageView = itemView.findViewById(R.id.book_imageView)
    }
}