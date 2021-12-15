package com.example.booksapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.booksapp.R
import com.example.booksapp.adaptersimport.HomeRecyclerViewAdapter
import com.example.booksapp.adaptersimport.MyListRecyclerViewAdapter
import com.example.booksapp.databinding.FragmentHomeBinding
import com.example.booksapp.databinding.FragmentMyListBinding
import com.example.booksapp.databinding.MyListItemLayoutBinding
import com.example.booksapp.models.Book
import com.example.booksapp.viewmodel.BooksViewModel
import com.example.booksapp.viewmodel.MyListViewModel
import com.google.android.gms.tasks.Tasks.call


class MyListFragment : Fragment() {

    private lateinit var binding: FragmentMyListBinding
    private lateinit var myListAdapter: MyListRecyclerViewAdapter
    private val myListViewModel: MyListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myListAdapter = MyListRecyclerViewAdapter()
        binding.myListRecyclerView.adapter = myListAdapter

        observers()

        myListViewModel.callMyList()



    }



    fun observers(){

        myListViewModel.myListLiveData.observe(viewLifecycleOwner, {
            binding.myListProgressBar.animate().alpha(0f).setDuration(1000)
            binding.myListRecyclerView.animate().alpha(1f)
        })


    }

}