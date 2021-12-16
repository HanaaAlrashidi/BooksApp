package com.example.booksapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.activities.SHARED_PREF
import com.example.booksapp.activities.STATE
import com.example.booksapp.activities.USERID
import com.example.booksapp.databinding.FragmentLoginBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth


class LoginBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentLoginBottomSheetBinding
    private lateinit var sharedPref:SharedPreferences
    private lateinit var sharedPrefEditor:SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        sharedPrefEditor = sharedPref.edit()
        binding = FragmentLoginBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  isCancelable = false

        binding.loginBottomSheetButton.setOnClickListener {
            val email = binding.EmailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()


            if (email.isNotBlank() && password.isNotBlank()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(requireActivity(), "User Logged in Successfully", Toast.LENGTH_SHORT)
                                .show()
                            sharedPrefEditor.putBoolean(STATE,true).commit()
                            sharedPrefEditor.putString(USERID,FirebaseAuth.getInstance().uid).commit()
                            dismiss()
                            findNavController().navigate(R.id.action_loginFragment_to_myListFragment2)
                        }
                    }
            }else{
                Toast.makeText(requireActivity(), "Email and password must not be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }


}