package com.example.aroundgym

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aroundgym.data.model.Document

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private val searchFragmentViewStateListener =
        object : SearchFragment.SearchFragmentViewStateListener {
            override fun routeDetail(item: Document) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container_main, DetailFragment.newInstance(item))
                    .addToBackStack("detail").commit()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(
                R.id.container_main,
                SearchFragment.newInstance(searchFragmentViewStateListener)
            ).commit()

    }

}