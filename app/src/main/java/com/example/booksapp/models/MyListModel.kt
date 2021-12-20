package com.example.booksapp.models


import com.google.gson.annotations.SerializedName

data class MyListModel(
    @SerializedName("author")
    val author: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("note")
    var note: String,
    @SerializedName("userid")
    val userid: String
)