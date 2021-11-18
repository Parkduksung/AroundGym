package com.example.aroundgym

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSample()

        val thread = getBitmap("https://asddsa.soll0803.repl.co/kospi.PNG")

        thread.start()

        try {
            thread.join()
            findViewById<ImageView>(R.id.image).setImageBitmap(bitmap)
        } catch (e: java.lang.Exception) {
            null
        }
    }

    private fun getBitmap(url: String): Thread =
        Thread {
            try {
                val inputStream = URL(url).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Log.d("결과", e.message.toString())
                e.printStackTrace()
            }
        }


    private fun getSample() {
        val assetManager = resources.assets
        val inputStream = assetManager.open("sample.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val jObject = JSONObject(jsonString)
        val jArray = jObject.getJSONArray("posts")

        val postList = mutableListOf<Post>()

        val emptyPost = Post()

        for (i in 0 until jArray.length()) {
            val obj = jArray.getJSONObject(i)
            emptyPost.title = obj.getString("title")
            emptyPost.url = obj.getString("url")
            emptyPost.draft = obj.getString("draft")
            postList.add(emptyPost)
        }
        postList.forEach {
            Log.d("결과", it.toString())
        }
    }

    data class Post(
        var title: String = "",
        var url: String = "",
        var draft: String = ""
    )
}