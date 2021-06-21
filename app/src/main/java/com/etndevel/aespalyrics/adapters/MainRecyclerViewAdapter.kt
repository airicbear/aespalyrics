package com.etndevel.aespalyrics.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.etndevel.aespalyrics.activities.SongActivity
import com.etndevel.aespalyrics.databinding.FragmentMainBinding
import com.etndevel.aespalyrics.fragments.MainFragmentDirections
import com.etndevel.aespalyrics.model.Album
import com.etndevel.aespalyrics.model.Song
import com.etndevel.aespalyrics.utils.Utils
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.*

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

    private fun getSongAsync(album: Album, holder: ViewHolder): Deferred<Song?> =
        CoroutineScope(Dispatchers.IO).async {
            val rawString =
                Utils.readTextAsset(holder.albumCard.context, album.lyricsPaths?.first()!!)
            return@async Klaxon()
                .parse<Song>(rawString)
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albumList[position]

        val song = CoroutineScope(Dispatchers.IO).async {
            return@async getSongAsync(
                album,
                holder
            ).await()
        }

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
                CoroutineScope(Dispatchers.IO).launch {
                    val intent = Intent(holder.albumCard.context, SongActivity::class.java).apply {
                        putExtra("song", song.await())
                    }
                    holder.albumCard.context.startActivity(intent)
                }
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