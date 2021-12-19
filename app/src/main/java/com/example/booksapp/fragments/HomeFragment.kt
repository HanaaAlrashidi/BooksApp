package com.example.booksapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.booksapp.adaptersimport.HomeRecyclerViewAdapter
import com.example.booksapp.databinding.FragmentHomeBinding
import com.example.booksapp.models.Book
import com.example.booksapp.models.BookModel
import com.example.booksapp.viewmodel.BooksViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var allBooks = listOf<Book>()
    private lateinit var homeAdapter: HomeRecyclerViewAdapter
    private val booksViewModel: BooksViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter = HomeRecyclerViewAdapter(requireActivity(), booksViewModel)
        binding.homeRecyclerView.adapter = homeAdapter

        // Response Observers
        observers()

        // Event
        booksViewModel.callBooks()
    }

    fun observers(){

        booksViewModel.booksLiveData.observe(viewLifecycleOwner, {
            binding.homeProgressBar.animate().alpha(0f).setDuration(1000)
            homeAdapter.submitList(it)
            allBooks = it
            binding.homeRecyclerView.animate().alpha(1f)
        })


    }


}

