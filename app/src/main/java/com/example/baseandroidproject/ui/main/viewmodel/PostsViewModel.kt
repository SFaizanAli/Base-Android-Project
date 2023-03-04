package com.example.baseandroidproject.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.model.PostResponseModel
import com.example.baseandroidproject.data.repository.PostsRepository
import com.example.baseandroidproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostsRepository) : ViewModel() {

    val postResponse: MutableLiveData<Resource<PostResponseModel>> = MutableLiveData()


    fun getPostsResultsItems() = viewModelScope.launch {
        getPostsResults()
    }



    private suspend fun getPostsResults() {
        postResponse.postValue(Resource.Loading())
        try {
            val response = repository.getPostsResults()
            response.data?.let {
                postResponse.postValue(Resource.Success(it))
            }
            postResponse.postValue(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> postResponse.postValue(Resource.Error("Network Error"))
                else -> postResponse.postValue(Resource.Error("Something went wrong"))
            }
        }
    }
}