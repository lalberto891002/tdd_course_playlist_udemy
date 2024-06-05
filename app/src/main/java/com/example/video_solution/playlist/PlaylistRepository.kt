package com.example.video_solution.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class PlaylistRepository {
  suspend  fun getPlaylists(): Flow<Result<List<PlayList>>> {

      val result = Result.success(
          listOf(
              PlayList(
                  "1",
                  "name song",
                  "Rock"),
              PlayList(
                  "2",
                  "name song2",
                  "Rock"
              ))
      )

      return flowOf(result)
    }

}
