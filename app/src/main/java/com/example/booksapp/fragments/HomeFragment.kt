package com.example.booksapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.booksapp.R
import com.example.booksapp.activities.SHARED_PREF
import com.example.booksapp.activities.STATE
import com.example.booksapp.activities.USERID
import com.example.booksapp.adaptersimport.HomeRecyclerViewAdapter
import com.example.booksapp.databinding.FragmentHomeBinding
import com.example.booksapp.models.Book
import com.example.booksapp.models.BookModel
import com.example.booksapp.viewmodel.BooksViewModel
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var allBooks = listOf<Book>()
    private lateinit var homeAdapter: HomeRecyclerViewAdapter
    private val booksViewModel: BooksViewModel by activityViewModels()

    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPrefEditor: SharedPreferences.Editor

    private lateinit var logoutItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        sharedPrefEditor = sharedPref.edit()

    }


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

    fun observers() {

        booksViewModel.booksLiveData.observe(viewLifecycleOwner, {
            binding.homeProgressBar.animate().alpha(0f).setDuration(1000)
            homeAdapter.submitList(it)
            allBooks = it
            binding.homeRecyclerView.animate().alpha(1f)
        })

    }

    //-------------------------------------------------------

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout_item -> {
                sharedPrefEditor.putBoolean(STATE,false)
                sharedPrefEditor.commit()
                FirebaseAuth.getInstance().signOut()
                Toast.makeText( requireActivity(), "you are logged out successfully", Toast.LENGTH_SHORT).show()
                logoutItem.isVisible = false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.settingmenu, menu)

        logoutItem = menu.findItem(R.id.logout_item)

        val userId = sharedPref.getString(USERID,"")

        logoutItem.isVisible = userId!!.isNotBlank()


    }


}

