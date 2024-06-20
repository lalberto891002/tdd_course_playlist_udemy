package com.example.video_solution.playlist

import com.example.video_solution.R
import javax.inject.Inject

class PlayListMapper @Inject constructor(): Function1<List<PlayListRaw>, List<PlayList>> {
    override fun invoke(playListRaw: List<PlayListRaw>): List<PlayList> {
        return playListRaw.map {
            val imageId = when(it.category){
                "rock" -> R.drawable.rock
                else -> R.drawable.playlist
            }
            PlayList(it.id,it.name,it.category,imageId)
        }
    }

}
