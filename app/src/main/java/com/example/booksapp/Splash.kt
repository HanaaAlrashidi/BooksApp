package com.example.booksapp

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booksapp.databinding.ActivitySplashBinding
import com.example.booksapp.repository.ApiServiceRepository

class Splash : AppCompatActivity() {

    private lateinit var  binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Api Service Repository only for one time with companion object function in ApiServiceRepository class

        ApiServiceRepository.init(this)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}