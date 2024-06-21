package com.example.video_solution.playlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.video_solution.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class PlayListFragment : Fragment() {

    private var columnCount = 1



    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

    private lateinit var viewModel: PlayListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)
        val loadDisplay = view.findViewById<View>(R.id.loadDisplay)
        val list = view.findViewById<RecyclerView>(R.id.list)

        setupViewModel()

        viewModel.loader.observe( this as LifecycleOwner) { loading ->
            when (loading) {
                    true -> loadDisplay.visibility = View.VISIBLE
                    false -> loadDisplay.visibility = View.INVISIBLE
                }
        }

        viewModel.playlists.observe(this as LifecycleOwner) { playlists ->
            if(playlists.getOrNull() != null)
                setupList(list, playlists.getOrDefault(listOf()))
            else{
                //todo
            }
        }

        return view
    }

    private fun setupViewModel() {
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
                adapter = MyPlayListRecyclerViewAdapter(playlists) { id ->
                    val action =
                        PlayListFragmentDirections.actionPlayListFragmentToPlaylistDetailFragment(id)

                    findNavController().navigate(action)
                }
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
