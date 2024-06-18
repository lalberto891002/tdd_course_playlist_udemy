package com.example.video_solution.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun providePlayListApi(retrofit: Retrofit):PlayListApi =  retrofit.create(PlayListApi::class.java)
    @Provides
    fun provideRetrofit():Retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:3001/")//place the url of your server
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}