package com.example.video_solution.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlayListService (private val api:IPlayListApi) {

  suspend  fun fetchPlayLists(): Flow<Result<List<PlayList>>> = flow {
      emit(Result.success(api.fetchAllPlaylists()))
  }.catch {
      emit(Result.failure(RuntimeException("Oops")))
  }


}
