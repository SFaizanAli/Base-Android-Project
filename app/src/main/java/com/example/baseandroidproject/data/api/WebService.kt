package com.example.baseandroidproject.data.api


import com.example.baseandroidproject.data.model.PostResponseModel
import retrofit2.Response
import retrofit2.http.GET


interface WebService {
    @GET("posts")
    suspend fun getAllPosts(): Response<PostResponseModel>

}