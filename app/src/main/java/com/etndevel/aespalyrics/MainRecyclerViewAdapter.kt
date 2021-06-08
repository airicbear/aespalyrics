package com.etndevel.aespalyrics

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.etndevel.aespalyrics.album.Album
import com.etndevel.aespalyrics.databinding.FragmentMainBinding
import com.google.android.material.card.MaterialCardView

/**
 * [RecyclerView.Adapter] that can display a [Album].
 */
class MainRecyclerViewAdapter(
    private val albumList: List<Album>
) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albumList[position]

        // Card
        holder.albumCard.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAlbumFragment(album, album.title ?: "Black Mamba")
            holder.albumCard.findNavController().navigate(action)
        }

        // Title
        holder.albumTitle.text = album.title
        val albumCoverArt = ContextCompat.getDrawable(holder.albumCard.context, album.imageId)
        albumCoverArt?.setBounds(0, 0, 100, 100)
        holder.albumTitle.setCompoundDrawables(albumCoverArt, null, null, null)

        // Date
        holder.albumDate.text = album.releaseDate.year.toString()
    }

    override fun getItemCount(): Int = albumList.size

    inner class ViewHolder(binding: FragmentMainBinding) : RecyclerView.ViewHolder(binding.root) {
        val albumCard: MaterialCardView = binding.albumCard
        val albumTitle: TextView = binding.albumTitle
        val albumDate: TextView = binding.albumDate
    }

}