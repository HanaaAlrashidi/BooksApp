package com.example.booksapp.repository

import com.example.booksapp.api.IMyListApi
import com.example.booksapp.models.MyListModel
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://61b057943c954f001722a33a.mockapi.io"
class MyListApiRepository {

    // Retrofit.Builder
    // And I need to specify a factory for deserializing the response using the Gson library

    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Builder API
    //I can then use the create method from the retrofitService to get an instance of the api.

    private val retrofitApi = retrofitService.create(IMyListApi::class.java)

    suspend fun getMyList() = retrofitApi.getMyList()

    suspend fun addToMyList(mylistBody: MyListModel) = retrofitApi.addToMyList(mylistBody)

    suspend fun editMyList(id:String,mylistBody: MyListModel) = retrofitApi.editMyList(id,mylistBody)

    suspend fun deleteFromMyList(id:String) = retrofitApi.deleteFromMyList(id)
}