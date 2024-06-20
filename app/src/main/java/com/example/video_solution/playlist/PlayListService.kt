package com.example.video_solution.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListService @Inject constructor(private val api:PlayListApi,private val idlingResource: SimpleIdlingResource?) {

  suspend  fun fetchPlayLists(): Flow<Result<List<PlayListRaw>>> = flow {
      idlingResource?.setIdleState(false)
      emit(Result.success(api.fetchAllPlaylists()))
      idlingResource?.setIdleState(true)
  }.catch {
      emit(Result.failure(RuntimeException(it.message)))
  }


}
