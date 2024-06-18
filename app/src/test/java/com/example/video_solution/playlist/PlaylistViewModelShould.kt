package com.example.video_solution.playlist

import com.example.video_solution.utils.BaseUnitTest
import com.example.video_solution.utils.captureValues
import com.example.video_solution.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test


class PlaylistViewModelShould :BaseUnitTest(){

    private lateinit var viewModel: PlayListViewModel



    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<PlayList>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Opps Wrong!")

    @Test
    fun getPlaylistsFromRepository() = runTest{
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                })
        }


        val viewModel = PlayListViewModel(repository)
        viewModel.playlists.getValueForTest()

        verify(repository,times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runTest{
        viewModel = mockSuccessfulCase()
        assertEquals(expected,viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError(){
        val viewModel = mockErrorCase()

        assertEquals(exception,viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoading() = runTest{
        viewModel = mockSuccessfulCase()
        viewModel.loader.captureValues{
            viewModel.playlists.getValueForTest()
            assertEquals(true,values[0])
        }
        verify(repository,times(1)).getPlaylists()
    }

    @Test
    fun closeSpinnerAfterPalylistLoad() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.playlists.getValueForTest()
            assertEquals(false,values.last())
        }
    }

    private fun mockSuccessfulCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                })
        }
        return PlayListViewModel(repository)
    }

    private fun mockErrorCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<PlayList>>(exception))
                }
            )
        }
        val viewModel = PlayListViewModel(repository)
        return viewModel
    }
}