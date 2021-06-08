package com.etndevel.aespalyrics.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.beust.klaxon.Klaxon
import com.etndevel.aespalyrics.model.Album
import com.etndevel.aespalyrics.databinding.FragmentAlbumBinding
import com.etndevel.aespalyrics.model.Song
import com.etndevel.aespalyrics.utils.Utils
import java.io.IOException

class AlbumRecyclerViewAdapter(
    private val album: Album
) : RecyclerView.Adapter<AlbumRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rawString = Utils.readTextAsset(holder.songCard.context, album.lyricsPaths?.get(position)!!)
        val jsonString = Klaxon()
            .parse<Song>(rawString)

        holder.songTitle.text = jsonString?.title
        val albumCoverArt = ContextCompat.getDrawable(holder.songCard.context, album.imageId)
        albumCoverArt?.setBounds(0, 0, 100, 100)
        holder.songTitle.setCompoundDrawables(albumCoverArt, null, null, null)
    }

    override fun getItemCount(): Int = album.lyricsPaths?.size ?: 0

    inner class ViewHolder(binding: FragmentAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        val songCard = binding.songCard
        val songTitle = binding.songTitle
    }

}