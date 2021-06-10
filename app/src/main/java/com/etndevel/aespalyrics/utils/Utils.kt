package com.etndevel.aespalyrics.utils

import android.content.Context
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
}