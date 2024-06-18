package com.example.video_solution.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PlayListViewModel @Inject constructor(
    private val repository: PlaylistRepository
): ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlists = liveData<Result<List<PlayList>>>{
        loader.postValue(true)


        emitSource(repository.getPlaylists().onEach {
            loader.postValue(false)
        }.asLiveData())
    }

}
