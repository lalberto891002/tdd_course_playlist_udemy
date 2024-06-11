package com.example.video_solution.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PlaylistRepository @Inject constructor(private val service:PlayListService){
    suspend  fun getPlaylists(): Flow<Result<List<PlayList>>> = service.fetchPlayLists()

}
