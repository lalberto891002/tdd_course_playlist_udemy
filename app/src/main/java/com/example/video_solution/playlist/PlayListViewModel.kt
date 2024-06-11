package com.example.video_solution.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import javax.inject.Inject

class PlayListViewModel @Inject constructor(
    private val repository: PlaylistRepository
): ViewModel() {

    val playlists = liveData<Result<List<PlayList>>>{
        emitSource(repository.getPlaylists().asLiveData())
    }

}
