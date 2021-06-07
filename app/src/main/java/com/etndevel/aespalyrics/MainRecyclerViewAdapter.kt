package com.etndevel.aespalyrics

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.etndevel.aespalyrics.album.Album
import com.etndevel.aespalyrics.databinding.FragmentMainBinding

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
        val albumItem = albumList[position]
        holder.albumItem.text = albumItem.title
        holder.albumDate.text = albumItem.releaseDate.year.toString()

        val albumCoverArt = ContextCompat.getDrawable(holder.albumItem.context, albumItem.imageId)
        albumCoverArt?.setBounds(0, 0, 100, 100)
        holder.albumItem.setCompoundDrawables(albumCoverArt, null, null, null)
    }

    override fun getItemCount(): Int = albumList.size

    inner class ViewHolder(binding: FragmentMainBinding) : RecyclerView.ViewHolder(binding.root) {
        val albumItem: TextView = binding.albumItem
        val albumDate: TextView = binding.albumDate
    }

}