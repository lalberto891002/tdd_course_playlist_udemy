package com.example.video_solution.details

import com.example.video_solution.playlist.SimpleIdlingResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListDetailsService @Inject constructor(
    private val api: PlayListDetailsApi,
    private val idlingResource: SimpleIdlingResource?
) {
    fun fetchPlaylistDetails(id:String): Flow<Result<PlayListDetails>> {
        return flow {
            idlingResource?.setIdleState(false)
            emit(Result.success(api.fetchPlaylistDetails(id)))
            idlingResource?.setIdleState(false)
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}
