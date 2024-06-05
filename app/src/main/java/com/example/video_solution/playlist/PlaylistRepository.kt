package com.example.video_solution.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class PlaylistRepository (private val service:PlayListService){
    suspend  fun getPlaylists(): Flow<Result<List<PlayList>>> = service.fetchPlayLists()

}
