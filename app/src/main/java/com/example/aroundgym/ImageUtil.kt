package com.example.aroundgym

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import java.net.URL

object ImageUtil {

    private lateinit var bitmap: Bitmap

    private fun getBitmapThread(url: String): Thread =
        Thread {
            try {
                val inputStream = URL(url).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Log.d("결과", e.message.toString())
                e.printStackTrace()
            }
        }


    fun setBitmapImage(url: String, imageView: ImageView) {
        val getBitmapThread = getBitmapThread(url)
        getBitmapThread.start()
        getBitmapThread.join()
        imageView.setImageBitmap(bitmap)
    }
}