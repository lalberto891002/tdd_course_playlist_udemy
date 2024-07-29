package com.example.video_solution.details

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
class PlaylistDetailsModule {

    @Provides
    fun providePlayListDetailsApi(retrofit: Retrofit): PlayListDetailsApi =  retrofit.create(PlayListDetailsApi::class.java)

}