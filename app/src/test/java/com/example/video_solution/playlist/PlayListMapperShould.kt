package com.example.video_solution.playlist

import com.example.video_solution.R
import com.example.video_solution.utils.BaseUnitTest
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PlayListMapperShould : BaseUnitTest() {
    private val playlistRaw = PlayListRaw("1","name","category")
    private val playListMapper = PlayListMapper()
    @Test
    fun keepSameId(){
        val playlist = playListMapper.invoke(listOf(playlistRaw))

        assertEquals(playlistRaw.id,playlist.first().id)
    }

    @Test
    fun keepSameName(){
        val playlist = playListMapper.invoke(listOf(playlistRaw))

        assertEquals(playlistRaw.name,playlist.first().name)
    }

    @Test
    fun keepSameCategory(){
        val playlist = playListMapper.invoke(listOf(playlistRaw))

        assertEquals(playlistRaw.category,playlist.first().category)
    }

    @Test
    fun mapRockCategoryIfRock(){
        val playlist = playListMapper.invoke(listOf(playlistRaw.copy(category = "rock")))

        assertEquals(R.drawable.rock,playlist.first().image)
    }

    @Test
    fun mapDefaultCategoryWhenNotRock(){
        val playlist = playListMapper.invoke(listOf(playlistRaw.copy(category = "pop")))

        assertEquals(R.drawable.playlist,playlist.first().image)
    }
}