package com.example.booksapp.api

import com.example.booksapp.models.BookModel
import retrofit2.Response
import retrofit2.http.GET

interface IBookApi {

    @GET("/api/recent")
    suspend fun getBooks(): Response<BookModel>

}

