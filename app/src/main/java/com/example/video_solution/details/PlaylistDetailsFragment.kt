package com.example.video_solution.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import com.example.video_solution.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    val viewModel: PlayListDetailsViewModel by viewModels<PlayListDetailsViewModel> { viewModelFactory }

    @Inject
    lateinit var viewModelFactory: PlaylistDetailsViewModelFactory

    val args: PlaylistDetailsFragmentArgs by navArgs()
    private lateinit var detailsLoader:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_playlist_detail, container, false)
        val id = args.playlistId
        val name = view.findViewById<TextView>(R.id.playlist_name)
        val details = view.findViewById<TextView>(R.id.playlist_details)
        detailsLoader = view.findViewById<View>(R.id.details_progress_bar)
        viewModel.getPlaylistDetails(id)

        ObserveLiveData(name, details)

        return view
    }

    private fun ObserveLiveData(name: TextView, details: TextView) {

        viewModel.loader.observe( this as LifecycleOwner) { loading ->
            when (loading) {
                true -> detailsLoader.visibility = View.VISIBLE
                false -> detailsLoader.visibility = View.INVISIBLE
            }
        }

        viewModel.PlayListDetails.observe(this as LifecycleOwner) { playlistDetails ->
            if (playlistDetails.getOrNull() != null) {
                UpdateUi(name, playlistDetails, details)
            }else{
                Snackbar.make(
                    requireView(),
                    R.string.generic_error,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun UpdateUi(
        name: TextView,
        playlistDetails: Result<PlayListDetails>,
        details: TextView
    ) {
        name.text = playlistDetails.getOrNull()!!.name
        details.text = playlistDetails.getOrNull()!!.details
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlaylistDetailsFragment()
                }

}