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

    }


    //url 을 읽어 bitmap 으로 변환.
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

    //bitmap 을 imageview 에 넣기.
    private fun setImageView() {

        val thread =
            getBitmap("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F486985%3Ftimestamp%3D20210504151232")

        thread.start()

        try {
            thread.join()
            findViewById<ImageView>(R.id.image).setImageBitmap(bitmap)
        } catch (e: java.lang.Exception) {
            null
        }


    }

    //asset 에 있는 json 읽어와 Data Class 의 list 형식으로 변환하기.
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
    }

    data class Post(
        var title: String = "",
        var url: String = "",
        var draft: String = ""
    )
}