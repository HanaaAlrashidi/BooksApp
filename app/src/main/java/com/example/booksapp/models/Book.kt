package com.example.booksapp.models


import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("authors")
    val authors: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)