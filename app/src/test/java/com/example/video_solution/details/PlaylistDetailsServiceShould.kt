package com.example.video_solution.details

import com.example.video_solution.playlist.SimpleIdlingResource
import com.example.video_solution.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistDetailsServiceShould:BaseUnitTest() {

    lateinit var service: PlayListDetailsService
    private val id = "100"
    private val api: PlayListDetailsApi = mock()
    private val playlistDetails:PlayListDetails = mock()
    private val exception = RuntimeException("Something went wrong")
    val idlingResource: SimpleIdlingResource = SimpleIdlingResource()
    @Test
    fun fetchPlaylistDetailsFromApi() = runTest {

        service = PlayListDetailsService(api,idlingResource)

        service.fetchPlaylistDetails(id).single()

        verify(api,times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun convertValuesToFlowResultAndEmitThem() = runTest{

        mockSuccessfulCase()

        assertEquals(Result.success(playlistDetails),service.fetchPlaylistDetails(id).single())
    }


    @Test
    fun emitErrorResultWhenNetworkFails() = runTest{

        mockErrorCase()

        assertEquals(exception.message,service.fetchPlaylistDetails(id).single().exceptionOrNull()?.message)


    }

    private suspend fun mockErrorCase() {
        whenever(api.fetchPlaylistDetails(id)).thenThrow(RuntimeException(exception))

        service = PlayListDetailsService(api,idlingResource)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchPlaylistDetails(id)).thenReturn(playlistDetails)

        service = PlayListDetailsService(api,idlingResource)
    }

}