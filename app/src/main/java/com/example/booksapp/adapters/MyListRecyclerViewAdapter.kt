package com.example.booksapp.adaptersimport


import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.booksapp.databinding.MyListItemLayoutBinding
import com.example.booksapp.models.MyListModel
import com.example.booksapp.viewmodel.MyListViewModel
import com.squareup.picasso.Picasso

class MyListRecyclerViewAdapter(val myListViewModel: MyListViewModel) :
    RecyclerView.Adapter<MyListRecyclerViewAdapter.MyListViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MyListModel>() {
        override fun areItemsTheSame(oldItem: MyListModel, newItem: MyListModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MyListModel, newItem: MyListModel): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    fun submitList(list: List<MyListModel>) {
        differ.submitList(list)
    }

    //    ================================================================================

    //This function to delete items by swiping
    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(index: Int) {
        val item1 = differ.currentList[index]
        var listt = mutableListOf<MyListModel>()
        listt.addAll(differ.currentList)
        listt.removeAt(index)
        differ.submitList(listt.toList())
        myListViewModel.deleteFromMyList(item1)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyListRecyclerViewAdapter.MyListViewHolder {

        val binding = MyListItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyListViewHolder(binding, myListViewModel)
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        val item = differ.currentList[position]


        holder.binding.editImageButton.setOnClickListener {
            val text = holder.binding.noteEditText.text.toString()
            item.note = text
            myListViewModel.editMyList(item)

            holder.binding.noteEditText.isFocusable = false
        }

        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class MyListViewHolder(
        val binding: MyListItemLayoutBinding,
        val myListViewModel: MyListViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MyListModel) {
            binding.titleTextview.text = item.name
            binding.noteEditText.setText(item.note)
            Picasso.get().load(item.image).into(binding.bookImageView)


        }
    }
}