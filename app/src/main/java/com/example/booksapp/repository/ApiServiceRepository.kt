package com.example.booksapp.repository

import android.content.Context
import com.example.booksapp.api.IBookApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


// A Repository is a class that abstracts access to multiple data sources (Room db, Network).
//  It is a suggested best practice for code separation and architecture. A Repository class handles data operations


const val SHARED_PREF_FILE = "Auth"
private const val BASE_URL = "https://www.dbooks.org"

class ApiServiceRepository(val context: Context) {

    // Retrofit.Builder
    // And I need to specify a factory for deserializing the response using the Gson library

    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Builder API
    //I can then use the create method from the retrofitService to get an instance of the api.

    private val retrofitApi = retrofitService.create(IBookApi::class.java)

    suspend fun getBooks() = retrofitApi.getBooks()

    // to initialize and get the repository we use the companion object
    //singleton (single object)
    companion object{
        private var instance: ApiServiceRepository? = null
    }
}