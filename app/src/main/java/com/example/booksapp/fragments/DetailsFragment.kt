package com.example.booksapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivityMainBinding.inflate
import com.example.booksapp.databinding.FragmentDetailsBinding
import com.example.booksapp.models.Book
import com.example.booksapp.models.MyListModel
import com.example.booksapp.viewmodel.BooksViewModel
import com.example.booksapp.viewmodel.MyListViewModel
import com.squareup.picasso.Picasso


class DetailsFragment() : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val booksViewModel: BooksViewModel by activityViewModels()
    private val myListViewModel: MyListViewModel by activityViewModels()
    private lateinit var book: Book


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()

        binding.imageButton.setOnClickListener {
            booksViewModel.addBooks(book, binding.noteEditText.text.toString())
            findNavController().navigate(R.id.action_detailsFragment_to_myListFragment2)
        }


    }


    fun observers() {

        booksViewModel.selectedItemMutableLiveData.observe(viewLifecycleOwner,{
            book = it
            binding.autherTextview.text = it.authors
            binding.subtitleTextView.text = it.subtitle
            Picasso.get().load(it.image).into(binding.bookDetailsImageView)


        })




    }


}
