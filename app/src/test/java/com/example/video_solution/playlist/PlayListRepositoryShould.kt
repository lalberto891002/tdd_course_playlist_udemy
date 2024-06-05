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

class PlayListRepositoryShould :BaseUnitTest() {

    private val exception = RuntimeException("opps Error!")
    private val service: PlayListService = mock()
    private val playlists = mock<List<PlayList>> ()
    @Test
    fun getPlaylistsFromService(): Unit = runBlocking{

        val repository = PlaylistRepository(service)

        repository.getPlaylists()

        verify(service,times(1)).fetchPlayLists()
    }

    @Test
    fun emitPlaylistsFromService():Unit = runBlocking {
        
        mockSuccessfulCase()

        val repository = PlaylistRepository(service)

        assertEquals(playlists,repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun propagateErrors():Unit = runBlocking{

        mockErrorCase()

        val  repository = PlaylistRepository(service)

        assertEquals(exception,repository.getPlaylists().first().exceptionOrNull())

    }

    private suspend fun mockErrorCase() {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.failure<List<PlayList>>(exception))
            })
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {

                emit(Result.success(playlists))
            })
    }
}