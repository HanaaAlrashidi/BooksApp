package com.example.booksapp.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.activities.USERID
import com.example.booksapp.models.Book
import com.example.booksapp.models.MyListModel
import com.example.booksapp.repository.ApiServiceRepository
import com.example.booksapp.repository.MyListApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "MyListViewModel"

class MyListViewModel() : ViewModel() {

    private val apiRepo = MyListApiRepository()
    val myListLiveData = MutableLiveData<List<MyListModel>>()
    val editLiveData = MutableLiveData<String>()
    val deleteLiveData = MutableLiveData<String>()


    fun callMyList() {

        viewModelScope.launch(Dispatchers.IO) {
            // Coroutines Dispatchers

            // Use try and catch for handling http exceptions
            try {

                // Calling the API Methods and handles the result

                val response = apiRepo.getMyList()
                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        //Send Response to view
                        myListLiveData.postValue(this)
                    }

                } else {
                    Log.d(TAG, response.message())
                }


            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }

    fun editMyList(myListBody: MyListModel) {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = apiRepo.editMyList(myListBody.id, myListBody)
                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        editLiveData.postValue("successful")

                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }

    fun deleteFromMyList(myListModel: MyListModel) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepo.deleteFromMyList(myListModel.id)
            if (response.isSuccessful) {
                Log.d(TAG, "success")
                response.body()?.run {
                    Log.d(TAG, this.toString())
                    deleteLiveData.postValue("successful")
                }
            } else
                Log.d(TAG, "fail- ${response.message()}")
        }

    }

}