package com.example.booksapp.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.models.BookModel
import com.example.booksapp.repository.ApiServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


private const val TAG = "BooksViewModel"

class BooksViewModel : ViewModel() {

    // Getting instance from Api Service Repository with companion object function
    private val apiRepo = ApiServiceRepository.get()

    val booksLiveData = MutableLiveData<BookModel>()


    fun callBooks() {

        viewModelScope.launch(Dispatchers.IO) {
            // Coroutines Dispatchers

            // Use try and catch for handling http exceptions
            try {
                // Calling the API Methods and handles the result
                val response = apiRepo.getBooks()

                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        //Send Response to view
                        booksLiveData.postValue(this)
                    }

                } else {
                    Log.d(TAG, response.message())
                }


            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }
}