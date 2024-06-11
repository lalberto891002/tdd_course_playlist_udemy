package com.example.video_solution.playlist

import com.example.video_solution.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PlayListServiceShould: BaseUnitTest(){

    private val playlistApi: PlayListApi = mock()
    private val myPlayList =  listOf<PlayList>(PlayList("Rock1","My Song","Rock"),PlayList("Rock2","My Song2","Rock"))
    private val exceptionResult = Result.failure<Exception>(RuntimeException("Oops"))
    @Test
    fun getAllPlaylistsFromApi():Unit = runBlocking{

        val service = PlayListService(playlistApi)

        service.fetchPlayLists().first()

        verify(playlistApi,times(1)).fetchAllPlaylists()//to verify the api call
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem():Unit = runBlocking {

        mockSuccessfulValues()

        val service = PlayListService(playlistApi)

        val received = service.fetchPlayLists().first().getOrNull()
        
        assertEquals(myPlayList,received)
    }

    @Test
    fun emitsErrorResultWHenNetworkFails():Unit = runBlocking {

        mockErrorValues()

        val service = PlayListService(playlistApi)

        val received = service.fetchPlayLists().first().exceptionOrNull()?.message

        assertEquals(received,exceptionResult.exceptionOrNull()?.message)

    }

    private fun mockErrorValues():Unit = runBlocking {
        whenever(playlistApi.fetchAllPlaylists()).thenThrow(
            RuntimeException("Oops")
        )
    }

    private fun mockSuccessfulValues():Unit = runBlocking {
        whenever(playlistApi.fetchAllPlaylists()).thenReturn(
            myPlayList
        )
    }

}