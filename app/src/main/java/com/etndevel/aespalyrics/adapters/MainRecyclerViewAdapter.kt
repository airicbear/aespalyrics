package com.etndevel.aespalyrics.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.etndevel.aespalyrics.activities.SongActivity
import com.etndevel.aespalyrics.databinding.FragmentMainBinding
import com.etndevel.aespalyrics.fragments.MainFragmentDirections
import com.etndevel.aespalyrics.model.Album
import com.etndevel.aespalyrics.utils.Utils
import com.etndevel.aespalyrics.utils.Utils.songJsonAdapter
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
            val action: NavDirections
            if (album.lyricsPaths?.size ?: 0 > 1) {
                action = MainFragmentDirections.actionMainFragmentToAlbumFragment(
                    album,
                    album.title ?: "Black Mamba"
                )
                holder.albumCard.findNavController().navigate(action)
            } else {
                val rawString =
                    Utils.readTextAsset(holder.albumCard.context, album.lyricsPaths?.first()!!)
                val song = songJsonAdapter.fromJson(rawString)
                val intent = Intent(holder.albumCard.context, SongActivity::class.java).apply {
                    putExtra("song", song)
                }
                holder.albumCard.context.startActivity(intent)
            }
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