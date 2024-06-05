package com.example.video_solution.playlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.video_solution.R

/**
 * A fragment representing a list of Items.
 */
class PlayListFragment : Fragment() {

    private var columnCount = 1

    private lateinit var viewModelFactory: PlaylistViewModelFactory
    private lateinit var viewModel: PlayListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setupViewModel()

        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            setupList(view, playlists.getOrDefault(listOf()))
        }

        return view
    }

    private fun setupViewModel() {
        viewModelFactory = PlaylistViewModelFactory(PlaylistRepository())
        viewModel = ViewModelProvider(this,viewModelFactory)[PlayListViewModel::class.java]
    }

    private fun setupList(
        view: View?,
        playlists: List<PlayList>
    ) {
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyPlayListRecyclerViewAdapter(playlists)
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            PlayListFragment().apply {
                    Log.d("TEST","${this.isInLayout}")
                }
            }
    }
