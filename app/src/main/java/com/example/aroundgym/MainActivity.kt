package com.example.aroundgym

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSample()
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