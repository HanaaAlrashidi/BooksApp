package com.example.booksapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.models.Book
import com.example.booksapp.models.BookModel
import com.example.booksapp.models.MyListModel
import com.example.booksapp.repository.ApiServiceRepository
import com.example.booksapp.repository.MyListApiRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


private const val TAG = "BooksViewModel"
class BooksViewModel : ViewModel() {

    private val apiRepo = ApiServiceRepository
    private val apiMylistRepo = MyListApiRepository()

    val booksLiveData = MutableLiveData<List<Book>>()
    val myListLiveData = MutableLiveData<MyListViewModel>()
    var selectedItemMutableLiveData = MutableLiveData<Book>()


    fun callBooks() {

        viewModelScope.launch(Dispatchers.IO) {
            // Coroutines Dispatchers

            // Use try and catch for handling http exceptions
            try {
                // Calling the API Methods and handles the result
                val response = apiRepo.getBooks()

                if (response.isSuccessful) {
                    Log.d(TAG, "Success")
                 response.body()?.run{
                     Log.d(TAG, this.toString())
                     //Send Response to view
                     booksLiveData.postValue(books)
                 }

                } else {
                    Log.d(TAG, response.message())
                }


            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }



    fun addBooks(myListModel: Book, note: String){

        Log.d(TAG,myListModel.toString())
        viewModelScope.launch(Dispatchers.IO) {
            // Coroutines Dispatchers

            // Use try and catch for handling http exceptions
            try {
                // Calling the API Methods and handles the result
                val response = apiMylistRepo.addToMyList(
                    MyListModel(myListModel.authors,myListModel.id,myListModel.image,myListModel.title,note,
                FirebaseAuth.getInstance().currentUser!!.uid)
                )

                if (response.isSuccessful) {
                    Log.d(TAG, "Success")
                    response.body()?.run{
                        Log.d(TAG, "Book: ${this.toString()}")


                    }

                } else {
                    Log.d(TAG, "ERROR HERE")
                    Log.d(TAG, response.message())
                }

                Log.d(TAG, response.message())

            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }

    }
}