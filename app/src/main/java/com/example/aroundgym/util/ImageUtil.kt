package com.example.aroundgym.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.net.URL

object ImageUtil {

    private var bitmap: Bitmap? = null

    fun setBitmapImage(url: String, onSuccess: (bitmap: Bitmap) -> Unit, onFailure: () -> Unit) {
        Thread {
            try {
                val inputStream = URL(url).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Log.d("결과", e.message.toString())
                e.printStackTrace()
                onFailure()
            } finally {
                bitmap?.let(onSuccess)
            }
        }.start()
    }
}