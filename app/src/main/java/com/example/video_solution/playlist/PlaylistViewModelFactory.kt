package com.example.video_solution.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlaylistViewModelFactory(private val repository: PlaylistRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListViewModel(repository) as T
    }
}