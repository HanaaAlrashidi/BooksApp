package com.example.booksapp.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.example.booksapp.databinding.ActivitySplashBinding
import com.example.booksapp.repository.ApiServiceRepository


const val SHARED_PREF = "login"
const val USERID = "userId"
const val STATE = "state"
class Splash : AppCompatActivity() {

    private lateinit var  binding: ActivitySplashBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //var sharedPref = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

        //hiding action bar
        supportActionBar?.hide()

        //hiding status bar

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set tim5=e for splash
        object : CountDownTimer(2000,1000){
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {

                 startActivity(Intent(this@Splash,MainActivity::class.java))
                 finish()


            }
        }.start()
    }




    }
