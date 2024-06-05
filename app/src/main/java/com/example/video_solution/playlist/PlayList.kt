package com.example.video_solution.playlist

import com.example.video_solution.R

data class PlayList(
    val id:String,
    val name:String,
    val category:String,
    val image:Int = R.drawable.playlist
    )
