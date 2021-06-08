package com.etndevel.aespalyrics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.etndevel.aespalyrics.adapters.AlbumRecyclerViewAdapter
import com.etndevel.aespalyrics.R
import com.etndevel.aespalyrics.model.Album

/**
 * A fragment representing a list of Items.
 */
class AlbumFragment : Fragment() {

    private var columnCount = 1
    private lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            album = it.getParcelable(ALBUM)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_album_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = AlbumRecyclerViewAdapter(album)
            }
        }
        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        const val ALBUM = "album"
    }
}