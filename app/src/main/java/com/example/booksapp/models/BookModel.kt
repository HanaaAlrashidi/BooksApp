package com.example.booksapp.models


import com.google.gson.annotations.SerializedName

data class BookModel(
    @SerializedName("books")
    val books: List<Book>,
    @SerializedName("status")
    val status: String,
    @SerializedName("total")
    val total: Int
)