package com.etndevel.aespalyrics.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etndevel.aespalyrics.databinding.FragmentLyricBinding

class LyricsRecyclerViewAdapter(private val lyrics: List<String>) :
    RecyclerView.Adapter<LyricsRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(binding: FragmentLyricBinding) : RecyclerView.ViewHolder(binding.root) {
        val lyricContent = binding.lyricContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentLyricBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lyricContent.text = Html.fromHtml(lyrics[position], Html.FROM_HTML_MODE_COMPACT)
    }

    override fun getItemCount(): Int = lyrics.size
}