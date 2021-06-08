package com.etndevel.aespalyrics.utils

import android.content.Context
import java.io.IOException

class Utils {
    companion object {
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
}