package com.example.booksapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentLoginBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class LoginBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentLoginBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}