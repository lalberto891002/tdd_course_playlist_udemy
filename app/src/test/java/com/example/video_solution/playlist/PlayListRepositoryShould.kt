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
    private val mapper: PlayListMapper = mock()
    private val playlists = mock<List<PlayList>> ()
    private val playlistsRaw = mock<List<PlayListRaw>>()
    @Test
    fun getPlaylistsFromService(): Unit = runBlocking{

        whenever(mapper.invoke(playlistsRaw)).thenReturn(playlists)
        val repository = PlaylistRepository(service,mapper)

        repository.getPlaylists()

        verify(service,times(1)).fetchPlayLists()
    }

    @Test
    fun emitPlaylistsFromService():Unit = runBlocking {
        
        mockSuccessfulCase()
        whenever(mapper.invoke(playlistsRaw)).thenReturn(playlists)
        val repository = PlaylistRepository(service,mapper)

        assertEquals(playlists,repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun propagateErrors():Unit = runBlocking{

        mockErrorCase()
        whenever(mapper.invoke(playlistsRaw)).thenReturn(playlists)

        val  repository = PlaylistRepository(service,mapper)

        assertEquals(exception,repository.getPlaylists().first().exceptionOrNull())

    }

    @Test
    fun delegateBusinessLogicToMapper():Unit = runBlocking {
        mockSuccessfulCase()
        whenever(mapper.invoke(playlistsRaw)).thenReturn(playlists)
        val repository = PlaylistRepository(service,mapper)

        repository.getPlaylists().first()

        verify(mapper,times(1)).invoke(playlistsRaw)
    }


    private suspend fun mockErrorCase() {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.failure<List<PlayListRaw>>(exception))
            })
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.fetchPlayLists()).thenReturn(
            flow {

                emit(Result.success(playlistsRaw))
            })
    }
}