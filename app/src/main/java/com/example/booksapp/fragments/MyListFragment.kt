package com.example.booksapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.activities.SHARED_PREF
import com.example.booksapp.activities.STATE
import com.example.booksapp.activities.USERID
import com.example.booksapp.adaptersimport.HomeRecyclerViewAdapter
import com.example.booksapp.adaptersimport.MyListRecyclerViewAdapter
import com.example.booksapp.databinding.FragmentHomeBinding
import com.example.booksapp.databinding.FragmentMyListBinding
import com.example.booksapp.databinding.MyListItemLayoutBinding
import com.example.booksapp.models.Book
import com.example.booksapp.models.MyListModel
import com.example.booksapp.viewmodel.BooksViewModel
import com.example.booksapp.viewmodel.MyListViewModel
import com.google.android.gms.tasks.Tasks.call
import com.google.firebase.auth.FirebaseAuth


class MyListFragment : Fragment() {

    private lateinit var binding: FragmentMyListBinding
    private lateinit var myListAdapter: MyListRecyclerViewAdapter
    private val myListViewModel: MyListViewModel by activityViewModels()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var  userID:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyListBinding.inflate(inflater, container, false)
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myListAdapter = MyListRecyclerViewAdapter()
        binding.myListRecyclerView.adapter = myListAdapter


        observers()


        //========================
        val loginFragment = LoginFragment()
        val loggedIn = sharedPref.getBoolean(STATE,false)
        if (!loggedIn){
//            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.my_list_layout,loginFragment)
//                .commit()
            findNavController().navigate(R.id.action_myListFragment2_to_loginFragment)
        }else{
         userID = sharedPref.getString(USERID,"")!!
            myListViewModel.callMyList()
        }



        //============================
    }

    fun observers(){

        myListViewModel.myListLiveData.observe(viewLifecycleOwner, {

            //Filtering the list that is coming from the response based on the userID
          val filteredList =   it.filter {
                userID == it.userid
            }


            myListAdapter.submitList(filteredList)
            binding.myListProgressBar.animate().alpha(0f).setDuration(1000)
            binding.myListRecyclerView.animate().alpha(1f)
        })


    }

}