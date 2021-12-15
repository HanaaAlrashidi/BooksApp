package com.example.booksapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentLoginBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth


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
                            dismiss()
                        }
                    }
            }else{
                Toast.makeText(requireActivity(), "Email and password must not be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }


}