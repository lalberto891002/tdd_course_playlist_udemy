package com.example.video_solution.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListService @Inject constructor(private val api:PlayListApi) {

  suspend  fun fetchPlayLists(): Flow<Result<List<PlayList>>> = flow {
      emit(Result.success(api.fetchAllPlaylists()))
  }.catch {
      emit(Result.failure(RuntimeException(it.message)))
  }


}
