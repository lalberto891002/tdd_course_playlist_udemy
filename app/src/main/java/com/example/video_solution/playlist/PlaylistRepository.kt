package com.example.video_solution.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val service:PlayListService,
    private val mapper: PlayListMapper){
    suspend  fun getPlaylists(): Flow<Result<List<PlayList>>> =
        service.fetchPlayLists().map {
           if(it.isSuccess)
               Result.success(mapper(it.getOrNull()!!))
            else
                Result.failure(it.exceptionOrNull()!!)
        }

}
