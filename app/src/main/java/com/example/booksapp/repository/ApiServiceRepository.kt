package com.example.booksapp.repository


import com.example.booksapp.api.IBookApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


// A Repository is a class that abstracts access to multiple data sources.
//  It is a suggested best practice for code separation and architecture. A Repository class handles data operations


private const val BASE_URL = "https://www.dbooks.org"

object ApiServiceRepository {

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


}