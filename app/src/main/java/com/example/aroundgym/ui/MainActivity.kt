package com.example.aroundgym.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.aroundgym.R
import com.example.aroundgym.data.model.Document

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private val searchFragmentViewStateListener =
        object : SearchFragment.SearchFragmentViewStateListener {
            override fun routeDetail(item: Pair<Document, Boolean>) {
                _routeDetail(item)
            }
        }

    private val detailFragmentViewStateListener =
        object : DetailFragment.DetailFragmentViewStateListener {
            override fun routeSearch() {
                _routeSearch()
            }

            override fun toggleBookmark(item: Pair<Document, Boolean>) {
                (supportFragmentManager.findFragmentByTag("search") as? SearchFragment).let {
                    it?.toggleBookmark(item)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(
            R.id.container_main,
            SearchFragment.newInstance(searchFragmentViewStateListener),
            "search"
        ).commit()
    }

    private fun _routeDetail(item: Pair<Document, Boolean>) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container_main,
                DetailFragment.newInstance(item, detailFragmentViewStateListener),
                "details"
            ).addToBackStack("details").commit()
    }

    private fun _routeSearch() {
        supportFragmentManager.popBackStack("details", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}