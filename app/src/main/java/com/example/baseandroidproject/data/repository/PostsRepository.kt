package com.example.baseandroidproject.data.repository

import com.example.baseandroidproject.data.api.WebService
import com.example.baseandroidproject.data.model.PostResponseModel
import com.example.baseandroidproject.utils.Resource


import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val webService: WebService
) {
    suspend fun getPostsResults(): Resource<PostResponseModel> {
        return try {
            val response = webService.getAllPosts()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Error("An unknown error occurred", null)
            } else {
                Resource.Error("An unknown error occurred", null)
            }
        } catch (e: Exception) {
            Resource.Error("Couldn't reach the server. check your internet connection", null)
        }
    }
}