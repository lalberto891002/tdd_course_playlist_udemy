package com.example.video_solution.playlist

import retrofit2.http.GET

interface PlayListApi {
     @GET("playlist")
     suspend fun fetchAllPlaylists():List<PlayListRaw>
}
