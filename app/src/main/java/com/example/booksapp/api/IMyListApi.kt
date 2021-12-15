package com.example.booksapp.api

import com.example.booksapp.models.MyListModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface IMyListApi {

    @GET("/mylist")
    suspend fun getMyList(
        @Query("userId") userid: String
    ):Response<List<MyListModel>>

    @POST("/mylist")
    suspend fun addToMyList
                (@Body MylistBody: MyListModel):Response<ResponseBody>

    @PUT("/mylist/{id}")
    suspend fun editMyList(@Path("id")Id: String,
    @Body MylistBody: MyListModel ): Response<MyListModel>

    @DELETE("/mylist/{id}")
    suspend fun deleteFromMyList (@Path("id") Id: String): Response<ResponseBody>


}