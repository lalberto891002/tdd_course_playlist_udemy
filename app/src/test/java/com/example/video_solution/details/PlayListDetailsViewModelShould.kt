package com.example.video_solution.details

import com.example.video_solution.utils.BaseUnitTest
import com.example.video_solution.utils.captureValues
import com.example.video_solution.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.times

class PlayListDetailsViewModelShould :BaseUnitTest() {
    lateinit var viewModel: PlayListDetailsViewModel
    private val id = "1"
    private val service: PlayListDetailsService = mock()
    private val playlistDetails: PlayListDetails = mock()
    private val expected = Result.success(playlistDetails)
    @Test
    fun getPlaylistDetailsFromService(){
        viewModel = PlayListDetailsViewModel(service)
        viewModel.getPlaylistDetails(id)

        viewModel.PlayListDetails.getValueForTest()

        verify(service,times(1)).fetchPlaylistDetails(id);
    }

    @Test
    fun emitPlaylistDetailsFromService()
    {
        mockSuccessfulCase()
        viewModel = PlayListDetailsViewModel(service)

        viewModel.getPlaylistDetails(id)

        assertEquals(expected,viewModel.PlayListDetails.getValueForTest())
    }


    @Test
    fun emitErrorWhenServiceFails(){
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow { emit(Result.failure(Exception("Something went wrong"))) }
        )
        viewModel = PlayListDetailsViewModel(service)

        viewModel.getPlaylistDetails(id)

        assertEquals("Something went wrong",viewModel.PlayListDetails.getValueForTest()?.exceptionOrNull()?.message)
    }

    @Test
    fun showSpinnerWhileLoading() = runTest{
        mockSuccessfulCase()
        viewModel = PlayListDetailsViewModel(service)

        viewModel.loader.captureValues{
            viewModel.getPlaylistDetails(id)
            viewModel.PlayListDetails.getValueForTest()
            TestCase.assertEquals(true,values[0])
        }
        verify(service, com.nhaarman.mockitokotlin2.times(1)).fetchPlaylistDetails(id)
    }

    @Test
    fun closeSpinnerAfterPalylistLoad() = runTest {
        mockSuccessfulCase()
        viewModel = PlayListDetailsViewModel(service)

        viewModel.getPlaylistDetails(id)
        viewModel.PlayListDetails.getValueForTest()
        viewModel.loader.captureValues {
            viewModel.PlayListDetails.getValueForTest()
            TestCase.assertEquals(false,values.last())
        }
    }

    private fun mockSuccessfulCase() {
        whenever(service.fetchPlaylistDetails(id)).thenReturn(
            flow { emit(expected) }
        )
    }

}