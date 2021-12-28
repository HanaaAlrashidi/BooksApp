package com.example.booksapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentLoginBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.creatButton.setOnClickListener {

            var register = RegisterBottomSheetFragment()
            register.show(childFragmentManager, "")
        }

        binding.loginButton.setOnClickListener {
            var login = LoginBottomSheetFragment()
            login.show(childFragmentManager, "")
        }


    }


}