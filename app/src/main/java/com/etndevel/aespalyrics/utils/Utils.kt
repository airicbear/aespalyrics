package com.etndevel.aespalyrics.utils

import android.content.Context
import com.etndevel.aespalyrics.model.Song
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.IOException

object Utils {
    fun readTextAsset(context: Context, filename: String): String {
        return try {
            context.assets.open(filename).bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            ""
        }
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val songJsonAdapter: JsonAdapter<Song> = moshi.adapter(Song::class.java)
}