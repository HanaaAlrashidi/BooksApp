package com.example.booksapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentRegisterBottmSheetBinding
import com.example.booksapp.util.RegisterValidations
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentRegisterBottmSheetBinding
    private val validator = RegisterValidations()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBottmSheetBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {

            val email = binding.EmailAddressEditText.text.toString()
            val password = binding.passwordReEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            if (email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()){
                if (password == confirmPassword){
                    if (validator.passwordIsValid(email)){
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        requireActivity(),
                                        "User Registered Successful",
                                        Toast.LENGTH_SHORT
                                    )  .show()
                                }
                            }


                }else{
                        Toast.makeText(requireActivity(), "Make sure you typed your email address correctly.", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireActivity(), "all field should be fill", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
}