package com.example.baseandroidproject.di


import com.example.baseandroidproject.constants.Constants.baseUrl
import com.example.baseandroidproject.data.api.WebService
import com.example.baseandroidproject.data.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun provideWebService(): WebService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideDataRepository(webService: WebService) = PostsRepository(webService)

}