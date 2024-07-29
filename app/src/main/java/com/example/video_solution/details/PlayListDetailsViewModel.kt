package com.example.video_solution.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayListDetailsViewModel @Inject constructor(private val service: PlayListDetailsService)
    :ViewModel(){

    fun getPlaylistDetails(id: String) {
        viewModelScope.launch {
            loader.postValue(true)
            service.fetchPlaylistDetails(id)
                .collect {
                    PlayListDetails.postValue(it)
                    loader.postValue(false)
                }
        }

    }

    val loader = MutableLiveData<Boolean>()
    val PlayListDetails: MutableLiveData<Result<PlayListDetails>> = MutableLiveData()

}
