package com.etndevel.aespalyrics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.etndevel.aespalyrics.adapters.LyricsRecyclerViewAdapter
import com.etndevel.aespalyrics.databinding.FragmentSongBinding
import com.etndevel.aespalyrics.viewmodels.LyricsViewModel

class SongFragment : Fragment() {

    private lateinit var pageViewModel: LyricsViewModel
    private var _binding: FragmentSongBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(LyricsViewModel::class.java).apply {
            setLanguage(arguments?.getStringArray(LYRICS)?.toList() ?: listOf())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSongBinding.inflate(inflater, container, false)
        val root = binding.root

        val recyclerView: RecyclerView = binding.lyricsList
        pageViewModel.text.observe(viewLifecycleOwner) {
            val lyrics = arguments?.getStringArray(LYRICS)?.toList()
            recyclerView.adapter = LyricsRecyclerViewAdapter(lyrics ?: listOf("No lyrics found."))
        }
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val LYRICS = "lyrics"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(lyrics: Array<String>): SongFragment {
            return SongFragment().apply {
                arguments = Bundle().apply {
                    putStringArray(LYRICS, lyrics)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}